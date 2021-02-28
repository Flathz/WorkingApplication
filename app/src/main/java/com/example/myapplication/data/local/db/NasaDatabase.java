package com.example.myapplication.data.local.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.data.local.dao.NasaDao;
import com.example.myapplication.data.remote.model.Nasa;


@Database(entities = Nasa.class, version = 1, exportSchema = false)
public abstract class NasaDatabase extends RoomDatabase {

    private static NasaDatabase instance;
    public abstract NasaDao nasaDao();

    /** we create the instance of the database if it's null.
     * We use synchronized since we only want one thread at a time to access it.
     * @param context
     * @return
     */
    public static synchronized NasaDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NasaDatabase.class, "nasa_database")
                    // delete our database and create it from scratch if we increment the version number
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
