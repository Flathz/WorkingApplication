package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.model.Nasa;
import com.example.myapplication.viewmodel.NasaViewModel;

public class NasaDetails extends AppCompatActivity {

    private ImageView imageViewDetails;
    private TextView titleViewDetails , imageViewExplanationDetails;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        imageViewDetails = findViewById(R.id.detail_image_view);
        titleViewDetails = findViewById(R.id.detail_image_title_text);
        imageViewExplanationDetails = findViewById(R.id.detail_image_explanation_text);

        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if(getIntent().hasExtra("Nasa")){
            Nasa nasa = getIntent().getParcelableExtra("Nasa");
            Log.v("INTENTDATANASADETAILS","incoming intent" + nasa);
        }
    }


}
