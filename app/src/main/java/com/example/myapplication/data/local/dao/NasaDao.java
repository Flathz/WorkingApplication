package com.example.myapplication.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.data.remote.model.Nasa;

import java.util.List;

/** Dao for local database
 * Add some elements and modify them if you wish for more flexibility
 */
@Dao
public interface NasaDao {

    @Query("SELECT * from Nasa")
    LiveData<List<Nasa>> getAllNasa();

    @Insert
    void insert(Nasa nasa);

    @Insert
    void insertAll(Nasa ...nasas);

    @Delete
    void deleteAll(Nasa... nasas);


}
