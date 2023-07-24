package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.model.NewsArticles;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class NewsArticleActivity extends AppCompatActivity {
    NewsArticles articles;
    TextView txt_headline, txt_author, txt_time, txt_detail, txt_content;
    ImageView img_news;
    FloatingActionButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);

        txt_headline = findViewById(R.id.txt_headline);
        txt_author = findViewById(R.id.txt_author);
        txt_time = findViewById(R.id.txt_time);
        txt_detail = findViewById(R.id.txt_detail);
        txt_content = findViewById(R.id.txt_content);
        img_news = findViewById(R.id.img_news);
        backBtn = findViewById(R.id.backBtn);

        articles = (NewsArticles) getIntent().getSerializableExtra("Data");

        txt_headline.setText(articles.getTitle());
        txt_author.setText(articles.getAuthor());
        txt_time.setText(articles.getPublishedAt());
        txt_detail.setText(articles.getDescription());
        txt_content.setText(articles.getContent());
        Picasso.get().load(articles.getUrlToImage()).into(img_news);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}