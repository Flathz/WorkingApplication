package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.data.model.Nasa;

public class NasaDetails extends AppCompatActivity {

    private ImageView imageViewDetails;
    private TextView titleViewDetails , imageViewExplanationDetails;

    /* String to identify the intent */
    public static final String EXTRA_NASA = "extra nasa";

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        imageViewDetails = findViewById(R.id.detail_image_view);
        titleViewDetails = findViewById(R.id.detail_image_title_text);
        imageViewExplanationDetails = findViewById(R.id.detail_image_explanation_text);

        setDataFromIntent();

    }

    /** This function is made to get Data from intent in adapter and to set the views with that data
     *
     */
    private void setDataFromIntent() {
        Nasa nasa = getIntent().getParcelableExtra(EXTRA_NASA);

        Glide.with(this).load(nasa.getUrl()).into(imageViewDetails);
        titleViewDetails.setText(nasa.getTitle());
        imageViewExplanationDetails.setText(nasa.getExplanation());
    }

}
