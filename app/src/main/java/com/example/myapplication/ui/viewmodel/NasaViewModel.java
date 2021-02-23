package com.example.myapplication.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.model.Nasa;
import com.example.myapplication.data.network.ApiService;
import com.example.myapplication.data.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasaViewModel extends ViewModel {

    private MutableLiveData<List<Nasa>> nasaList;
    public NasaViewModel() {
        nasaList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Nasa>> getNasaListObserver(){
        return nasaList;
    }

    public void makeApiCall(){
        ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);
        Call<List<Nasa>> call = apiService.getNasaList();
        call.enqueue(new Callback<List<Nasa>>() {
            @Override
            public void onResponse(Call<List<Nasa>> call, Response<List<Nasa>> response) {
                nasaList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Nasa>> call, Throwable t) {
                nasaList.postValue(null);
            }
        });
    }
}
