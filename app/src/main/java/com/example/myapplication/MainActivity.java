package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.data.model.Nasa;
import com.example.myapplication.viewmodel.NasaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private List<Nasa> nasaList;
    private NasaViewModel nasaViewModel;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        initRecyclerView();
    }


    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(nasaList,this);
        recyclerView.setAdapter(adapter);
        initViewModel();
        onNasaItemClick();

    }

    private void initViewModel() {
        nasaViewModel = new ViewModelProvider(this).get(NasaViewModel.class);
        TextView notFound = findViewById(R.id.no_results);
        nasaViewModel.getNasaListObserver().observe(this, new Observer<List<Nasa>>() {
            @Override
            public void onChanged(List<Nasa> nasaList) {
                if(nasaList != null){
                    adapter.setNasaList(nasaList);
                }
                else{
                    notFound.setVisibility(View.VISIBLE);

                }

            }
        });
        nasaViewModel.makeApiCall();
    }

    public void onNasaItemClick(){

        //check that the nasa item isn't null
        Log.d(TAG, "onNasaItemClick: " + adapter.getSelectedNasaItem());


        Intent intent = new Intent(this,NasaDetails.class);
        intent.putExtra("NASAINTENT", (Parcelable) adapter.getSelectedNasaItem());
        startActivity(intent);
    }

}