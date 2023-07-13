package com.example.e_commerce_example.Models;

public class Model_Category {

    private String id;
    private String name;
    private String img_url;


    public Model_Category() {
        this.name = name;
        this.id = id;
        this.img_url = img_url;

    }


    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}