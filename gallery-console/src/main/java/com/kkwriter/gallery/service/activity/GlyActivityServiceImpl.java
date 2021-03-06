package com.kkwriter.gallery.service.activity;

import com.kkwriter.gallery.entity.activity.GlyActivity;
import com.kkwriter.gallery.repository.activity.GlyActivityRepository;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lisha on 2018/5/21 11:24.
 *
 * @author lisha
 */
@Service("activityService")
public class GlyActivityServiceImpl implements IGlyActivityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PICTURE_BASE_PATH = File.separator + "home" + File.separator + "gallery" +
            File.separator + "apps" + File.separator + "gallery-web" + File.separator + "res" + File.separator + "img" + File.separator;
    private static final String DEFAULT_PICTURE_FILENAME = "add_picture.jpg";

    @Resource(name = "glyActivityRepository")
    private GlyActivityRepository glyActivityRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveActivity(GlyActivity activity) {
        glyActivityRepository.saveAndFlush(activity);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveActivity(GlyActivity activity, String activityTime, String picture) {
        if (activity.getActivityId() == null) {
            // 添加广告活动时，先给默认值
            activity.setPictureFileName(DEFAULT_PICTURE_FILENAME);
        } else {
            GlyActivity oldActivity = glyActivityRepository.findById(activity.getActivityId()).orElseThrow(null);
            oldActivity.updateMe(activity);
            activity = oldActivity;
        }
        if (activityTime != null) {
            setActivityTime(activity, activityTime);
        }
        saveActivity(activity);
        if (picture != null) {
            // 若为更新先获取旧图名
            String oldFileName = null;
            if (!DEFAULT_PICTURE_FILENAME.equals(activity.getPictureFileName())) {
                oldFileName = PICTURE_BASE_PATH + activity.getPictureFileName();
            }
            // 再保存新图
            String pictureFileName = generateFileName(activity.getActivityId(), getPictureExtension(picture));
            activity.setPictureFileName(pictureFileName);
            saveActivity(activity);
            savePicture2Disk(pictureFileName, picture, activity.getActivityId());
            // 最后删除旧图
            if (oldFileName != null) {
                // 捕获异常，确保此步骤不会导致事务回滚
                try {
                    File file = new File(oldFileName);
                    if (file.exists()) {
                        if (!file.delete()) {
                            throw new Exception("更新图片时删除旧图失败！");
                        }
                    }
                } catch (Exception e) {
                    logger.error("更新图片时删除旧图失败，旧图路径名：{}，异常信息：", oldFileName, e.getMessage());
                }
            }
        }
    }

    private void savePicture2Disk(final String fileName, final String data, final Integer id) {
        byte[] b = Base64.decodeBase64(data.split(";base64,")[1]);
        for (int i = 0; i < b.length; ++i) {
            // 调整异常数据
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        String imgPath = PICTURE_BASE_PATH + "activity" + File.separator + id;
        File file = new File(imgPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("文件路径创建失败！");
            }
        }
        imgPath = PICTURE_BASE_PATH + fileName;
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imgPath))) {
            bos.write(b);
            bos.flush();
        } catch (IOException e) {
            throw new RuntimeException("写文件到硬盘失败", e);
        }
    }

    private void setActivityTime(GlyActivity activity, String activityTime) {
        String[] times = activityTime.split(" - ");
        activity.setActivityBeginTime(Timestamp.valueOf(times[0].trim()));
        activity.setActivityEndTime(Timestamp.valueOf(times[1].trim()));
    }

    private String getPictureExtension(String picture) {
        int index = picture.indexOf(";");
        if (index == -1) {
            throw new RuntimeException("图片扩展名不正确");
        }
        return picture.substring(11, index);
    }

    private String generateFileName(Integer id, String imageExtension) {
        return "activity" + File.separator + id + File.separator + System.currentTimeMillis() + "." + imageExtension;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteActivity(Integer id) {
        GlyActivity activity = glyActivityRepository.findById(id).orElseThrow(null);
        // 获取到图片文件名
        String pictureFileName = activity.getPictureFileName();
        // 先删除数据库记录
        glyActivityRepository.deleteById(id);
        // 后删除硬盘数据
        if (!DEFAULT_PICTURE_FILENAME.equals(pictureFileName)) {
            String path = PICTURE_BASE_PATH + pictureFileName;
            File file = new File(path);
            if (file.exists()) {
                if (!file.delete()) {
                    throw new RuntimeException("删除图片异常！");
                }
            }
            file = file.getParentFile();
            String[] fileNames = file.list();
            if (fileNames != null && fileNames.length == 0) {
                if (!file.delete()) {
                    logger.error("删除目录：{} 失败！", file.getPath());
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<GlyActivity> queryAllActivity() {
        return glyActivityRepository.findAll(new Sort(Sort.Direction.DESC, "creationTime"));
    }

}
