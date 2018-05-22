package com.kkwriter.gallery.entity.activity;

import com.kkwriter.gallery.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by lisha on 2018/5/21 10:39.
 *
 * @author lisha
 */
@Entity(name = "gly_activity")
public class GlyActivity extends BaseEntity {
    private static final long serialVersionUID = -6534580309266300809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Integer activityId;
    @Column(name = "activity_name", length = 50)
    private String activityName;
    @Column(name = "activity_picture_file_name", length = 100)
    private String pictureFileName;
    @Column(name = "activity_url", length = 300)
    private String activityUrl;
    @Column(name = "activity_type")
    private Integer activityType;
    @Column(name = "activity_begin_time")
    private Timestamp activityBeginTime;
    @Column(name = "activity_end_time")
    private Timestamp activityEndTime;
    @Column(name = "activity_desc", length = 300)
    private String activityDescription;
    @Column(name = "is_valid")
    private Integer valid;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Timestamp getActivityBeginTime() {
        return activityBeginTime;
    }

    public void setActivityBeginTime(Timestamp activityBeginTime) {
        this.activityBeginTime = activityBeginTime;
    }

    public Timestamp getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Timestamp activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "GlyActivity{" +
                "activityId='" + activityId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", activityUrl='" + activityUrl + '\'' +
                ", activityType=" + activityType +
                ", activityBeginTime=" + activityBeginTime +
                ", activityEndTime=" + activityEndTime +
                ", activityDescription='" + activityDescription + '\'' +
                ", valid=" + valid +
                '}';
    }

    public void updateMe(GlyActivity that) {
        if (that.getActivityName() != null) {
            this.setActivityName(that.getActivityName());
        }
        if (that.getPictureFileName() != null) {
            this.setPictureFileName(that.getPictureFileName());
        }
        if (that.getActivityUrl() != null) {
            this.setActivityUrl(that.getActivityUrl());
        }
        if (that.getActivityType() != null) {
            this.setActivityType(that.getActivityType());
        }
        if (that.getActivityBeginTime() != null) {
            this.setActivityBeginTime(that.getActivityBeginTime());
        }
        if (that.getActivityEndTime() != null) {
            this.setActivityEndTime(that.getActivityEndTime());
        }
        if (that.getActivityDescription() != null) {
            this.setActivityDescription(that.getActivityDescription());
        }
        if (that.getValid() != null) {
            this.setValid(that.getValid());
        }
        if (that.getCreationTime() != null) {
            this.setCreationTime(that.getCreationTime());
        }
        this.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }

}
