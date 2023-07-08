package com.example.e_commerce_example.Slider;

import com.google.gson.annotations.SerializedName;

public class RecomendedItem {
    @SerializedName("rd_id")
    private Integer id;
    private String discount_id;
    private String image;
    @SerializedName("title")
    private String title;
    @SerializedName("created_at")
    private String desc;
    private String content;
    private Boolean is_pinned;
    private Integer views;


    public RecomendedItem(Integer id, String discount_id, String image, String title, String desc, String content, Boolean is_pinned, Integer views) {
        this.id = id;
        this.discount_id = discount_id;
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.content = content;
        this.is_pinned = is_pinned;
        this.views = views;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(String discount_id) {
        this.discount_id = discount_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIs_pinned() {
        return is_pinned;
    }

    public void setIs_pinned(Boolean is_pinned) {
        this.is_pinned = is_pinned;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
