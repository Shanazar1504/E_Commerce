package com.example.e_commerce_example.Models;

public class productImage {

    public productImage(String imgUrl) {
        this.img_url = img_url;
    }

    public String getImgUrl() {
        return img_url;
    }

    public void setImgUrl(String imgUrl) {
        this.img_url = img_url;
    }

    private String img_url;


}
