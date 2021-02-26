package com.example.myapplication.data.repository;

import com.example.myapplication.data.remote.network.ApiService;
import com.example.myapplication.data.remote.network.RetroInstance;

public class Repository {

    private ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);

    public Repository(){
    }

    //GETTERS

    public ApiService getApiService() {
        return apiService;
    }


}
