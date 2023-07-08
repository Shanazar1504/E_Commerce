package com.example.e_commerce_example.Models;

public class Model_Products {

    private String id;
    private String name;
    private String img_url;
    private String price;
    private String description;


    public Model_Products() {
        this.name = this.name;
        this.id = id;
        this.price = this.price;
        this.description = description;
        this.img_url = this.img_url;

    }


    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
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
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}