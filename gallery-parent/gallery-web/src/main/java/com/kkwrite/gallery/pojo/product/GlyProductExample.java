package com.kkwrite.gallery.pojo.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GlyProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GlyProductExample() {
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

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductIntroIsNull() {
            addCriterion("product_intro is null");
            return (Criteria) this;
        }

        public Criteria andProductIntroIsNotNull() {
            addCriterion("product_intro is not null");
            return (Criteria) this;
        }

        public Criteria andProductIntroEqualTo(String value) {
            addCriterion("product_intro =", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroNotEqualTo(String value) {
            addCriterion("product_intro <>", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroGreaterThan(String value) {
            addCriterion("product_intro >", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroGreaterThanOrEqualTo(String value) {
            addCriterion("product_intro >=", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroLessThan(String value) {
            addCriterion("product_intro <", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroLessThanOrEqualTo(String value) {
            addCriterion("product_intro <=", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroLike(String value) {
            addCriterion("product_intro like", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroNotLike(String value) {
            addCriterion("product_intro not like", value, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroIn(List<String> values) {
            addCriterion("product_intro in", values, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroNotIn(List<String> values) {
            addCriterion("product_intro not in", values, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroBetween(String value1, String value2) {
            addCriterion("product_intro between", value1, value2, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductIntroNotBetween(String value1, String value2) {
            addCriterion("product_intro not between", value1, value2, "productIntro");
            return (Criteria) this;
        }

        public Criteria andProductDetailIsNull() {
            addCriterion("product_detail is null");
            return (Criteria) this;
        }

        public Criteria andProductDetailIsNotNull() {
            addCriterion("product_detail is not null");
            return (Criteria) this;
        }

        public Criteria andProductDetailEqualTo(String value) {
            addCriterion("product_detail =", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotEqualTo(String value) {
            addCriterion("product_detail <>", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailGreaterThan(String value) {
            addCriterion("product_detail >", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailGreaterThanOrEqualTo(String value) {
            addCriterion("product_detail >=", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLessThan(String value) {
            addCriterion("product_detail <", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLessThanOrEqualTo(String value) {
            addCriterion("product_detail <=", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLike(String value) {
            addCriterion("product_detail like", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotLike(String value) {
            addCriterion("product_detail not like", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailIn(List<String> values) {
            addCriterion("product_detail in", values, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotIn(List<String> values) {
            addCriterion("product_detail not in", values, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailBetween(String value1, String value2) {
            addCriterion("product_detail between", value1, value2, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotBetween(String value1, String value2) {
            addCriterion("product_detail not between", value1, value2, "productDetail");
            return (Criteria) this;
        }

        public Criteria andInitialPriceIsNull() {
            addCriterion("initial_price is null");
            return (Criteria) this;
        }

        public Criteria andInitialPriceIsNotNull() {
            addCriterion("initial_price is not null");
            return (Criteria) this;
        }

        public Criteria andInitialPriceEqualTo(Float value) {
            addCriterion("initial_price =", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceNotEqualTo(Float value) {
            addCriterion("initial_price <>", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceGreaterThan(Float value) {
            addCriterion("initial_price >", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("initial_price >=", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceLessThan(Float value) {
            addCriterion("initial_price <", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceLessThanOrEqualTo(Float value) {
            addCriterion("initial_price <=", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceIn(List<Float> values) {
            addCriterion("initial_price in", values, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceNotIn(List<Float> values) {
            addCriterion("initial_price not in", values, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceBetween(Float value1, Float value2) {
            addCriterion("initial_price between", value1, value2, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceNotBetween(Float value1, Float value2) {
            addCriterion("initial_price not between", value1, value2, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNull() {
            addCriterion("real_price is null");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNotNull() {
            addCriterion("real_price is not null");
            return (Criteria) this;
        }

        public Criteria andRealPriceEqualTo(Float value) {
            addCriterion("real_price =", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotEqualTo(Float value) {
            addCriterion("real_price <>", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThan(Float value) {
            addCriterion("real_price >", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("real_price >=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThan(Float value) {
            addCriterion("real_price <", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThanOrEqualTo(Float value) {
            addCriterion("real_price <=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIn(List<Float> values) {
            addCriterion("real_price in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotIn(List<Float> values) {
            addCriterion("real_price not in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceBetween(Float value1, Float value2) {
            addCriterion("real_price between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotBetween(Float value1, Float value2) {
            addCriterion("real_price not between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Float value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Float value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Float value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Float value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Float value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Float value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Float> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Float> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Float value1, Float value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Float value1, Float value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberIsNull() {
            addCriterion("inventory_number is null");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberIsNotNull() {
            addCriterion("inventory_number is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberEqualTo(Float value) {
            addCriterion("inventory_number =", value, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberNotEqualTo(Float value) {
            addCriterion("inventory_number <>", value, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberGreaterThan(Float value) {
            addCriterion("inventory_number >", value, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberGreaterThanOrEqualTo(Float value) {
            addCriterion("inventory_number >=", value, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberLessThan(Float value) {
            addCriterion("inventory_number <", value, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberLessThanOrEqualTo(Float value) {
            addCriterion("inventory_number <=", value, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberIn(List<Float> values) {
            addCriterion("inventory_number in", values, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberNotIn(List<Float> values) {
            addCriterion("inventory_number not in", values, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberBetween(Float value1, Float value2) {
            addCriterion("inventory_number between", value1, value2, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andInventoryNumberNotBetween(Float value1, Float value2) {
            addCriterion("inventory_number not between", value1, value2, "inventoryNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberIsNull() {
            addCriterion("sale_number is null");
            return (Criteria) this;
        }

        public Criteria andSaleNumberIsNotNull() {
            addCriterion("sale_number is not null");
            return (Criteria) this;
        }

        public Criteria andSaleNumberEqualTo(Float value) {
            addCriterion("sale_number =", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberNotEqualTo(Float value) {
            addCriterion("sale_number <>", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberGreaterThan(Float value) {
            addCriterion("sale_number >", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberGreaterThanOrEqualTo(Float value) {
            addCriterion("sale_number >=", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberLessThan(Float value) {
            addCriterion("sale_number <", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberLessThanOrEqualTo(Float value) {
            addCriterion("sale_number <=", value, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberIn(List<Float> values) {
            addCriterion("sale_number in", values, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberNotIn(List<Float> values) {
            addCriterion("sale_number not in", values, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberBetween(Float value1, Float value2) {
            addCriterion("sale_number between", value1, value2, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andSaleNumberNotBetween(Float value1, Float value2) {
            addCriterion("sale_number not between", value1, value2, "saleNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberIsNull() {
            addCriterion("residue_number is null");
            return (Criteria) this;
        }

        public Criteria andResidueNumberIsNotNull() {
            addCriterion("residue_number is not null");
            return (Criteria) this;
        }

        public Criteria andResidueNumberEqualTo(Float value) {
            addCriterion("residue_number =", value, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberNotEqualTo(Float value) {
            addCriterion("residue_number <>", value, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberGreaterThan(Float value) {
            addCriterion("residue_number >", value, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberGreaterThanOrEqualTo(Float value) {
            addCriterion("residue_number >=", value, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberLessThan(Float value) {
            addCriterion("residue_number <", value, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberLessThanOrEqualTo(Float value) {
            addCriterion("residue_number <=", value, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberIn(List<Float> values) {
            addCriterion("residue_number in", values, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberNotIn(List<Float> values) {
            addCriterion("residue_number not in", values, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberBetween(Float value1, Float value2) {
            addCriterion("residue_number between", value1, value2, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andResidueNumberNotBetween(Float value1, Float value2) {
            addCriterion("residue_number not between", value1, value2, "residueNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberIsNull() {
            addCriterion("book_number is null");
            return (Criteria) this;
        }

        public Criteria andBookNumberIsNotNull() {
            addCriterion("book_number is not null");
            return (Criteria) this;
        }

        public Criteria andBookNumberEqualTo(Float value) {
            addCriterion("book_number =", value, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberNotEqualTo(Float value) {
            addCriterion("book_number <>", value, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberGreaterThan(Float value) {
            addCriterion("book_number >", value, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberGreaterThanOrEqualTo(Float value) {
            addCriterion("book_number >=", value, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberLessThan(Float value) {
            addCriterion("book_number <", value, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberLessThanOrEqualTo(Float value) {
            addCriterion("book_number <=", value, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberIn(List<Float> values) {
            addCriterion("book_number in", values, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberNotIn(List<Float> values) {
            addCriterion("book_number not in", values, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberBetween(Float value1, Float value2) {
            addCriterion("book_number between", value1, value2, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andBookNumberNotBetween(Float value1, Float value2) {
            addCriterion("book_number not between", value1, value2, "bookNumber");
            return (Criteria) this;
        }

        public Criteria andProductOrderIsNull() {
            addCriterion("product_order is null");
            return (Criteria) this;
        }

        public Criteria andProductOrderIsNotNull() {
            addCriterion("product_order is not null");
            return (Criteria) this;
        }

        public Criteria andProductOrderEqualTo(Float value) {
            addCriterion("product_order =", value, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderNotEqualTo(Float value) {
            addCriterion("product_order <>", value, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderGreaterThan(Float value) {
            addCriterion("product_order >", value, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderGreaterThanOrEqualTo(Float value) {
            addCriterion("product_order >=", value, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderLessThan(Float value) {
            addCriterion("product_order <", value, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderLessThanOrEqualTo(Float value) {
            addCriterion("product_order <=", value, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderIn(List<Float> values) {
            addCriterion("product_order in", values, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderNotIn(List<Float> values) {
            addCriterion("product_order not in", values, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderBetween(Float value1, Float value2) {
            addCriterion("product_order between", value1, value2, "productOrder");
            return (Criteria) this;
        }

        public Criteria andProductOrderNotBetween(Float value1, Float value2) {
            addCriterion("product_order not between", value1, value2, "productOrder");
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