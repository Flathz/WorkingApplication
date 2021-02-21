package com.example.myapplication.network;

import com.example.myapplication.model.Nasa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    static final String TAG = "ApiService";

    @GET("?count=20&api_key=DEMO_KEY")
    Call<List<Nasa>> getNasaList();
}
