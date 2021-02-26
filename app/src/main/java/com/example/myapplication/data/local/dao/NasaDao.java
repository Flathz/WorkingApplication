package com.example.myapplication.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.data.remote.model.Nasa;

import java.util.List;

@Dao
public interface NasaDao {

    @Query("SELECT * from Nasa")
    LiveData<List<Nasa>> getAllEntities();

    @Insert
    void insertAll(Nasa... nasas);

    @Insert
    void insert(Nasa nasa);

    @Delete
    void delete(Nasa nasa);


}
