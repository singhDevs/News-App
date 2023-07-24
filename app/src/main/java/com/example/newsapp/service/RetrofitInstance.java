package com.example.newsapp.service;

import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.OnFetchDataListener;
import com.example.newsapp.R;
import com.example.newsapp.model.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitInstance {
    Context context;
    public RetrofitInstance(Context context) {
        this.context = context;
    }

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsArticles(OnFetchDataListener listener, String category, String query){
        CallNewsAPI callNewsAPI = retrofit.create(CallNewsAPI.class);
        Call<Response> call = callNewsAPI.callArticles("in", category, query, context.getString(R.string.api_key));

        try{
            call.enqueue(new Callback<Response>(){

                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                    }

                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface CallNewsAPI{
        @GET("top-headlines")
        Call<Response> callArticles(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String apiKey
        );
    }
}
