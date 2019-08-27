package com.babydays.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BStudentExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("s.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPetnameIsNull() {
            addCriterion("petname is null");
            return (Criteria) this;
        }

        public Criteria andPetnameIsNotNull() {
            addCriterion("petname is not null");
            return (Criteria) this;
        }

        public Criteria andPetnameEqualTo(String value) {
            addCriterion("petname =", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotEqualTo(String value) {
            addCriterion("petname <>", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameGreaterThan(String value) {
            addCriterion("petname >", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameGreaterThanOrEqualTo(String value) {
            addCriterion("petname >=", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLessThan(String value) {
            addCriterion("petname <", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLessThanOrEqualTo(String value) {
            addCriterion("petname <=", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameLike(String value) {
            addCriterion("petname like", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotLike(String value) {
            addCriterion("petname not like", value, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameIn(List<String> values) {
            addCriterion("petname in", values, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotIn(List<String> values) {
            addCriterion("petname not in", values, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameBetween(String value1, String value2) {
            addCriterion("petname between", value1, value2, "petname");
            return (Criteria) this;
        }

        public Criteria andPetnameNotBetween(String value1, String value2) {
            addCriterion("petname not between", value1, value2, "petname");
            return (Criteria) this;
        }

        public Criteria andHomeIsNull() {
            addCriterion("home is null");
            return (Criteria) this;
        }

        public Criteria andHomeIsNotNull() {
            addCriterion("home is not null");
            return (Criteria) this;
        }

        public Criteria andHomeEqualTo(String value) {
            addCriterion("home =", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeNotEqualTo(String value) {
            addCriterion("home <>", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeGreaterThan(String value) {
            addCriterion("home >", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeGreaterThanOrEqualTo(String value) {
            addCriterion("home >=", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeLessThan(String value) {
            addCriterion("home <", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeLessThanOrEqualTo(String value) {
            addCriterion("home <=", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeLike(String value) {
            addCriterion("home like", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeNotLike(String value) {
            addCriterion("home not like", value, "home");
            return (Criteria) this;
        }

        public Criteria andHomeIn(List<String> values) {
            addCriterion("home in", values, "home");
            return (Criteria) this;
        }

        public Criteria andHomeNotIn(List<String> values) {
            addCriterion("home not in", values, "home");
            return (Criteria) this;
        }

        public Criteria andHomeBetween(String value1, String value2) {
            addCriterion("home between", value1, value2, "home");
            return (Criteria) this;
        }

        public Criteria andHomeNotBetween(String value1, String value2) {
            addCriterion("home not between", value1, value2, "home");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andBirthIsNull() {
            addCriterion("birth is null");
            return (Criteria) this;
        }

        public Criteria andBirthIsNotNull() {
            addCriterion("birth is not null");
            return (Criteria) this;
        }

        public Criteria andBirthEqualTo(Date value) {
            addCriterionForJDBCDate("birth =", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotEqualTo(Date value) {
            addCriterionForJDBCDate("birth <>", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthGreaterThan(Date value) {
            addCriterionForJDBCDate("birth >", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birth >=", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthLessThan(Date value) {
            addCriterionForJDBCDate("birth <", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birth <=", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthIn(List<Date> values) {
            addCriterionForJDBCDate("birth in", values, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotIn(List<Date> values) {
            addCriterionForJDBCDate("birth not in", values, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birth between", value1, value2, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birth not between", value1, value2, "birth");
            return (Criteria) this;
        }

        public Criteria andAllergyIsNull() {
            addCriterion("allergy is null");
            return (Criteria) this;
        }

        public Criteria andAllergyIsNotNull() {
            addCriterion("allergy is not null");
            return (Criteria) this;
        }

        public Criteria andAllergyEqualTo(String value) {
            addCriterion("allergy =", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyNotEqualTo(String value) {
            addCriterion("allergy <>", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyGreaterThan(String value) {
            addCriterion("allergy >", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyGreaterThanOrEqualTo(String value) {
            addCriterion("allergy >=", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyLessThan(String value) {
            addCriterion("allergy <", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyLessThanOrEqualTo(String value) {
            addCriterion("allergy <=", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyLike(String value) {
            addCriterion("allergy like", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyNotLike(String value) {
            addCriterion("allergy not like", value, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyIn(List<String> values) {
            addCriterion("allergy in", values, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyNotIn(List<String> values) {
            addCriterion("allergy not in", values, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyBetween(String value1, String value2) {
            addCriterion("allergy between", value1, value2, "allergy");
            return (Criteria) this;
        }

        public Criteria andAllergyNotBetween(String value1, String value2) {
            addCriterion("allergy not between", value1, value2, "allergy");
            return (Criteria) this;
        }

        public Criteria andDiapersIsNull() {
            addCriterion("diapers is null");
            return (Criteria) this;
        }

        public Criteria andDiapersIsNotNull() {
            addCriterion("diapers is not null");
            return (Criteria) this;
        }

        public Criteria andDiapersEqualTo(Integer value) {
            addCriterion("diapers =", value, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersNotEqualTo(Integer value) {
            addCriterion("diapers <>", value, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersGreaterThan(Integer value) {
            addCriterion("diapers >", value, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersGreaterThanOrEqualTo(Integer value) {
            addCriterion("diapers >=", value, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersLessThan(Integer value) {
            addCriterion("diapers <", value, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersLessThanOrEqualTo(Integer value) {
            addCriterion("diapers <=", value, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersIn(List<Integer> values) {
            addCriterion("diapers in", values, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersNotIn(List<Integer> values) {
            addCriterion("diapers not in", values, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersBetween(Integer value1, Integer value2) {
            addCriterion("diapers between", value1, value2, "diapers");
            return (Criteria) this;
        }

        public Criteria andDiapersNotBetween(Integer value1, Integer value2) {
            addCriterion("diapers not between", value1, value2, "diapers");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNull() {
            addCriterion("parent_name is null");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNotNull() {
            addCriterion("parent_name is not null");
            return (Criteria) this;
        }

        public Criteria andParentNameEqualTo(String value) {
            addCriterion("parent_name =", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotEqualTo(String value) {
            addCriterion("parent_name <>", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThan(String value) {
            addCriterion("parent_name >", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThanOrEqualTo(String value) {
            addCriterion("parent_name >=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThan(String value) {
            addCriterion("parent_name <", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThanOrEqualTo(String value) {
            addCriterion("parent_name <=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLike(String value) {
            addCriterion("parent_name like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotLike(String value) {
            addCriterion("parent_name not like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameIn(List<String> values) {
            addCriterion("parent_name in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotIn(List<String> values) {
            addCriterion("parent_name not in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameBetween(String value1, String value2) {
            addCriterion("parent_name between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotBetween(String value1, String value2) {
            addCriterion("parent_name not between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentTypeIsNull() {
            addCriterion("parent_type is null");
            return (Criteria) this;
        }

        public Criteria andParentTypeIsNotNull() {
            addCriterion("parent_type is not null");
            return (Criteria) this;
        }

        public Criteria andParentTypeEqualTo(String value) {
            addCriterion("parent_type =", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeNotEqualTo(String value) {
            addCriterion("parent_type <>", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeGreaterThan(String value) {
            addCriterion("parent_type >", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("parent_type >=", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeLessThan(String value) {
            addCriterion("parent_type <", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeLessThanOrEqualTo(String value) {
            addCriterion("parent_type <=", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeLike(String value) {
            addCriterion("parent_type like", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeNotLike(String value) {
            addCriterion("parent_type not like", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeIn(List<String> values) {
            addCriterion("parent_type in", values, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeNotIn(List<String> values) {
            addCriterion("parent_type not in", values, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeBetween(String value1, String value2) {
            addCriterion("parent_type between", value1, value2, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeNotBetween(String value1, String value2) {
            addCriterion("parent_type not between", value1, value2, "parentType");
            return (Criteria) this;
        }

        public Criteria andTelOneIsNull() {
            addCriterion("tel_one is null");
            return (Criteria) this;
        }

        public Criteria andTelOneIsNotNull() {
            addCriterion("tel_one is not null");
            return (Criteria) this;
        }

        public Criteria andTelOneEqualTo(String value) {
            addCriterion("tel_one =", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneNotEqualTo(String value) {
            addCriterion("tel_one <>", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneGreaterThan(String value) {
            addCriterion("tel_one >", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneGreaterThanOrEqualTo(String value) {
            addCriterion("tel_one >=", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneLessThan(String value) {
            addCriterion("tel_one <", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneLessThanOrEqualTo(String value) {
            addCriterion("tel_one <=", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneLike(String value) {
            addCriterion("tel_one like", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneNotLike(String value) {
            addCriterion("tel_one not like", value, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneIn(List<String> values) {
            addCriterion("tel_one in", values, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneNotIn(List<String> values) {
            addCriterion("tel_one not in", values, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneBetween(String value1, String value2) {
            addCriterion("tel_one between", value1, value2, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelOneNotBetween(String value1, String value2) {
            addCriterion("tel_one not between", value1, value2, "telOne");
            return (Criteria) this;
        }

        public Criteria andTelTwoIsNull() {
            addCriterion("tel_two is null");
            return (Criteria) this;
        }

        public Criteria andTelTwoIsNotNull() {
            addCriterion("tel_two is not null");
            return (Criteria) this;
        }

        public Criteria andTelTwoEqualTo(String value) {
            addCriterion("tel_two =", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoNotEqualTo(String value) {
            addCriterion("tel_two <>", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoGreaterThan(String value) {
            addCriterion("tel_two >", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoGreaterThanOrEqualTo(String value) {
            addCriterion("tel_two >=", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoLessThan(String value) {
            addCriterion("tel_two <", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoLessThanOrEqualTo(String value) {
            addCriterion("tel_two <=", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoLike(String value) {
            addCriterion("tel_two like", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoNotLike(String value) {
            addCriterion("tel_two not like", value, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoIn(List<String> values) {
            addCriterion("tel_two in", values, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoNotIn(List<String> values) {
            addCriterion("tel_two not in", values, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoBetween(String value1, String value2) {
            addCriterion("tel_two between", value1, value2, "telTwo");
            return (Criteria) this;
        }

        public Criteria andTelTwoNotBetween(String value1, String value2) {
            addCriterion("tel_two not between", value1, value2, "telTwo");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andGardenIdIsNull() {
            addCriterion("garden_id is null");
            return (Criteria) this;
        }

        public Criteria andGardenIdIsNotNull() {
            addCriterion("garden_id is not null");
            return (Criteria) this;
        }

        public Criteria andGardenIdEqualTo(Integer value) {
            addCriterion("s.garden_id =", value, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdNotEqualTo(Integer value) {
            addCriterion("garden_id <>", value, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdGreaterThan(Integer value) {
            addCriterion("garden_id >", value, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("garden_id >=", value, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdLessThan(Integer value) {
            addCriterion("garden_id <", value, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdLessThanOrEqualTo(Integer value) {
            addCriterion("garden_id <=", value, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdIn(List<Integer> values) {
            addCriterion("garden_id in", values, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdNotIn(List<Integer> values) {
            addCriterion("garden_id not in", values, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdBetween(Integer value1, Integer value2) {
            addCriterion("garden_id between", value1, value2, "gardenId");
            return (Criteria) this;
        }

        public Criteria andGardenIdNotBetween(Integer value1, Integer value2) {
            addCriterion("garden_id not between", value1, value2, "gardenId");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Integer value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("s.status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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