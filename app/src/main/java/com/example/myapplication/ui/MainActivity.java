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
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.remote.adapter.RecyclerViewAdapter;
import com.example.myapplication.data.remote.model.Nasa;
import com.example.myapplication.ui.viewmodel.NasaViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private List<Nasa> nasaList;
    private NasaViewModel nasaViewModel;
    private RecyclerViewAdapter adapter;


    /** Main function of our application.
     * Designed to make a series of callback in order to initialize the recyclerview, adapter, viewmodel
     * and to set the data to the viewmodel.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        initRecyclerView();
    }


    /** Function to bind the recycler view to the adapter.
     *
     */
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SwitchMaterial switchMaterial = findViewById(R.id.linear_grid_switch);
        changeLayoutOnSwitchPress(recyclerView, switchMaterial);
        adapter = new RecyclerViewAdapter(nasaList,this);
        recyclerView.setAdapter(adapter);
        initViewModel();
    }

    /** Function to switch between layout on switch press between Linear and grid layout.
     *
     * @param recyclerView
     * @param switchMaterial
     */
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


    /** Function to initialize our viewmodel and data.
     *
     */
    private void initViewModel() {
        TextView notFound = findViewById(R.id.no_results);
        nasaViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NasaViewModel.class);
        nasaViewModel.getNasaListObserver().observe(this, new Observer<List<Nasa>>() {
            @Override
            public void onChanged(List<Nasa> nasaList) {
                if(nasaList != null){
                    adapter.setNasaList(nasaList);
                    addApiListToDatabase(nasaList);

                }
                else{
                    notFound.setVisibility(View.VISIBLE);

                }

            }
        });
        nasaViewModel.makeApiCall();
    }

    /** This function allows us to add the whole list of items in our room database
     *
     * @param nasaList
     */
    private void addApiListToDatabase(List<Nasa> nasaList) {
        for (Nasa nasa:
             nasaList) {
            nasaViewModel.insert(nasa);

        }
    }

}