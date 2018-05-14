package com.kkwriter.gallery.entity.module;

import com.kkwriter.gallery.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lisha on 2018/5/14 10:20.
 *
 * @author lisha
 */
@Entity(name = "gly_module_item")
public class GlyModuleItem extends BaseEntity {
    private static final long serialVersionUID = 8388166088093318327L;

    @Id
    @Column(name = "module_item_id")
    private Integer moduleItemId;
    @Column(name = "module_id")
    private Integer moduleId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "module_item_name", length = 100)
    private String moduleItemName;
    @Column(name = "module_item_type")
    private Integer moduleItemType;
    @Column(name = "module_item_image", length = 200)
    private String moduleItemImage;
    @Column(name = "module_item_order")
    private Float moduleItemOrder;
    @Column(name = "is_valid")
    private Integer valid;

    public Integer getModuleItemId() {
        return moduleItemId;
    }

    public void setModuleItemId(Integer moduleItemId) {
        this.moduleItemId = moduleItemId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getModuleItemName() {
        return moduleItemName;
    }

    public void setModuleItemName(String moduleItemName) {
        this.moduleItemName = moduleItemName;
    }

    public Integer getModuleItemType() {
        return moduleItemType;
    }

    public void setModuleItemType(Integer moduleItemType) {
        this.moduleItemType = moduleItemType;
    }

    public String getModuleItemImage() {
        return moduleItemImage;
    }

    public void setModuleItemImage(String moduleItemImage) {
        this.moduleItemImage = moduleItemImage;
    }

    public Float getModuleItemOrder() {
        return moduleItemOrder;
    }

    public void setModuleItemOrder(Float moduleItemOrder) {
        this.moduleItemOrder = moduleItemOrder;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "GlyModuleItem{" +
                "moduleItemId=" + moduleItemId +
                ", moduleId=" + moduleId +
                ", productId=" + productId +
                ", moduleItemName='" + moduleItemName + '\'' +
                ", moduleItemType=" + moduleItemType +
                ", moduleItemImage='" + moduleItemImage + '\'' +
                ", moduleItemOrder=" + moduleItemOrder +
                ", valid=" + valid +
                '}';
    }
}
