package com.babydays.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BAbilitiesCataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BAbilitiesCataExample() {
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

        public Criteria andCataIdIsNull() {
            addCriterion("cata_id is null");
            return (Criteria) this;
        }

        public Criteria andCataIdIsNotNull() {
            addCriterion("cata_id is not null");
            return (Criteria) this;
        }

        public Criteria andCataIdEqualTo(Integer value) {
            addCriterion("cata_id =", value, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdNotEqualTo(Integer value) {
            addCriterion("cata_id <>", value, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdGreaterThan(Integer value) {
            addCriterion("cata_id >", value, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cata_id >=", value, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdLessThan(Integer value) {
            addCriterion("cata_id <", value, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdLessThanOrEqualTo(Integer value) {
            addCriterion("cata_id <=", value, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdIn(List<Integer> values) {
            addCriterion("cata_id in", values, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdNotIn(List<Integer> values) {
            addCriterion("cata_id not in", values, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdBetween(Integer value1, Integer value2) {
            addCriterion("cata_id between", value1, value2, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cata_id not between", value1, value2, "cataId");
            return (Criteria) this;
        }

        public Criteria andCataNameIsNull() {
            addCriterion("cata_name is null");
            return (Criteria) this;
        }

        public Criteria andCataNameIsNotNull() {
            addCriterion("cata_name is not null");
            return (Criteria) this;
        }

        public Criteria andCataNameEqualTo(String value) {
            addCriterion("cata_name =", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameNotEqualTo(String value) {
            addCriterion("cata_name <>", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameGreaterThan(String value) {
            addCriterion("cata_name >", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameGreaterThanOrEqualTo(String value) {
            addCriterion("cata_name >=", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameLessThan(String value) {
            addCriterion("cata_name <", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameLessThanOrEqualTo(String value) {
            addCriterion("cata_name <=", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameLike(String value) {
            addCriterion("cata_name like", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameNotLike(String value) {
            addCriterion("cata_name not like", value, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameIn(List<String> values) {
            addCriterion("cata_name in", values, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameNotIn(List<String> values) {
            addCriterion("cata_name not in", values, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameBetween(String value1, String value2) {
            addCriterion("cata_name between", value1, value2, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataNameNotBetween(String value1, String value2) {
            addCriterion("cata_name not between", value1, value2, "cataName");
            return (Criteria) this;
        }

        public Criteria andCataLevelIsNull() {
            addCriterion("cata_level is null");
            return (Criteria) this;
        }

        public Criteria andCataLevelIsNotNull() {
            addCriterion("cata_level is not null");
            return (Criteria) this;
        }

        public Criteria andCataLevelEqualTo(String value) {
            addCriterion("cata_level =", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelNotEqualTo(String value) {
            addCriterion("cata_level <>", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelGreaterThan(String value) {
            addCriterion("cata_level >", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelGreaterThanOrEqualTo(String value) {
            addCriterion("cata_level >=", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelLessThan(String value) {
            addCriterion("cata_level <", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelLessThanOrEqualTo(String value) {
            addCriterion("cata_level <=", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelLike(String value) {
            addCriterion("cata_level like", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelNotLike(String value) {
            addCriterion("cata_level not like", value, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelIn(List<String> values) {
            addCriterion("cata_level in", values, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelNotIn(List<String> values) {
            addCriterion("cata_level not in", values, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelBetween(String value1, String value2) {
            addCriterion("cata_level between", value1, value2, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelNotBetween(String value1, String value2) {
            addCriterion("cata_level not between", value1, value2, "cataLevel");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeIsNull() {
            addCriterion("cata_level_type is null");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeIsNotNull() {
            addCriterion("cata_level_type is not null");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeEqualTo(Integer value) {
            addCriterion("cata_level_type =", value, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeNotEqualTo(Integer value) {
            addCriterion("cata_level_type <>", value, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeGreaterThan(Integer value) {
            addCriterion("cata_level_type >", value, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cata_level_type >=", value, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeLessThan(Integer value) {
            addCriterion("cata_level_type <", value, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cata_level_type <=", value, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeIn(List<Integer> values) {
            addCriterion("cata_level_type in", values, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeNotIn(List<Integer> values) {
            addCriterion("cata_level_type not in", values, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeBetween(Integer value1, Integer value2) {
            addCriterion("cata_level_type between", value1, value2, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andCataLevelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cata_level_type not between", value1, value2, "cataLevelType");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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