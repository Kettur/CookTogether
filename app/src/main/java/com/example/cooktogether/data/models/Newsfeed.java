package com.example.cooktogether.data.models;


public class Newsfeed {
    private String postText;
    private int photo;

    public int getPhoto() {
        return photo;
    }

    public String getPostText() {
        return postText;
    }

    public Newsfeed(String postText, int photo){
        this.photo = photo;
        this.postText = postText;
    }
}
