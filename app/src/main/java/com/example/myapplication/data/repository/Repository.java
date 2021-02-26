package com.example.myapplication.data.repository;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.local.dao.NasaDao;
import com.example.myapplication.data.local.entity.NasaEntity;
import com.example.myapplication.data.remote.model.Nasa;
import com.example.myapplication.data.remote.network.ApiService;
import com.example.myapplication.data.remote.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private NasaDao nasaDao = new NasaDao() {
        @Override
        public LiveData<List<NasaEntity>> getAllEntities() {
            return nasaDao.getAllEntities();
        }

        @Override
        public void insertAll(NasaEntity... nasaEntities) {
            nasaDao.insertAll(nasaEntities);
        }

        @Override
        public void insert(NasaEntity nasaEntity) {
            nasaDao.insert(nasaEntity);

        }

        @Override
        public void delete(NasaEntity nasaEntity) {
            nasaDao.delete(nasaEntity);
        }
    };

    private ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);

    public Repository(){
    }

    //GETTERS

    public ApiService getApiService() {
        return apiService;
    }

    public NasaDao getNasaDao() {
        return nasaDao;
    }

}
