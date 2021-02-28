package com.example.myapplication.data.remote.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Create the retrofit instance that will fetch our request.
 *
 */
public class RetroInstance {

    public static String BASE_URL = "https://api.nasa.gov/planetary/apod/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //Gson converter but you can use another converter for retrofit.
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
