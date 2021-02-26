package com.example.myapplication.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nasa implements Parcelable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @PrimaryKey
    private int id;
    private String title;
    private String url;
    private String explanation;
    public Nasa(){}

    protected Nasa(Parcel in) {
        id = in.readInt();
        title = in.readString();
        url = in.readString();
        explanation = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(explanation);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Nasa> CREATOR = new Parcelable.Creator<Nasa>() {
        @Override
        public Nasa createFromParcel(Parcel in) {
            return new Nasa(in);
        }

        @Override
        public Nasa[] newArray(int size) {
            return new Nasa[size];
        }
    };
}