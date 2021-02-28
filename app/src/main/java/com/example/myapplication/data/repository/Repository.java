package com.example.myapplication.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.local.dao.NasaDao;
import com.example.myapplication.data.local.db.NasaDatabase;
import com.example.myapplication.data.remote.model.Nasa;
import com.example.myapplication.data.remote.network.ApiService;
import com.example.myapplication.data.remote.network.RetroInstance;

import java.util.List;

/** Repository designed to choose the source of our data.
 * Functions designed to interact with our database and
 *
 */
public class Repository {

    private ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);
    private NasaDao nasaDao;
    private LiveData<List<Nasa>> allNasaItems;


    public Repository(Application application){
        NasaDatabase nasaDatabase = NasaDatabase.getInstance(application);
        nasaDao = nasaDatabase.nasaDao();
        allNasaItems = nasaDao.getAllNasa();
    }

    public void insert(Nasa nasa){
        new InsertNasaAsyncTask(nasaDao).execute(nasa);
    }

    public void insertAll(Nasa ... nasas){
        new InsertAllNasaAsyncTask(nasaDao).execute(nasas);
    }

    public void deleteAll(Nasa ... nasas){
        new DeleteAllNasaAsyncTask(nasaDao).execute(nasas);

    }

    public LiveData<List<Nasa>> getAllNasaItems(){
        return allNasaItems;
    }

    //create multiple async tasks to deal with blockaged with maindatabase.

    /** Since i don't use a dependency injection, i need to figure out another way to deal with the room database.
     * Fortunately with me, it's possible to bypass the prevention of modification on the main database by
     * adding multiple async tasks.
     */
    private static class InsertNasaAsyncTask extends AsyncTask<Nasa,Void,Void> {

        private NasaDao nasaDao;

        private InsertNasaAsyncTask(NasaDao nasaDao){
            this.nasaDao = nasaDao;
        }

        @Override
        protected Void doInBackground(Nasa... nasas) {
            //we need to pass  a single note so let's pass the first one of the list for the async task
            nasaDao.insert(nasas[0]);
            return null;
        }
    }

    private static class InsertAllNasaAsyncTask extends AsyncTask<Nasa,Void,Void> {

        private NasaDao nasaDao;

        private InsertAllNasaAsyncTask(NasaDao nasaDao){
            this.nasaDao = nasaDao;
        }

        @Override
        protected Void doInBackground(Nasa... nasas) {
            //we need to pass  a single note so let's pass the first one of the list for the async task
            nasaDao.insertAll(nasas);
            return null;
        }
    }

    private static class DeleteAllNasaAsyncTask extends AsyncTask<Nasa,Void,Void> {

        private NasaDao nasaDao;

        private DeleteAllNasaAsyncTask(NasaDao nasaDao){
            this.nasaDao = nasaDao;
        }

        @Override
        protected Void doInBackground(Nasa... nasas) {
            //we need to pass  a single note so let's pass the first one of the list for the async task
            nasaDao.deleteAll(nasas);
            return null;
        }
    }

    //GETTERS

    public ApiService getApiService() {
        return apiService;
    }

}
