package com.example.myapplication.data.repository;

import com.example.myapplication.data.remote.model.Nasa;
import com.example.myapplication.data.remote.network.ApiService;
import com.example.myapplication.data.remote.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);

    public Repository(){
    }

    public ApiService getApiService() {
        return apiService;
    }

}
