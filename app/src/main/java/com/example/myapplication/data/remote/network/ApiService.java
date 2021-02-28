package com.example.myapplication.data.remote.network;

import com.example.myapplication.data.remote.model.Nasa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/** Service with a single request to fetch 20 random nasa pictures
 * Add your API_KEY
 */
public interface ApiService {

    static final String TAG = "ApiService";
    static final String API_KEY = "X6ltaOAQ16acvbgUrQA8f9jSNcrUhMCYpF9jdh1R";

    /** Modify the integer in order to change the number of item being fetched. I recommend 20 items in order to not overload the application.
     *
     * @return
     */
    @GET("?count=20&api_key="+API_KEY)
    Call<List<Nasa>> getNasaList();


}
