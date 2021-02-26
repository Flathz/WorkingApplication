package com.example.myapplication.data.local.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NasaEntity {

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    private int id;

    private String title;
    private String explanation;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
