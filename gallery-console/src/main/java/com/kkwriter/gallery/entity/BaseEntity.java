package com.kkwriter.gallery.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 7040829411481981700L;
	private String creator;
	private Timestamp creationTime;
	private String updator;
	private Timestamp updateTime;
	private String ext_0;
    private String ext_1;
    private String ext_2;
    private String ext_3;
    private String ext_4;
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
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getExt_0() {
		return ext_0;
	}
	public void setExt_0(String ext_0) {
		this.ext_0 = ext_0;
	}
	public String getExt_1() {
		return ext_1;
	}
	public void setExt_1(String ext_1) {
		this.ext_1 = ext_1;
	}
	public String getExt_2() {
		return ext_2;
	}
	public void setExt_2(String ext_2) {
		this.ext_2 = ext_2;
	}
	public String getExt_3() {
		return ext_3;
	}
	public void setExt_3(String ext_3) {
		this.ext_3 = ext_3;
	}
	public String getExt_4() {
		return ext_4;
	}
	public void setExt_4(String ext_4) {
		this.ext_4 = ext_4;
	}
}
