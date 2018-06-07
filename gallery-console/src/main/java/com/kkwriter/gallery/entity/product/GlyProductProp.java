package com.kkwriter.gallery.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kkwriter.gallery.entity.BaseEntity;

/**
 * @author lisha
 */
@Entity(name = "gly_product_prop")
public class GlyProductProp extends BaseEntity {
	private static final long serialVersionUID = 3212762047635814904L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prop_id")
	private Integer propId;

	@Column(name = "prop_name", length = 20)
	private String propName;

	@Column(name = "prop_type", length = 20)
	private String propType;

	@Column(name = "prop_value", length = 200)
	private String propValue;

	@Column(name = "is_valid")
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

	@Override
	public String toString() {
		return "GlyProductProp{" +
				"propId=" + propId +
				", propName='" + propName + '\'' +
				", propType='" + propType + '\'' +
				", propValue='" + propValue + '\'' +
				", isValid=" + isValid +
				'}';
	}
}
