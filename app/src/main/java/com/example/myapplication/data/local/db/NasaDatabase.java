package com.example.myapplication.data.local.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.data.local.dao.NasaDao;
import com.example.myapplication.data.local.entity.NasaEntity;


@Database(entities = NasaEntity.class, version = 1, exportSchema = false)
public abstract class NasaDatabase extends RoomDatabase {
    public abstract NasaDao nasaDao();
}
