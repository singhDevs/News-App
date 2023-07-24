package com.example.newsapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {
    String status;
    int totalResults;
    ArrayList<NewsArticles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<NewsArticles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsArticles> articles) {
        this.articles = articles;
    }
}
