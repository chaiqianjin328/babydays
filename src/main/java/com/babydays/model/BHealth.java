package com.babydays.model;

import java.util.Date;

public class BHealth {
    private Integer id;

    private Integer realAge;

    private Double weight;

    private Double height;

    private Double visionLeft;

    private Double visionRight;

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

    public Integer getRealAge() {
        return realAge;
    }

    public void setRealAge(Integer realAge) {
        this.realAge = realAge;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getVisionLeft() {
        return visionLeft;
    }

    public void setVisionLeft(Double visionLeft) {
        this.visionLeft = visionLeft;
    }

    public Double getVisionRight() {
        return visionRight;
    }

    public void setVisionRight(Double visionRight) {
        this.visionRight = visionRight;
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