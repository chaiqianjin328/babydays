package com.babydays.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BDocumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BDocumentExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("d.id =", value, "id");
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andImgsIsNull() {
            addCriterion("imgs is null");
            return (Criteria) this;
        }

        public Criteria andImgsIsNotNull() {
            addCriterion("imgs is not null");
            return (Criteria) this;
        }

        public Criteria andImgsEqualTo(String value) {
            addCriterion("imgs =", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsNotEqualTo(String value) {
            addCriterion("imgs <>", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsGreaterThan(String value) {
            addCriterion("imgs >", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsGreaterThanOrEqualTo(String value) {
            addCriterion("imgs >=", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsLessThan(String value) {
            addCriterion("imgs <", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsLessThanOrEqualTo(String value) {
            addCriterion("imgs <=", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsLike(String value) {
            addCriterion("imgs like", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsNotLike(String value) {
            addCriterion("imgs not like", value, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsIn(List<String> values) {
            addCriterion("imgs in", values, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsNotIn(List<String> values) {
            addCriterion("imgs not in", values, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsBetween(String value1, String value2) {
            addCriterion("imgs between", value1, value2, "imgs");
            return (Criteria) this;
        }

        public Criteria andImgsNotBetween(String value1, String value2) {
            addCriterion("imgs not between", value1, value2, "imgs");
            return (Criteria) this;
        }

        public Criteria andVoicesIsNull() {
            addCriterion("voices is null");
            return (Criteria) this;
        }

        public Criteria andVoicesIsNotNull() {
            addCriterion("voices is not null");
            return (Criteria) this;
        }

        public Criteria andVoicesEqualTo(String value) {
            addCriterion("voices =", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesNotEqualTo(String value) {
            addCriterion("voices <>", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesGreaterThan(String value) {
            addCriterion("voices >", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesGreaterThanOrEqualTo(String value) {
            addCriterion("voices >=", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesLessThan(String value) {
            addCriterion("voices <", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesLessThanOrEqualTo(String value) {
            addCriterion("voices <=", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesLike(String value) {
            addCriterion("voices like", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesNotLike(String value) {
            addCriterion("voices not like", value, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesIn(List<String> values) {
            addCriterion("voices in", values, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesNotIn(List<String> values) {
            addCriterion("voices not in", values, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesBetween(String value1, String value2) {
            addCriterion("voices between", value1, value2, "voices");
            return (Criteria) this;
        }

        public Criteria andVoicesNotBetween(String value1, String value2) {
            addCriterion("voices not between", value1, value2, "voices");
            return (Criteria) this;
        }

        public Criteria andVideosIsNull() {
            addCriterion("videos is null");
            return (Criteria) this;
        }

        public Criteria andVideosIsNotNull() {
            addCriterion("videos is not null");
            return (Criteria) this;
        }

        public Criteria andVideosEqualTo(String value) {
            addCriterion("videos =", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotEqualTo(String value) {
            addCriterion("videos <>", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosGreaterThan(String value) {
            addCriterion("videos >", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosGreaterThanOrEqualTo(String value) {
            addCriterion("videos >=", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLessThan(String value) {
            addCriterion("videos <", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLessThanOrEqualTo(String value) {
            addCriterion("videos <=", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLike(String value) {
            addCriterion("videos like", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotLike(String value) {
            addCriterion("videos not like", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosIn(List<String> values) {
            addCriterion("videos in", values, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotIn(List<String> values) {
            addCriterion("videos not in", values, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosBetween(String value1, String value2) {
            addCriterion("videos between", value1, value2, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotBetween(String value1, String value2) {
            addCriterion("videos not between", value1, value2, "videos");
            return (Criteria) this;
        }

        public Criteria andVimagesIsNull() {
            addCriterion("vimages is null");
            return (Criteria) this;
        }

        public Criteria andVimagesIsNotNull() {
            addCriterion("vimages is not null");
            return (Criteria) this;
        }

        public Criteria andVimagesEqualTo(String value) {
            addCriterion("vimages =", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesNotEqualTo(String value) {
            addCriterion("vimages <>", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesGreaterThan(String value) {
            addCriterion("vimages >", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesGreaterThanOrEqualTo(String value) {
            addCriterion("vimages >=", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesLessThan(String value) {
            addCriterion("vimages <", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesLessThanOrEqualTo(String value) {
            addCriterion("vimages <=", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesLike(String value) {
            addCriterion("vimages like", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesNotLike(String value) {
            addCriterion("vimages not like", value, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesIn(List<String> values) {
            addCriterion("vimages in", values, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesNotIn(List<String> values) {
            addCriterion("vimages not in", values, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesBetween(String value1, String value2) {
            addCriterion("vimages between", value1, value2, "vimages");
            return (Criteria) this;
        }

        public Criteria andVimagesNotBetween(String value1, String value2) {
            addCriterion("vimages not between", value1, value2, "vimages");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andStuIdIsNull() {
            addCriterion("stu_id is null");
            return (Criteria) this;
        }

        public Criteria andStuIdIsNotNull() {
            addCriterion("stu_id is not null");
            return (Criteria) this;
        }

        public Criteria andStuIdEqualTo(Integer value) {
            addCriterion("stu_id =", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotEqualTo(Integer value) {
            addCriterion("stu_id <>", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdGreaterThan(Integer value) {
            addCriterion("stu_id >", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("stu_id >=", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLessThan(Integer value) {
            addCriterion("stu_id <", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdLessThanOrEqualTo(Integer value) {
            addCriterion("stu_id <=", value, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdIn(List<Integer> values) {
            addCriterion("stu_id in", values, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotIn(List<Integer> values) {
            addCriterion("stu_id not in", values, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdBetween(Integer value1, Integer value2) {
            addCriterion("stu_id between", value1, value2, "stuId");
            return (Criteria) this;
        }

        public Criteria andStuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("stu_id not between", value1, value2, "stuId");
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
            addCriterion("d.type =", value, "type");
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

        public Criteria andAbcataIdIsNull() {
            addCriterion("abcata_id is null");
            return (Criteria) this;
        }

        public Criteria andAbcataIdIsNotNull() {
            addCriterion("abcata_id is not null");
            return (Criteria) this;
        }

        public Criteria andAbcataIdEqualTo(Integer value) {
            addCriterion("abcata_id =", value, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdNotEqualTo(Integer value) {
            addCriterion("abcata_id <>", value, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdGreaterThan(Integer value) {
            addCriterion("abcata_id >", value, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("abcata_id >=", value, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdLessThan(Integer value) {
            addCriterion("abcata_id <", value, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdLessThanOrEqualTo(Integer value) {
            addCriterion("abcata_id <=", value, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdIn(List<Integer> values) {
            addCriterion("abcata_id in", values, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdNotIn(List<Integer> values) {
            addCriterion("abcata_id not in", values, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdBetween(Integer value1, Integer value2) {
            addCriterion("abcata_id between", value1, value2, "abcataId");
            return (Criteria) this;
        }

        public Criteria andAbcataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("abcata_id not between", value1, value2, "abcataId");
            return (Criteria) this;
        }

        public Criteria andWaterIsNull() {
            addCriterion("water is null");
            return (Criteria) this;
        }

        public Criteria andWaterIsNotNull() {
            addCriterion("water is not null");
            return (Criteria) this;
        }

        public Criteria andWaterEqualTo(Integer value) {
            addCriterion("water =", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotEqualTo(Integer value) {
            addCriterion("water <>", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterGreaterThan(Integer value) {
            addCriterion("water >", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("water >=", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterLessThan(Integer value) {
            addCriterion("water <", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterLessThanOrEqualTo(Integer value) {
            addCriterion("water <=", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterIn(List<Integer> values) {
            addCriterion("water in", values, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotIn(List<Integer> values) {
            addCriterion("water not in", values, "water");
            return (Criteria) this;
        }

        public Criteria andWaterBetween(Integer value1, Integer value2) {
            addCriterion("water between", value1, value2, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("water not between", value1, value2, "water");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNull() {
            addCriterion("breakfast is null");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNotNull() {
            addCriterion("breakfast is not null");
            return (Criteria) this;
        }

        public Criteria andBreakfastEqualTo(Integer value) {
            addCriterion("breakfast =", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotEqualTo(Integer value) {
            addCriterion("breakfast <>", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThan(Integer value) {
            addCriterion("breakfast >", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThanOrEqualTo(Integer value) {
            addCriterion("breakfast >=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThan(Integer value) {
            addCriterion("breakfast <", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThanOrEqualTo(Integer value) {
            addCriterion("breakfast <=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastIn(List<Integer> values) {
            addCriterion("breakfast in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotIn(List<Integer> values) {
            addCriterion("breakfast not in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastBetween(Integer value1, Integer value2) {
            addCriterion("breakfast between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotBetween(Integer value1, Integer value2) {
            addCriterion("breakfast not between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andLunchIsNull() {
            addCriterion("lunch is null");
            return (Criteria) this;
        }

        public Criteria andLunchIsNotNull() {
            addCriterion("lunch is not null");
            return (Criteria) this;
        }

        public Criteria andLunchEqualTo(Integer value) {
            addCriterion("lunch =", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchNotEqualTo(Integer value) {
            addCriterion("lunch <>", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchGreaterThan(Integer value) {
            addCriterion("lunch >", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchGreaterThanOrEqualTo(Integer value) {
            addCriterion("lunch >=", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchLessThan(Integer value) {
            addCriterion("lunch <", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchLessThanOrEqualTo(Integer value) {
            addCriterion("lunch <=", value, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchIn(List<Integer> values) {
            addCriterion("lunch in", values, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchNotIn(List<Integer> values) {
            addCriterion("lunch not in", values, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchBetween(Integer value1, Integer value2) {
            addCriterion("lunch between", value1, value2, "lunch");
            return (Criteria) this;
        }

        public Criteria andLunchNotBetween(Integer value1, Integer value2) {
            addCriterion("lunch not between", value1, value2, "lunch");
            return (Criteria) this;
        }

        public Criteria andDinnerIsNull() {
            addCriterion("dinner is null");
            return (Criteria) this;
        }

        public Criteria andDinnerIsNotNull() {
            addCriterion("dinner is not null");
            return (Criteria) this;
        }

        public Criteria andDinnerEqualTo(Integer value) {
            addCriterion("dinner =", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerNotEqualTo(Integer value) {
            addCriterion("dinner <>", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerGreaterThan(Integer value) {
            addCriterion("dinner >", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerGreaterThanOrEqualTo(Integer value) {
            addCriterion("dinner >=", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerLessThan(Integer value) {
            addCriterion("dinner <", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerLessThanOrEqualTo(Integer value) {
            addCriterion("dinner <=", value, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerIn(List<Integer> values) {
            addCriterion("dinner in", values, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerNotIn(List<Integer> values) {
            addCriterion("dinner not in", values, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerBetween(Integer value1, Integer value2) {
            addCriterion("dinner between", value1, value2, "dinner");
            return (Criteria) this;
        }

        public Criteria andDinnerNotBetween(Integer value1, Integer value2) {
            addCriterion("dinner not between", value1, value2, "dinner");
            return (Criteria) this;
        }

        public Criteria andSiestaIsNull() {
            addCriterion("siesta is null");
            return (Criteria) this;
        }

        public Criteria andSiestaIsNotNull() {
            addCriterion("siesta is not null");
            return (Criteria) this;
        }

        public Criteria andSiestaEqualTo(Integer value) {
            addCriterion("siesta =", value, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaNotEqualTo(Integer value) {
            addCriterion("siesta <>", value, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaGreaterThan(Integer value) {
            addCriterion("siesta >", value, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaGreaterThanOrEqualTo(Integer value) {
            addCriterion("siesta >=", value, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaLessThan(Integer value) {
            addCriterion("siesta <", value, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaLessThanOrEqualTo(Integer value) {
            addCriterion("siesta <=", value, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaIn(List<Integer> values) {
            addCriterion("siesta in", values, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaNotIn(List<Integer> values) {
            addCriterion("siesta not in", values, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaBetween(Integer value1, Integer value2) {
            addCriterion("siesta between", value1, value2, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaNotBetween(Integer value1, Integer value2) {
            addCriterion("siesta not between", value1, value2, "siesta");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeIsNull() {
            addCriterion("siesta_time is null");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeIsNotNull() {
            addCriterion("siesta_time is not null");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeEqualTo(String value) {
            addCriterion("siesta_time =", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeNotEqualTo(String value) {
            addCriterion("siesta_time <>", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeGreaterThan(String value) {
            addCriterion("siesta_time >", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeGreaterThanOrEqualTo(String value) {
            addCriterion("siesta_time >=", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeLessThan(String value) {
            addCriterion("siesta_time <", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeLessThanOrEqualTo(String value) {
            addCriterion("siesta_time <=", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeLike(String value) {
            addCriterion("siesta_time like", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeNotLike(String value) {
            addCriterion("siesta_time not like", value, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeIn(List<String> values) {
            addCriterion("siesta_time in", values, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeNotIn(List<String> values) {
            addCriterion("siesta_time not in", values, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeBetween(String value1, String value2) {
            addCriterion("siesta_time between", value1, value2, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andSiestaTimeNotBetween(String value1, String value2) {
            addCriterion("siesta_time not between", value1, value2, "siestaTime");
            return (Criteria) this;
        }

        public Criteria andEgestionIsNull() {
            addCriterion("egestion is null");
            return (Criteria) this;
        }

        public Criteria andEgestionIsNotNull() {
            addCriterion("egestion is not null");
            return (Criteria) this;
        }

        public Criteria andEgestionEqualTo(Integer value) {
            addCriterion("egestion =", value, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionNotEqualTo(Integer value) {
            addCriterion("egestion <>", value, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionGreaterThan(Integer value) {
            addCriterion("egestion >", value, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionGreaterThanOrEqualTo(Integer value) {
            addCriterion("egestion >=", value, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionLessThan(Integer value) {
            addCriterion("egestion <", value, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionLessThanOrEqualTo(Integer value) {
            addCriterion("egestion <=", value, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionIn(List<Integer> values) {
            addCriterion("egestion in", values, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionNotIn(List<Integer> values) {
            addCriterion("egestion not in", values, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionBetween(Integer value1, Integer value2) {
            addCriterion("egestion between", value1, value2, "egestion");
            return (Criteria) this;
        }

        public Criteria andEgestionNotBetween(Integer value1, Integer value2) {
            addCriterion("egestion not between", value1, value2, "egestion");
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