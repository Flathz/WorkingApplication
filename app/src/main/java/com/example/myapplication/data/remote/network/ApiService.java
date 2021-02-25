package com.example.myapplication.data.remote.network;

import com.example.myapplication.data.remote.model.Nasa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    static final String TAG = "ApiService";
    static final String API_KEY = "X6ltaOAQ16acvbgUrQA8f9jSNcrUhMCYpF9jdh1R";

    @GET("?count=20&api_key="+API_KEY)
    Call<List<Nasa>> getNasaList();


}
