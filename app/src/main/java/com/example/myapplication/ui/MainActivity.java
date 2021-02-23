package com.example.myapplication.ui;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.adapter.RecyclerViewAdapter;
import com.example.myapplication.data.model.Nasa;
import com.example.myapplication.ui.viewmodel.NasaViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;

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
        SwitchMaterial switchMaterial = findViewById(R.id.linear_grid_switch);
        changeLayoutOnSwitchPress(recyclerView, switchMaterial);
        adapter = new RecyclerViewAdapter(nasaList,this);
        recyclerView.setAdapter(adapter);
        initViewModel();
    }

    private void changeLayoutOnSwitchPress(RecyclerView recyclerView, SwitchMaterial switchMaterial) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked == true){
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                }
                else{
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }
        });
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

}