package com.kkwriter.gallery.entity;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * 实体类父类
 * @author lisha
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 7040829411481981700L;
    @Column(name = "creator")
    private String creator;
    @Column(name = "creation_time", updatable = false)
    private Timestamp creationTime;
    @Column(name = "updator")
    private String updater;
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Column(name = "ext_0", length = 50)
    private String ext0;
    @Column(name = "ext_1", length = 50)
    private String ext1;
    @Column(name = "ext_2", length = 50)
    private String ext2;
    @Column(name = "ext_3", length = 50)
    private String ext3;
    @Column(name = "ext_4", length = 50)
    private String ext4;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getExt0() {
        return ext0;
    }

    public void setExt0(String ext0) {
        this.ext0 = ext0;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "creator='" + creator + '\'' +
                ", creationTime=" + creationTime +
                ", updater='" + updater + '\'' +
                ", updateTime=" + updateTime +
                ", ext0='" + ext0 + '\'' +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                ", ext4='" + ext4 + '\'' +
                '}';
    }

    /**
     * 更新一个对象。从from取值更新to
     * @param to 被更新者
     * @param from 包含新值者
     * @param <T> 泛型参数
     * @throws IllegalAccessException 抛出该异常
     */
    public static <T extends BaseEntity> void update(@NotNull T to, @NotNull T from) throws IllegalAccessException {
        Field[] field = from.getClass().getDeclaredFields();
        Field[] superField = from.getClass().getSuperclass().getDeclaredFields();
        Field[] result = Arrays.copyOf(field, field.length + superField.length);
        System.arraycopy(superField, 0, result, field.length, superField.length);
        for (Field f : result) {
            if ("serialVersionUID".equals(f.getName())) {
                continue;
            }
            f.setAccessible(true);
            Object value = f.get(from);
            if (value != null) {
                f.set(to, value);
            }
        }
    }

}
