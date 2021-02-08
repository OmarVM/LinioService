package com.example.myapplintest.model;

import com.google.gson.JsonObject;

public class Users {
    public int id;
    public String name;
    public String description;
    public JsonObject products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JsonObject getProducts() {
        return products;
    }

    public void setProducts(JsonObject products) {
        this.products = products;
    }
}
