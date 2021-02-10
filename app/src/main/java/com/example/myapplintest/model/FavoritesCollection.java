package com.example.myapplintest.model;

import java.util.List;

public class FavoritesCollection {
    private String description;
    private List<String> listImages;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getListImages() {
        return listImages;
    }

    public void setListImages(List<String> listImages) {
        this.listImages = listImages;
    }
}
