package com.example.myapplication.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.data.local.dao.NasaDao;
import com.example.myapplication.data.remote.model.Nasa;


@Database(entities = Nasa.class, version = 1, exportSchema = false)
public abstract class NasaDatabase extends RoomDatabase {
    public abstract NasaDao nasaDao();
}
