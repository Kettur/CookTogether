package com.example.cooktogether.data.repositories;

import com.example.cooktogether.data.Service.NewsfeedService;
import com.example.cooktogether.data.models.Newsfeed;

import java.util.List;

public class NewsRepository {
    private NewsfeedService newsfeedService;
    public NewsRepository(){
        this.newsfeedService = new NewsfeedService();
    }
    
    public List<Newsfeed> getData(){
        return newsfeedService.news();
    }
}
