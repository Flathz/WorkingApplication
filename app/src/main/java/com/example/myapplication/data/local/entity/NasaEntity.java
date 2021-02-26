package com.example.myapplication.data.local.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NasaEntity {

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    private int id;

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getUrl() {
        return url;
    }

    private String title;
    private String explanation;
    private String url;

}
