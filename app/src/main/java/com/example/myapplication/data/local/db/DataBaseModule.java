package com.example.myapplication.data.local.db;

import android.app.Application;

import androidx.room.Room;

import com.example.myapplication.data.local.dao.NasaDao;

public class DataBaseModule {
    public static NasaDatabase provideNasaDB(Application application){
        return Room.databaseBuilder(application,NasaDatabase.class,"Nasa Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static NasaDao provideNasaDao(NasaDatabase nasaDatabase){
        return nasaDatabase.nasaDao();
    }
}
