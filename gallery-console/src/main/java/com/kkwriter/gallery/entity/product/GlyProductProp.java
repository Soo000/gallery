package com.kkwriter.gallery.entity.product;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.kkwriter.gallery.entity.BaseEntity;

@Entity(name = "gly_product_prop")
public class GlyProductProp extends BaseEntity {
	private static final long serialVersionUID = 3212762047635814904L;
	
	@Id
	private Integer propId;
	
	private String propName;
	
	private String propType;
	
	private String propValue;
	
	private Integer isValid;

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
}
