package com.example.e_commerce_example.Models;


public class JSONResponse {
    public Model_Products[] getData() {
        return data;
    }

    public void setData(Model_Products[] data) {
        this.data = data;
    }

    private Model_Products[] data;

}
