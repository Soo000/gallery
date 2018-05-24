package com.kkwriter.gallery.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kkwriter.gallery.entity.BaseEntity;

/**
 * @author lisha
 */
@Entity
public class SysRole extends BaseEntity {
	private static final long serialVersionUID = 733772900802072213L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
