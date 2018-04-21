package com.kkwrite.gallery.pojo.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GlyReservationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GlyReservationExample() {
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

        public Criteria andReservationIdIsNull() {
            addCriterion("reservation_id is null");
            return (Criteria) this;
        }

        public Criteria andReservationIdIsNotNull() {
            addCriterion("reservation_id is not null");
            return (Criteria) this;
        }

        public Criteria andReservationIdEqualTo(Integer value) {
            addCriterion("reservation_id =", value, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdNotEqualTo(Integer value) {
            addCriterion("reservation_id <>", value, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdGreaterThan(Integer value) {
            addCriterion("reservation_id >", value, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reservation_id >=", value, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdLessThan(Integer value) {
            addCriterion("reservation_id <", value, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdLessThanOrEqualTo(Integer value) {
            addCriterion("reservation_id <=", value, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdIn(List<Integer> values) {
            addCriterion("reservation_id in", values, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdNotIn(List<Integer> values) {
            addCriterion("reservation_id not in", values, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdBetween(Integer value1, Integer value2) {
            addCriterion("reservation_id between", value1, value2, "reservationId");
            return (Criteria) this;
        }

        public Criteria andReservationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reservation_id not between", value1, value2, "reservationId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andReservationNameIsNull() {
            addCriterion("reservation_name is null");
            return (Criteria) this;
        }

        public Criteria andReservationNameIsNotNull() {
            addCriterion("reservation_name is not null");
            return (Criteria) this;
        }

        public Criteria andReservationNameEqualTo(String value) {
            addCriterion("reservation_name =", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameNotEqualTo(String value) {
            addCriterion("reservation_name <>", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameGreaterThan(String value) {
            addCriterion("reservation_name >", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameGreaterThanOrEqualTo(String value) {
            addCriterion("reservation_name >=", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameLessThan(String value) {
            addCriterion("reservation_name <", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameLessThanOrEqualTo(String value) {
            addCriterion("reservation_name <=", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameLike(String value) {
            addCriterion("reservation_name like", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameNotLike(String value) {
            addCriterion("reservation_name not like", value, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameIn(List<String> values) {
            addCriterion("reservation_name in", values, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameNotIn(List<String> values) {
            addCriterion("reservation_name not in", values, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameBetween(String value1, String value2) {
            addCriterion("reservation_name between", value1, value2, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationNameNotBetween(String value1, String value2) {
            addCriterion("reservation_name not between", value1, value2, "reservationName");
            return (Criteria) this;
        }

        public Criteria andReservationTypeIsNull() {
            addCriterion("reservation_type is null");
            return (Criteria) this;
        }

        public Criteria andReservationTypeIsNotNull() {
            addCriterion("reservation_type is not null");
            return (Criteria) this;
        }

        public Criteria andReservationTypeEqualTo(Integer value) {
            addCriterion("reservation_type =", value, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeNotEqualTo(Integer value) {
            addCriterion("reservation_type <>", value, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeGreaterThan(Integer value) {
            addCriterion("reservation_type >", value, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("reservation_type >=", value, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeLessThan(Integer value) {
            addCriterion("reservation_type <", value, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("reservation_type <=", value, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeIn(List<Integer> values) {
            addCriterion("reservation_type in", values, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeNotIn(List<Integer> values) {
            addCriterion("reservation_type not in", values, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeBetween(Integer value1, Integer value2) {
            addCriterion("reservation_type between", value1, value2, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("reservation_type not between", value1, value2, "reservationType");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameIsNull() {
            addCriterion("reservation_username is null");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameIsNotNull() {
            addCriterion("reservation_username is not null");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameEqualTo(String value) {
            addCriterion("reservation_username =", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameNotEqualTo(String value) {
            addCriterion("reservation_username <>", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameGreaterThan(String value) {
            addCriterion("reservation_username >", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("reservation_username >=", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameLessThan(String value) {
            addCriterion("reservation_username <", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameLessThanOrEqualTo(String value) {
            addCriterion("reservation_username <=", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameLike(String value) {
            addCriterion("reservation_username like", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameNotLike(String value) {
            addCriterion("reservation_username not like", value, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameIn(List<String> values) {
            addCriterion("reservation_username in", values, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameNotIn(List<String> values) {
            addCriterion("reservation_username not in", values, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameBetween(String value1, String value2) {
            addCriterion("reservation_username between", value1, value2, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationUsernameNotBetween(String value1, String value2) {
            addCriterion("reservation_username not between", value1, value2, "reservationUsername");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumIsNull() {
            addCriterion("reservation_phone_num is null");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumIsNotNull() {
            addCriterion("reservation_phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumEqualTo(String value) {
            addCriterion("reservation_phone_num =", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumNotEqualTo(String value) {
            addCriterion("reservation_phone_num <>", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumGreaterThan(String value) {
            addCriterion("reservation_phone_num >", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("reservation_phone_num >=", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumLessThan(String value) {
            addCriterion("reservation_phone_num <", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("reservation_phone_num <=", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumLike(String value) {
            addCriterion("reservation_phone_num like", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumNotLike(String value) {
            addCriterion("reservation_phone_num not like", value, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumIn(List<String> values) {
            addCriterion("reservation_phone_num in", values, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumNotIn(List<String> values) {
            addCriterion("reservation_phone_num not in", values, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumBetween(String value1, String value2) {
            addCriterion("reservation_phone_num between", value1, value2, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationPhoneNumNotBetween(String value1, String value2) {
            addCriterion("reservation_phone_num not between", value1, value2, "reservationPhoneNum");
            return (Criteria) this;
        }

        public Criteria andReservationAddressIsNull() {
            addCriterion("reservation_address is null");
            return (Criteria) this;
        }

        public Criteria andReservationAddressIsNotNull() {
            addCriterion("reservation_address is not null");
            return (Criteria) this;
        }

        public Criteria andReservationAddressEqualTo(String value) {
            addCriterion("reservation_address =", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressNotEqualTo(String value) {
            addCriterion("reservation_address <>", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressGreaterThan(String value) {
            addCriterion("reservation_address >", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressGreaterThanOrEqualTo(String value) {
            addCriterion("reservation_address >=", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressLessThan(String value) {
            addCriterion("reservation_address <", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressLessThanOrEqualTo(String value) {
            addCriterion("reservation_address <=", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressLike(String value) {
            addCriterion("reservation_address like", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressNotLike(String value) {
            addCriterion("reservation_address not like", value, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressIn(List<String> values) {
            addCriterion("reservation_address in", values, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressNotIn(List<String> values) {
            addCriterion("reservation_address not in", values, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressBetween(String value1, String value2) {
            addCriterion("reservation_address between", value1, value2, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationAddressNotBetween(String value1, String value2) {
            addCriterion("reservation_address not between", value1, value2, "reservationAddress");
            return (Criteria) this;
        }

        public Criteria andReservationDescIsNull() {
            addCriterion("reservation_desc is null");
            return (Criteria) this;
        }

        public Criteria andReservationDescIsNotNull() {
            addCriterion("reservation_desc is not null");
            return (Criteria) this;
        }

        public Criteria andReservationDescEqualTo(String value) {
            addCriterion("reservation_desc =", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescNotEqualTo(String value) {
            addCriterion("reservation_desc <>", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescGreaterThan(String value) {
            addCriterion("reservation_desc >", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescGreaterThanOrEqualTo(String value) {
            addCriterion("reservation_desc >=", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescLessThan(String value) {
            addCriterion("reservation_desc <", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescLessThanOrEqualTo(String value) {
            addCriterion("reservation_desc <=", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescLike(String value) {
            addCriterion("reservation_desc like", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescNotLike(String value) {
            addCriterion("reservation_desc not like", value, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescIn(List<String> values) {
            addCriterion("reservation_desc in", values, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescNotIn(List<String> values) {
            addCriterion("reservation_desc not in", values, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescBetween(String value1, String value2) {
            addCriterion("reservation_desc between", value1, value2, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationDescNotBetween(String value1, String value2) {
            addCriterion("reservation_desc not between", value1, value2, "reservationDesc");
            return (Criteria) this;
        }

        public Criteria andReservationTimeIsNull() {
            addCriterion("reservation_time is null");
            return (Criteria) this;
        }

        public Criteria andReservationTimeIsNotNull() {
            addCriterion("reservation_time is not null");
            return (Criteria) this;
        }

        public Criteria andReservationTimeEqualTo(Date value) {
            addCriterion("reservation_time =", value, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeNotEqualTo(Date value) {
            addCriterion("reservation_time <>", value, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeGreaterThan(Date value) {
            addCriterion("reservation_time >", value, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reservation_time >=", value, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeLessThan(Date value) {
            addCriterion("reservation_time <", value, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeLessThanOrEqualTo(Date value) {
            addCriterion("reservation_time <=", value, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeIn(List<Date> values) {
            addCriterion("reservation_time in", values, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeNotIn(List<Date> values) {
            addCriterion("reservation_time not in", values, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeBetween(Date value1, Date value2) {
            addCriterion("reservation_time between", value1, value2, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationTimeNotBetween(Date value1, Date value2) {
            addCriterion("reservation_time not between", value1, value2, "reservationTime");
            return (Criteria) this;
        }

        public Criteria andReservationStatusIsNull() {
            addCriterion("reservation_status is null");
            return (Criteria) this;
        }

        public Criteria andReservationStatusIsNotNull() {
            addCriterion("reservation_status is not null");
            return (Criteria) this;
        }

        public Criteria andReservationStatusEqualTo(Integer value) {
            addCriterion("reservation_status =", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusNotEqualTo(Integer value) {
            addCriterion("reservation_status <>", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusGreaterThan(Integer value) {
            addCriterion("reservation_status >", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("reservation_status >=", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusLessThan(Integer value) {
            addCriterion("reservation_status <", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusLessThanOrEqualTo(Integer value) {
            addCriterion("reservation_status <=", value, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusIn(List<Integer> values) {
            addCriterion("reservation_status in", values, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusNotIn(List<Integer> values) {
            addCriterion("reservation_status not in", values, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusBetween(Integer value1, Integer value2) {
            addCriterion("reservation_status between", value1, value2, "reservationStatus");
            return (Criteria) this;
        }

        public Criteria andReservationStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("reservation_status not between", value1, value2, "reservationStatus");
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