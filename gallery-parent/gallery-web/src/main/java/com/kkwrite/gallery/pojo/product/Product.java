package com.kkwrite.gallery.pojo.product;

import java.util.List;

public class Product extends GlyProduct {

	// 产品类别
	private List<GlyProductType> productTypes;
	// 产品配图
	private List<GlyProductPicture> productPictures;
	// 产品属性
	private List<GlyProductProp> productProps;
	// 产品数量
	private int productNum;
	// 是否选中
	private int isSelected;
	
	public List<GlyProductType> getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(List<GlyProductType> productTypes) {
		this.productTypes = productTypes;
	}
	public List<GlyProductPicture> getProductPictures() {
		return productPictures;
	}
	public void setProductPictures(List<GlyProductPicture> productPictures) {
		this.productPictures = productPictures;
	}
	public List<GlyProductProp> getProductProps() {
		return productProps;
	}
	public void setProductProps(List<GlyProductProp> productProps) {
		this.productProps = productProps;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public int getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}
	
}
