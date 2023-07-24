package com.example.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    TextView newsHead, newsTag, newsSource;
    ImageView newsImg;
    CardView cardView;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        newsHead = itemView.findViewById(R.id.newsHead);
        newsImg = itemView.findViewById(R.id.newsImg);
        cardView = itemView.findViewById(R.id.cardView);
        newsSource = itemView.findViewById(R.id.newsSource);
    }
}
