package com.kkwrite.gallery.pojo.product;

import java.util.ArrayList;
import java.util.List;

public class GlyRProductPropCarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GlyRProductPropCarExample() {
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

        public Criteria andPropIdIsNull() {
            addCriterion("prop_id is null");
            return (Criteria) this;
        }

        public Criteria andPropIdIsNotNull() {
            addCriterion("prop_id is not null");
            return (Criteria) this;
        }

        public Criteria andPropIdEqualTo(Integer value) {
            addCriterion("prop_id =", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotEqualTo(Integer value) {
            addCriterion("prop_id <>", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThan(Integer value) {
            addCriterion("prop_id >", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("prop_id >=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThan(Integer value) {
            addCriterion("prop_id <", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdLessThanOrEqualTo(Integer value) {
            addCriterion("prop_id <=", value, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdIn(List<Integer> values) {
            addCriterion("prop_id in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotIn(List<Integer> values) {
            addCriterion("prop_id not in", values, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdBetween(Integer value1, Integer value2) {
            addCriterion("prop_id between", value1, value2, "propId");
            return (Criteria) this;
        }

        public Criteria andPropIdNotBetween(Integer value1, Integer value2) {
            addCriterion("prop_id not between", value1, value2, "propId");
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

        public Criteria andCartCodeIsNull() {
            addCriterion("cart_code is null");
            return (Criteria) this;
        }

        public Criteria andCartCodeIsNotNull() {
            addCriterion("cart_code is not null");
            return (Criteria) this;
        }

        public Criteria andCartCodeEqualTo(String value) {
            addCriterion("cart_code =", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeNotEqualTo(String value) {
            addCriterion("cart_code <>", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeGreaterThan(String value) {
            addCriterion("cart_code >", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cart_code >=", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeLessThan(String value) {
            addCriterion("cart_code <", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeLessThanOrEqualTo(String value) {
            addCriterion("cart_code <=", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeLike(String value) {
            addCriterion("cart_code like", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeNotLike(String value) {
            addCriterion("cart_code not like", value, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeIn(List<String> values) {
            addCriterion("cart_code in", values, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeNotIn(List<String> values) {
            addCriterion("cart_code not in", values, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeBetween(String value1, String value2) {
            addCriterion("cart_code between", value1, value2, "cartCode");
            return (Criteria) this;
        }

        public Criteria andCartCodeNotBetween(String value1, String value2) {
            addCriterion("cart_code not between", value1, value2, "cartCode");
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