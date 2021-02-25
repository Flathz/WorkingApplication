package com.example.myapplication.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.remote.model.Nasa;
import com.example.myapplication.data.remote.network.ApiService;
import com.example.myapplication.data.remote.network.RetroInstance;
import com.example.myapplication.data.repository.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasaViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<List<Nasa>> nasaList;

    /** I could have used DI to prevent the creation of a new webservice and a new database whenever i create a new ViewModel with
     * the use of Hilt,Dagger or what we've seen in class.
     * On the other hand, the viewmodel is only created once so there isn't any true benefits to the current state of my application.
     * Tests are not a priority and memory leak shouldn't be an issue since i'm not destroying/re-creating my viewmodel on activity change.
     * I'm not an expert by any means but i did my research on dependency injection and this didn't feel like a necessity in order to not
     * over-engineer the application.
     */

    public NasaViewModel() {
        nasaList = new MutableLiveData<>();
        repository = new Repository();
    }

    public MutableLiveData<List<Nasa>> getNasaListObserver(){
        return nasaList;
    }

    public void makeApiCall(){
        ApiService apiService = repository.getApiService();
        Call<List<Nasa>> call = apiService.getNasaList();
        call.enqueue(new Callback<List<Nasa>>() {
            @Override
            public void onResponse(Call<List<Nasa>> call, Response<List<Nasa>> response) {
                nasaList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Nasa>> call, Throwable t) {
                nasaList.postValue(null);
            }
        });
    }
}
