package com.kkwrite.gallery.mapper.product;

import com.kkwrite.gallery.pojo.product.GlyProductPicture;
import com.kkwrite.gallery.pojo.product.GlyProductPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlyProductPictureMapper {
    int countByExample(GlyProductPictureExample example);

    int deleteByExample(GlyProductPictureExample example);

    int deleteByPrimaryKey(Integer productPictureCode);

    int insert(GlyProductPicture record);

    int insertSelective(GlyProductPicture record);

    List<GlyProductPicture> selectByExample(GlyProductPictureExample example);

    GlyProductPicture selectByPrimaryKey(Integer productPictureCode);
    
    List<GlyProductPicture> selectSelective(GlyProductPicture record);

    int updateByExampleSelective(@Param("record") GlyProductPicture record, @Param("example") GlyProductPictureExample example);

    int updateByExample(@Param("record") GlyProductPicture record, @Param("example") GlyProductPictureExample example);

    int updateByPrimaryKeySelective(GlyProductPicture record);

    int updateByPrimaryKey(GlyProductPicture record);
}