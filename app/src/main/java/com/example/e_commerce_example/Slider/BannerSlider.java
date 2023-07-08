package com.example.e_commerce_example.Slider;

import com.google.gson.annotations.SerializedName;

public class BannerSlider {
    @SerializedName("banner_id")
    private Integer id;
    @SerializedName("image")
    private String banner_image_tm;
    private Integer order;
    private Integer status;
    @SerializedName("hyperlink")
    private String siteURL;
    private String created_at;
    private String updated_at;
    private Integer position;

    public BannerSlider(Integer id, String banner_image_tm, Integer order, Integer status, String siteURL, String created_at, String updated_at, Integer position) {
        this.id = id;
        this.banner_image_tm = banner_image_tm;
        this.order = order;
        this.status = status;
        this.siteURL = siteURL;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBanner_image_tm() {
        return banner_image_tm;
    }

    public void setBanner_image_tm(String banner_image_tm) {
        this.banner_image_tm = banner_image_tm;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
