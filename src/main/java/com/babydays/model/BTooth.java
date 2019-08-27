package com.babydays.model;

import java.util.Date;

public class BTooth {
    private Integer id;

    private Integer fluorine;

    private String toothImg;

    private Integer stuId;

    private Integer uid;

    private Date checktime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFluorine() {
        return fluorine;
    }

    public void setFluorine(Integer fluorine) {
        this.fluorine = fluorine;
    }

    public String getToothImg() {
        return toothImg;
    }

    public void setToothImg(String toothImg) {
        this.toothImg = toothImg == null ? null : toothImg.trim();
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}