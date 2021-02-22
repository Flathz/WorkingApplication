package com.example.myapplication.data.model;

public class Nasa {

    private String title;
    private String url;
    private String explanation;

    public Nasa(String title, String url, String explanation) {
        this.title = title;
        this.url = url;
        this.explanation = explanation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
