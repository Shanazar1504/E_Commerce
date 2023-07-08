package com.example.e_commerce_example.Models;

public class Model_Category {

    private String id;
    private String name;
    private String img;


    public Model_Category() {
        this.name = name;
        this.id = id;
        this.img = img;

    }


    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

}