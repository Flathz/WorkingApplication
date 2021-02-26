package com.example.myapplication.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.data.local.entity.NasaEntity;

import java.util.List;

@Dao
public interface NasaDao {

    @Query("SELECT * from NasaEntity")
    LiveData<List<NasaEntity>> getAllEntities();

    @Insert
    void insertAll(NasaEntity... nasaEntities);

    @Insert
    void insert(NasaEntity nasaEntity);

    @Delete
    void delete(NasaEntity nasaEntity);


}
