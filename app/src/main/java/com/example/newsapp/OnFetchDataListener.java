package com.example.newsapp;

import com.example.newsapp.model.NewsArticles;

import java.util.ArrayList;

public interface OnFetchDataListener<Response> {
    void onFetchData(ArrayList<NewsArticles> articles, String message);
    void onError(String message);
}
