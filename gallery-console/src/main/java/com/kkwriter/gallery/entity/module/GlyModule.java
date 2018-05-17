package com.kkwriter.gallery.entity.module;

import com.kkwriter.gallery.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lisha on 2018/5/14 9:43.
 *
 * @author lisha
 */
@Entity(name = "gly_module")
public class GlyModule extends BaseEntity {
    private static final long serialVersionUID = 4047236370641599563L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Integer moduleId;
    @Column(name = "module_name", length = 30)
    private String moduleName;
    @Column(name = "p_module_id")
    private Integer parentModuleId;
    @Column(name = "p_module_name", length = 30)
    private String parentModuleName;
    @Column(name = "module_title", length = 30)
    private String moduleTitle;
    @Column(name = "module_template")
    private Integer moduleTemplate;
    @Column(name = "module_desc", length = 10)
    private String moduleDescription;
    @Column(name = "module_order")
    private float moduleOrder;
    @Column(name = "is_valid")
    private Integer valid;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getParentModuleId() {
        return parentModuleId;
    }

    public void setParentModuleId(Integer parentModuleId) {
        this.parentModuleId = parentModuleId;
    }

    public String getParentModuleName() {
        return parentModuleName;
    }

    public void setParentModuleName(String parentModuleName) {
        this.parentModuleName = parentModuleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public Integer getModuleTemplate() {
        return moduleTemplate;
    }

    public void setModuleTemplate(Integer moduleTemplate) {
        this.moduleTemplate = moduleTemplate;
    }

    public float getModuleOrder() {
        return moduleOrder;
    }

    public void setModuleOrder(float moduleOrder) {
        this.moduleOrder = moduleOrder;
    }

    @Override
    public String toString() {
        return "GlyModule{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", parentModuleId=" + parentModuleId +
                ", parentModuleName='" + parentModuleName + '\'' +
                ", moduleTitle='" + moduleTitle + '\'' +
                ", moduleTemplate=" + moduleTemplate +
                ", moduleDescription='" + moduleDescription + '\'' +
                ", moduleOrder=" + moduleOrder +
                ", valid=" + valid +
                '}';
    }
}
