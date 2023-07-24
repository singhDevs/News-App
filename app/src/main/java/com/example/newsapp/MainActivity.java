package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.model.NewsArticles;
import com.example.newsapp.model.Response;
import com.example.newsapp.service.RetrofitInstance;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    NewsAdapter adapter;
    ProgressDialog dialog;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles...");
        dialog.show();

        searchView = findViewById(R.id.search_bar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles related to " + query);
                dialog.show();
                RetrofitInstance instance = new RetrofitInstance(MainActivity.this);
                instance.getNewsArticles(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Button btn_entertainement = findViewById(R.id.entertainment);
        btn_entertainement.setOnClickListener(this);
        Button btn_general = findViewById(R.id.general);
        btn_general.setOnClickListener(this);
        Button btn_health = findViewById(R.id.health);
        btn_health.setOnClickListener(this);
        Button btn_business = findViewById(R.id.business);
        btn_business.setOnClickListener(this);
        Button btn_sceince = findViewById(R.id.science);
        btn_sceince.setOnClickListener(this);
        Button btn_sports = findViewById(R.id.sports);
        btn_sports.setOnClickListener(this);
        Button btn_technology = findViewById(R.id.technology);
        btn_technology.setOnClickListener(this);

        RetrofitInstance instance = new RetrofitInstance(this);
        instance.getNewsArticles(listener, "general", null);
    }

    private final OnFetchDataListener<Response> listener = new OnFetchDataListener<Response>() {
        @Override
        public void onFetchData(ArrayList<NewsArticles> articles, String message) {
            if(articles.isEmpty()) {
                Toast.makeText(MainActivity.this, "No Data found!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
            else{
                showNews(articles);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_LONG).show();
        }
    };

    private void showNews(ArrayList<NewsArticles> articles){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        adapter = new NewsAdapter(this, articles, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNewsClicked(NewsArticles articles) {
        startActivity(new Intent(MainActivity.this, NewsArticleActivity.class)
                .putExtra("Data", articles));

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString().toLowerCase();

        dialog.setTitle("Fetching news of " + category);
        dialog.show();

        RetrofitInstance instance = new RetrofitInstance(this);
        instance.getNewsArticles(listener, category, null);
    }
}