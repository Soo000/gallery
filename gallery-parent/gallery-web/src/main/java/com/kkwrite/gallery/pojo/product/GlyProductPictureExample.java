package com.kkwrite.gallery.pojo.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GlyProductPictureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GlyProductPictureExample() {
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

        public Criteria andProductPictureCodeIsNull() {
            addCriterion("product_picture_code is null");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeIsNotNull() {
            addCriterion("product_picture_code is not null");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeEqualTo(Integer value) {
            addCriterion("product_picture_code =", value, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeNotEqualTo(Integer value) {
            addCriterion("product_picture_code <>", value, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeGreaterThan(Integer value) {
            addCriterion("product_picture_code >", value, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_picture_code >=", value, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeLessThan(Integer value) {
            addCriterion("product_picture_code <", value, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeLessThanOrEqualTo(Integer value) {
            addCriterion("product_picture_code <=", value, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeIn(List<Integer> values) {
            addCriterion("product_picture_code in", values, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeNotIn(List<Integer> values) {
            addCriterion("product_picture_code not in", values, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeBetween(Integer value1, Integer value2) {
            addCriterion("product_picture_code between", value1, value2, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductPictureCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("product_picture_code not between", value1, value2, "productPictureCode");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameIsNull() {
            addCriterion("product_picture_name is null");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameIsNotNull() {
            addCriterion("product_picture_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameEqualTo(String value) {
            addCriterion("product_picture_name =", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameNotEqualTo(String value) {
            addCriterion("product_picture_name <>", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameGreaterThan(String value) {
            addCriterion("product_picture_name >", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_picture_name >=", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameLessThan(String value) {
            addCriterion("product_picture_name <", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameLessThanOrEqualTo(String value) {
            addCriterion("product_picture_name <=", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameLike(String value) {
            addCriterion("product_picture_name like", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameNotLike(String value) {
            addCriterion("product_picture_name not like", value, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameIn(List<String> values) {
            addCriterion("product_picture_name in", values, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameNotIn(List<String> values) {
            addCriterion("product_picture_name not in", values, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameBetween(String value1, String value2) {
            addCriterion("product_picture_name between", value1, value2, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureNameNotBetween(String value1, String value2) {
            addCriterion("product_picture_name not between", value1, value2, "productPictureName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameIsNull() {
            addCriterion("product_picture_file_name is null");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameIsNotNull() {
            addCriterion("product_picture_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameEqualTo(String value) {
            addCriterion("product_picture_file_name =", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameNotEqualTo(String value) {
            addCriterion("product_picture_file_name <>", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameGreaterThan(String value) {
            addCriterion("product_picture_file_name >", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_picture_file_name >=", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameLessThan(String value) {
            addCriterion("product_picture_file_name <", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameLessThanOrEqualTo(String value) {
            addCriterion("product_picture_file_name <=", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameLike(String value) {
            addCriterion("product_picture_file_name like", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameNotLike(String value) {
            addCriterion("product_picture_file_name not like", value, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameIn(List<String> values) {
            addCriterion("product_picture_file_name in", values, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameNotIn(List<String> values) {
            addCriterion("product_picture_file_name not in", values, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameBetween(String value1, String value2) {
            addCriterion("product_picture_file_name between", value1, value2, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureFileNameNotBetween(String value1, String value2) {
            addCriterion("product_picture_file_name not between", value1, value2, "productPictureFileName");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthIsNull() {
            addCriterion("product_picture_width is null");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthIsNotNull() {
            addCriterion("product_picture_width is not null");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthEqualTo(String value) {
            addCriterion("product_picture_width =", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthNotEqualTo(String value) {
            addCriterion("product_picture_width <>", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthGreaterThan(String value) {
            addCriterion("product_picture_width >", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthGreaterThanOrEqualTo(String value) {
            addCriterion("product_picture_width >=", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthLessThan(String value) {
            addCriterion("product_picture_width <", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthLessThanOrEqualTo(String value) {
            addCriterion("product_picture_width <=", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthLike(String value) {
            addCriterion("product_picture_width like", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthNotLike(String value) {
            addCriterion("product_picture_width not like", value, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthIn(List<String> values) {
            addCriterion("product_picture_width in", values, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthNotIn(List<String> values) {
            addCriterion("product_picture_width not in", values, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthBetween(String value1, String value2) {
            addCriterion("product_picture_width between", value1, value2, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureWidthNotBetween(String value1, String value2) {
            addCriterion("product_picture_width not between", value1, value2, "productPictureWidth");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightIsNull() {
            addCriterion("product_picture_height is null");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightIsNotNull() {
            addCriterion("product_picture_height is not null");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightEqualTo(String value) {
            addCriterion("product_picture_height =", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightNotEqualTo(String value) {
            addCriterion("product_picture_height <>", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightGreaterThan(String value) {
            addCriterion("product_picture_height >", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightGreaterThanOrEqualTo(String value) {
            addCriterion("product_picture_height >=", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightLessThan(String value) {
            addCriterion("product_picture_height <", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightLessThanOrEqualTo(String value) {
            addCriterion("product_picture_height <=", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightLike(String value) {
            addCriterion("product_picture_height like", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightNotLike(String value) {
            addCriterion("product_picture_height not like", value, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightIn(List<String> values) {
            addCriterion("product_picture_height in", values, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightNotIn(List<String> values) {
            addCriterion("product_picture_height not in", values, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightBetween(String value1, String value2) {
            addCriterion("product_picture_height between", value1, value2, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureHeightNotBetween(String value1, String value2) {
            addCriterion("product_picture_height not between", value1, value2, "productPictureHeight");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeIsNull() {
            addCriterion("product_picture_type is null");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeIsNotNull() {
            addCriterion("product_picture_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeEqualTo(Integer value) {
            addCriterion("product_picture_type =", value, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeNotEqualTo(Integer value) {
            addCriterion("product_picture_type <>", value, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeGreaterThan(Integer value) {
            addCriterion("product_picture_type >", value, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_picture_type >=", value, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeLessThan(Integer value) {
            addCriterion("product_picture_type <", value, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeLessThanOrEqualTo(Integer value) {
            addCriterion("product_picture_type <=", value, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeIn(List<Integer> values) {
            addCriterion("product_picture_type in", values, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeNotIn(List<Integer> values) {
            addCriterion("product_picture_type not in", values, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeBetween(Integer value1, Integer value2) {
            addCriterion("product_picture_type between", value1, value2, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("product_picture_type not between", value1, value2, "productPictureType");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderIsNull() {
            addCriterion("product_picture_order is null");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderIsNotNull() {
            addCriterion("product_picture_order is not null");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderEqualTo(Float value) {
            addCriterion("product_picture_order =", value, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderNotEqualTo(Float value) {
            addCriterion("product_picture_order <>", value, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderGreaterThan(Float value) {
            addCriterion("product_picture_order >", value, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderGreaterThanOrEqualTo(Float value) {
            addCriterion("product_picture_order >=", value, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderLessThan(Float value) {
            addCriterion("product_picture_order <", value, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderLessThanOrEqualTo(Float value) {
            addCriterion("product_picture_order <=", value, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderIn(List<Float> values) {
            addCriterion("product_picture_order in", values, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderNotIn(List<Float> values) {
            addCriterion("product_picture_order not in", values, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderBetween(Float value1, Float value2) {
            addCriterion("product_picture_order between", value1, value2, "productPictureOrder");
            return (Criteria) this;
        }

        public Criteria andProductPictureOrderNotBetween(Float value1, Float value2) {
            addCriterion("product_picture_order not between", value1, value2, "productPictureOrder");
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

        public Criteria andExt1IsNull() {
            addCriterion("ext_1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext_1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext_1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext_1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext_1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext_1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext_1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext_1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext_1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext_1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext_1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext_1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext_1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext_1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext_2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext_2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext_2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext_2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext_2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext_2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext_2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext_2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext_2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext_2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext_2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext_2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext_2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext_2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt3IsNull() {
            addCriterion("ext_3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("ext_3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("ext_3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("ext_3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("ext_3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("ext_3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("ext_3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("ext_3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("ext_3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("ext_3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("ext_3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("ext_3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("ext_3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("ext_3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt4IsNull() {
            addCriterion("ext_4 is null");
            return (Criteria) this;
        }

        public Criteria andExt4IsNotNull() {
            addCriterion("ext_4 is not null");
            return (Criteria) this;
        }

        public Criteria andExt4EqualTo(String value) {
            addCriterion("ext_4 =", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotEqualTo(String value) {
            addCriterion("ext_4 <>", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThan(String value) {
            addCriterion("ext_4 >", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThanOrEqualTo(String value) {
            addCriterion("ext_4 >=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThan(String value) {
            addCriterion("ext_4 <", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThanOrEqualTo(String value) {
            addCriterion("ext_4 <=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Like(String value) {
            addCriterion("ext_4 like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotLike(String value) {
            addCriterion("ext_4 not like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4In(List<String> values) {
            addCriterion("ext_4 in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotIn(List<String> values) {
            addCriterion("ext_4 not in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Between(String value1, String value2) {
            addCriterion("ext_4 between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotBetween(String value1, String value2) {
            addCriterion("ext_4 not between", value1, value2, "ext4");
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