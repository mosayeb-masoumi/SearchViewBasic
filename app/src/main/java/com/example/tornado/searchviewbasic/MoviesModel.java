package com.example.tornado.searchviewbasic;

/**
 * Created by Tornado on 7/19/2018.
 */

import com.google.gson.annotations.SerializedName;

public class MoviesModel {
    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String imageUrl;



    //constructor
    public MoviesModel(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }


    //getter & setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}