package com.babydays.model;

public class BDoctor extends BUser {
    private Integer id;

    private String name;

    private String email;

    private String tel;

    private Integer gardenId;

    private Integer status;
    
    private String gardenname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getGardenId() {
        return gardenId;
    }

    public void setGardenId(Integer gardenId) {
        this.gardenId = gardenId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getGardenname() {
		return gardenname;
	}

	public void setGardenname(String gardenname) {
		this.gardenname = gardenname;
	}
    
}