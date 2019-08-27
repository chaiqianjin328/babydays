package com.babydays.model;

import java.util.Date;

public class BAbilitiesCata {
    private Integer cataId;

    private String cataName;

    private String cataLevel;

    private Integer cataLevelType;

    private Integer parentId;

    private Date createtime;

    public Integer getCataId() {
        return cataId;
    }

    public void setCataId(Integer cataId) {
        this.cataId = cataId;
    }

    public String getCataName() {
        return cataName;
    }

    public void setCataName(String cataName) {
        this.cataName = cataName == null ? null : cataName.trim();
    }

    public String getCataLevel() {
        return cataLevel;
    }

    public void setCataLevel(String cataLevel) {
        this.cataLevel = cataLevel == null ? null : cataLevel.trim();
    }

    public Integer getCataLevelType() {
        return cataLevelType;
    }

    public void setCataLevelType(Integer cataLevelType) {
        this.cataLevelType = cataLevelType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}