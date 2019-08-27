package com.babydays.model;

import java.util.Date;

public class BDocument {
    private Integer id;

    private String title;

    private String content;

    private String imgs;

    private String voices;

    private String videos;

    private String vimages;

    private Integer uid;

    private String author;

    private String position;

    private Integer stuId;

    private Integer type;

    private Date createtime;

    private Integer abcataId;

    private Integer water;

    private Integer breakfast;

    private Integer lunch;

    private Integer dinner;

    private Integer siesta;

    private String siestaTime;

    private Integer egestion;
    
    private String stuName;
    
    private Integer classId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public String getVoices() {
        return voices;
    }

    public void setVoices(String voices) {
        this.voices = voices == null ? null : voices.trim();
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos == null ? null : videos.trim();
    }

    public String getVimages() {
        return vimages;
    }

    public void setVimages(String vimages) {
        this.vimages = vimages == null ? null : vimages.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getAbcataId() {
        return abcataId;
    }

    public void setAbcataId(Integer abcataId) {
        this.abcataId = abcataId;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getLunch() {
        return lunch;
    }

    public void setLunch(Integer lunch) {
        this.lunch = lunch;
    }

    public Integer getDinner() {
        return dinner;
    }

    public void setDinner(Integer dinner) {
        this.dinner = dinner;
    }

    public Integer getSiesta() {
        return siesta;
    }

    public void setSiesta(Integer siesta) {
        this.siesta = siesta;
    }

    public String getSiestaTime() {
        return siestaTime;
    }

    public void setSiestaTime(String siestaTime) {
        this.siestaTime = siestaTime == null ? null : siestaTime.trim();
    }

    public Integer getEgestion() {
        return egestion;
    }

    public void setEgestion(Integer egestion) {
        this.egestion = egestion;
    }

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	
    
	
	
}