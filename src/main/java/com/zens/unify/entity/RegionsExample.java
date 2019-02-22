package com.zens.unify.entity;

import java.util.ArrayList;
import java.util.List;

public class RegionsExample  extends Example{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public RegionsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("UUID is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("UUID is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("UUID =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("UUID <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("UUID >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("UUID >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("UUID <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("UUID <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("UUID like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("UUID not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("UUID in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("UUID not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("UUID between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("UUID not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andFnameIsNull() {
            addCriterion("FName is null");
            return (Criteria) this;
        }

        public Criteria andFnameIsNotNull() {
            addCriterion("FName is not null");
            return (Criteria) this;
        }

        public Criteria andFnameEqualTo(String value) {
            addCriterion("FName =", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameNotEqualTo(String value) {
            addCriterion("FName <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThan(String value) {
            addCriterion("FName >", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThanOrEqualTo(String value) {
            addCriterion("FName >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameLessThan(String value) {
            addCriterion("FName <", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameLessThanOrEqualTo(String value) {
            addCriterion("FName <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameLike(String value) {
            addCriterion("FName like", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameNotLike(String value) {
            addCriterion("FName not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andFnameIn(List<String> values) {
            addCriterion("FName in", values, "name");
            return (Criteria) this;
        }

        public Criteria andFnameNotIn(List<String> values) {
            addCriterion("FName not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andFnameBetween(String value1, String value2) {
            addCriterion("FName between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFnameNotBetween(String value1, String value2) {
            addCriterion("FName not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdIsNull() {
            addCriterion("FParentRegionsId is null");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdIsNotNull() {
            addCriterion("FParentRegionsId is not null");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdEqualTo(Long value) {
            addCriterion("FParentRegionsId =", value, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdNotEqualTo(Long value) {
            addCriterion("FParentRegionsId <>", value, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdGreaterThan(Long value) {
            addCriterion("FParentRegionsId >", value, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("FParentRegionsId >=", value, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdLessThan(Long value) {
            addCriterion("FParentRegionsId <", value, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdLessThanOrEqualTo(Long value) {
            addCriterion("FParentRegionsId <=", value, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdIn(List<Long> values) {
            addCriterion("FParentRegionsId in", values, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdNotIn(List<Long> values) {
            addCriterion("FParentRegionsId not in", values, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdBetween(Long value1, Long value2) {
            addCriterion("FParentRegionsId between", value1, value2, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andParentRegionsIdNotBetween(Long value1, Long value2) {
            addCriterion("FParentRegionsId not between", value1, value2, "parentRegionsId");
            return (Criteria) this;
        }

        public Criteria andFcnnameIsNull() {
            addCriterion("FCnName is null");
            return (Criteria) this;
        }

        public Criteria andFcnnameIsNotNull() {
            addCriterion("FCnName is not null");
            return (Criteria) this;
        }

        public Criteria andFcnnameEqualTo(String value) {
            addCriterion("FCnName =", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameNotEqualTo(String value) {
            addCriterion("FCnName <>", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameGreaterThan(String value) {
            addCriterion("FCnName >", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameGreaterThanOrEqualTo(String value) {
            addCriterion("FCnName >=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameLessThan(String value) {
            addCriterion("FCnName <", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameLessThanOrEqualTo(String value) {
            addCriterion("FCnName <=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameLike(String value) {
            addCriterion("FCnName like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameNotLike(String value) {
            addCriterion("FCnName not like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameIn(List<String> values) {
            addCriterion("FCnName in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameNotIn(List<String> values) {
            addCriterion("FCnName not in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameBetween(String value1, String value2) {
            addCriterion("FCnName between", value1, value2, "cnName");
            return (Criteria) this;
        }

        public Criteria andFcnnameNotBetween(String value1, String value2) {
            addCriterion("FCnName not between", value1, value2, "cnName");
            return (Criteria) this;
        }

        public Criteria andFexpandIsNull() {
            addCriterion("FExpand is null");
            return (Criteria) this;
        }

        public Criteria andFexpandIsNotNull() {
            addCriterion("FExpand is not null");
            return (Criteria) this;
        }

        public Criteria andFexpandEqualTo(String value) {
            addCriterion("FExpand =", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandNotEqualTo(String value) {
            addCriterion("FExpand <>", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandGreaterThan(String value) {
            addCriterion("FExpand >", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandGreaterThanOrEqualTo(String value) {
            addCriterion("FExpand >=", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandLessThan(String value) {
            addCriterion("FExpand <", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandLessThanOrEqualTo(String value) {
            addCriterion("FExpand <=", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandLike(String value) {
            addCriterion("FExpand like", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandNotLike(String value) {
            addCriterion("FExpand not like", value, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandIn(List<String> values) {
            addCriterion("FExpand in", values, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandNotIn(List<String> values) {
            addCriterion("FExpand not in", values, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandBetween(String value1, String value2) {
            addCriterion("FExpand between", value1, value2, "expand");
            return (Criteria) this;
        }

        public Criteria andFexpandNotBetween(String value1, String value2) {
            addCriterion("FExpand not between", value1, value2, "expand");
            return (Criteria) this;
        }

    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_regions
     *
     * @mbggenerated do_not_delete_during_merge Tue Sep 02 11:00:25 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_regions
     *
     * @mbggenerated Tue Sep 02 11:00:25 CST 2014
     */
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
            this.condition =  "t_regions." + condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition =  "t_regions." + condition;
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
            this.condition = "t_regions." + condition;
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