package com.example.cooktogether.data.Service;

import com.example.cooktogether.R;
import com.example.cooktogether.data.models.Newsfeed;

import java.util.ArrayList;
import java.util.List;

public class NewsfeedService {
    public List<Newsfeed> news(){
        ArrayList<Newsfeed> news = new ArrayList<>();
        for (int i = 0 ; i < 60; i++)
            news.add(new Newsfeed("News " + (i + 1), R.drawable.ic_chef_hat_black_24dp));
        return news;
    }
}
