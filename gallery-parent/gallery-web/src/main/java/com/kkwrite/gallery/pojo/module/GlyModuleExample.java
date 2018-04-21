package com.kkwrite.gallery.pojo.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GlyModuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GlyModuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("module_id is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("module_id is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Integer value) {
            addCriterion("module_id =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Integer value) {
            addCriterion("module_id <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Integer value) {
            addCriterion("module_id >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("module_id >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Integer value) {
            addCriterion("module_id <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("module_id <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Integer> values) {
            addCriterion("module_id in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Integer> values) {
            addCriterion("module_id not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("module_id between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("module_id not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNull() {
            addCriterion("module_name is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("module_name is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("module_name =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("module_name <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("module_name >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("module_name >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("module_name <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("module_name <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("module_name like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("module_name not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("module_name in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("module_name not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("module_name between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("module_name not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andPModuleIdIsNull() {
            addCriterion("p_module_id is null");
            return (Criteria) this;
        }

        public Criteria andPModuleIdIsNotNull() {
            addCriterion("p_module_id is not null");
            return (Criteria) this;
        }

        public Criteria andPModuleIdEqualTo(Integer value) {
            addCriterion("p_module_id =", value, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdNotEqualTo(Integer value) {
            addCriterion("p_module_id <>", value, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdGreaterThan(Integer value) {
            addCriterion("p_module_id >", value, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_module_id >=", value, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdLessThan(Integer value) {
            addCriterion("p_module_id <", value, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("p_module_id <=", value, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdIn(List<Integer> values) {
            addCriterion("p_module_id in", values, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdNotIn(List<Integer> values) {
            addCriterion("p_module_id not in", values, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("p_module_id between", value1, value2, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("p_module_id not between", value1, value2, "pModuleId");
            return (Criteria) this;
        }

        public Criteria andPModuleNameIsNull() {
            addCriterion("p_module_name is null");
            return (Criteria) this;
        }

        public Criteria andPModuleNameIsNotNull() {
            addCriterion("p_module_name is not null");
            return (Criteria) this;
        }

        public Criteria andPModuleNameEqualTo(String value) {
            addCriterion("p_module_name =", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameNotEqualTo(String value) {
            addCriterion("p_module_name <>", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameGreaterThan(String value) {
            addCriterion("p_module_name >", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("p_module_name >=", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameLessThan(String value) {
            addCriterion("p_module_name <", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameLessThanOrEqualTo(String value) {
            addCriterion("p_module_name <=", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameLike(String value) {
            addCriterion("p_module_name like", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameNotLike(String value) {
            addCriterion("p_module_name not like", value, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameIn(List<String> values) {
            addCriterion("p_module_name in", values, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameNotIn(List<String> values) {
            addCriterion("p_module_name not in", values, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameBetween(String value1, String value2) {
            addCriterion("p_module_name between", value1, value2, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andPModuleNameNotBetween(String value1, String value2) {
            addCriterion("p_module_name not between", value1, value2, "pModuleName");
            return (Criteria) this;
        }

        public Criteria andModuleDescIsNull() {
            addCriterion("module_desc is null");
            return (Criteria) this;
        }

        public Criteria andModuleDescIsNotNull() {
            addCriterion("module_desc is not null");
            return (Criteria) this;
        }

        public Criteria andModuleDescEqualTo(String value) {
            addCriterion("module_desc =", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescNotEqualTo(String value) {
            addCriterion("module_desc <>", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescGreaterThan(String value) {
            addCriterion("module_desc >", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescGreaterThanOrEqualTo(String value) {
            addCriterion("module_desc >=", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescLessThan(String value) {
            addCriterion("module_desc <", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescLessThanOrEqualTo(String value) {
            addCriterion("module_desc <=", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescLike(String value) {
            addCriterion("module_desc like", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescNotLike(String value) {
            addCriterion("module_desc not like", value, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescIn(List<String> values) {
            addCriterion("module_desc in", values, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescNotIn(List<String> values) {
            addCriterion("module_desc not in", values, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescBetween(String value1, String value2) {
            addCriterion("module_desc between", value1, value2, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andModuleDescNotBetween(String value1, String value2) {
            addCriterion("module_desc not between", value1, value2, "moduleDesc");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNull() {
            addCriterion("is_valid is null");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNotNull() {
            addCriterion("is_valid is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidEqualTo(Integer value) {
            addCriterion("is_valid =", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotEqualTo(Integer value) {
            addCriterion("is_valid <>", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThan(Integer value) {
            addCriterion("is_valid >", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_valid >=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThan(Integer value) {
            addCriterion("is_valid <", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThanOrEqualTo(Integer value) {
            addCriterion("is_valid <=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidIn(List<Integer> values) {
            addCriterion("is_valid in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotIn(List<Integer> values) {
            addCriterion("is_valid not in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidBetween(Integer value1, Integer value2) {
            addCriterion("is_valid between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotBetween(Integer value1, Integer value2) {
            addCriterion("is_valid not between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIsNull() {
            addCriterion("creation_time is null");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIsNotNull() {
            addCriterion("creation_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreationTimeEqualTo(Date value) {
            addCriterion("creation_time =", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotEqualTo(Date value) {
            addCriterion("creation_time <>", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeGreaterThan(Date value) {
            addCriterion("creation_time >", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creation_time >=", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeLessThan(Date value) {
            addCriterion("creation_time <", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeLessThanOrEqualTo(Date value) {
            addCriterion("creation_time <=", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIn(List<Date> values) {
            addCriterion("creation_time in", values, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotIn(List<Date> values) {
            addCriterion("creation_time not in", values, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeBetween(Date value1, Date value2) {
            addCriterion("creation_time between", value1, value2, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotBetween(Date value1, Date value2) {
            addCriterion("creation_time not between", value1, value2, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNull() {
            addCriterion("updator is null");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNotNull() {
            addCriterion("updator is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatorEqualTo(String value) {
            addCriterion("updator =", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotEqualTo(String value) {
            addCriterion("updator <>", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThan(String value) {
            addCriterion("updator >", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThanOrEqualTo(String value) {
            addCriterion("updator >=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThan(String value) {
            addCriterion("updator <", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThanOrEqualTo(String value) {
            addCriterion("updator <=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLike(String value) {
            addCriterion("updator like", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotLike(String value) {
            addCriterion("updator not like", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorIn(List<String> values) {
            addCriterion("updator in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotIn(List<String> values) {
            addCriterion("updator not in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorBetween(String value1, String value2) {
            addCriterion("updator between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotBetween(String value1, String value2) {
            addCriterion("updator not between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andExt0IsNull() {
            addCriterion("ext_0 is null");
            return (Criteria) this;
        }

        public Criteria andExt0IsNotNull() {
            addCriterion("ext_0 is not null");
            return (Criteria) this;
        }

        public Criteria andExt0EqualTo(String value) {
            addCriterion("ext_0 =", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0NotEqualTo(String value) {
            addCriterion("ext_0 <>", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0GreaterThan(String value) {
            addCriterion("ext_0 >", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0GreaterThanOrEqualTo(String value) {
            addCriterion("ext_0 >=", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0LessThan(String value) {
            addCriterion("ext_0 <", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0LessThanOrEqualTo(String value) {
            addCriterion("ext_0 <=", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0Like(String value) {
            addCriterion("ext_0 like", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0NotLike(String value) {
            addCriterion("ext_0 not like", value, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0In(List<String> values) {
            addCriterion("ext_0 in", values, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0NotIn(List<String> values) {
            addCriterion("ext_0 not in", values, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0Between(String value1, String value2) {
            addCriterion("ext_0 between", value1, value2, "ext0");
            return (Criteria) this;
        }

        public Criteria andExt0NotBetween(String value1, String value2) {
            addCriterion("ext_0 not between", value1, value2, "ext0");
            return (Criteria) this;
        }

        public Criteria and扩展字段1IsNull() {
            addCriterion("扩展字段_1 is null");
            return (Criteria) this;
        }

        public Criteria and扩展字段1IsNotNull() {
            addCriterion("扩展字段_1 is not null");
            return (Criteria) this;
        }

        public Criteria and扩展字段1EqualTo(String value) {
            addCriterion("扩展字段_1 =", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1NotEqualTo(String value) {
            addCriterion("扩展字段_1 <>", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1GreaterThan(String value) {
            addCriterion("扩展字段_1 >", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1GreaterThanOrEqualTo(String value) {
            addCriterion("扩展字段_1 >=", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1LessThan(String value) {
            addCriterion("扩展字段_1 <", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1LessThanOrEqualTo(String value) {
            addCriterion("扩展字段_1 <=", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1Like(String value) {
            addCriterion("扩展字段_1 like", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1NotLike(String value) {
            addCriterion("扩展字段_1 not like", value, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1In(List<String> values) {
            addCriterion("扩展字段_1 in", values, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1NotIn(List<String> values) {
            addCriterion("扩展字段_1 not in", values, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1Between(String value1, String value2) {
            addCriterion("扩展字段_1 between", value1, value2, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段1NotBetween(String value1, String value2) {
            addCriterion("扩展字段_1 not between", value1, value2, "扩展字段1");
            return (Criteria) this;
        }

        public Criteria and扩展字段2IsNull() {
            addCriterion("扩展字段_2 is null");
            return (Criteria) this;
        }

        public Criteria and扩展字段2IsNotNull() {
            addCriterion("扩展字段_2 is not null");
            return (Criteria) this;
        }

        public Criteria and扩展字段2EqualTo(String value) {
            addCriterion("扩展字段_2 =", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2NotEqualTo(String value) {
            addCriterion("扩展字段_2 <>", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2GreaterThan(String value) {
            addCriterion("扩展字段_2 >", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2GreaterThanOrEqualTo(String value) {
            addCriterion("扩展字段_2 >=", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2LessThan(String value) {
            addCriterion("扩展字段_2 <", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2LessThanOrEqualTo(String value) {
            addCriterion("扩展字段_2 <=", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2Like(String value) {
            addCriterion("扩展字段_2 like", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2NotLike(String value) {
            addCriterion("扩展字段_2 not like", value, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2In(List<String> values) {
            addCriterion("扩展字段_2 in", values, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2NotIn(List<String> values) {
            addCriterion("扩展字段_2 not in", values, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2Between(String value1, String value2) {
            addCriterion("扩展字段_2 between", value1, value2, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段2NotBetween(String value1, String value2) {
            addCriterion("扩展字段_2 not between", value1, value2, "扩展字段2");
            return (Criteria) this;
        }

        public Criteria and扩展字段3IsNull() {
            addCriterion("扩展字段_3 is null");
            return (Criteria) this;
        }

        public Criteria and扩展字段3IsNotNull() {
            addCriterion("扩展字段_3 is not null");
            return (Criteria) this;
        }

        public Criteria and扩展字段3EqualTo(String value) {
            addCriterion("扩展字段_3 =", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3NotEqualTo(String value) {
            addCriterion("扩展字段_3 <>", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3GreaterThan(String value) {
            addCriterion("扩展字段_3 >", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3GreaterThanOrEqualTo(String value) {
            addCriterion("扩展字段_3 >=", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3LessThan(String value) {
            addCriterion("扩展字段_3 <", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3LessThanOrEqualTo(String value) {
            addCriterion("扩展字段_3 <=", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3Like(String value) {
            addCriterion("扩展字段_3 like", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3NotLike(String value) {
            addCriterion("扩展字段_3 not like", value, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3In(List<String> values) {
            addCriterion("扩展字段_3 in", values, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3NotIn(List<String> values) {
            addCriterion("扩展字段_3 not in", values, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3Between(String value1, String value2) {
            addCriterion("扩展字段_3 between", value1, value2, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段3NotBetween(String value1, String value2) {
            addCriterion("扩展字段_3 not between", value1, value2, "扩展字段3");
            return (Criteria) this;
        }

        public Criteria and扩展字段4IsNull() {
            addCriterion("扩展字段_4 is null");
            return (Criteria) this;
        }

        public Criteria and扩展字段4IsNotNull() {
            addCriterion("扩展字段_4 is not null");
            return (Criteria) this;
        }

        public Criteria and扩展字段4EqualTo(String value) {
            addCriterion("扩展字段_4 =", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4NotEqualTo(String value) {
            addCriterion("扩展字段_4 <>", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4GreaterThan(String value) {
            addCriterion("扩展字段_4 >", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4GreaterThanOrEqualTo(String value) {
            addCriterion("扩展字段_4 >=", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4LessThan(String value) {
            addCriterion("扩展字段_4 <", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4LessThanOrEqualTo(String value) {
            addCriterion("扩展字段_4 <=", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4Like(String value) {
            addCriterion("扩展字段_4 like", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4NotLike(String value) {
            addCriterion("扩展字段_4 not like", value, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4In(List<String> values) {
            addCriterion("扩展字段_4 in", values, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4NotIn(List<String> values) {
            addCriterion("扩展字段_4 not in", values, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4Between(String value1, String value2) {
            addCriterion("扩展字段_4 between", value1, value2, "扩展字段4");
            return (Criteria) this;
        }

        public Criteria and扩展字段4NotBetween(String value1, String value2) {
            addCriterion("扩展字段_4 not between", value1, value2, "扩展字段4");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}