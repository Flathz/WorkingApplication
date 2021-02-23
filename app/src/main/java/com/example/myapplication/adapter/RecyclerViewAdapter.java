package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.NasaDetails;
import com.example.myapplication.R;
import com.example.myapplication.data.model.Nasa;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    public List<Nasa> nasaList = new ArrayList<>();
    private Context mContext;

    public void setNasaList(List<Nasa> nasaList) {
        this.nasaList = nasaList;
        // in case we set data manually
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(List<Nasa> nasaList, Context mContext) {
        this.nasaList = nasaList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(nasaList.get(position).getUrl()).apply(RequestOptions.centerCropTransform())
                .into(holder.image);

        holder.imageTitle.setText(nasaList.get(position).getTitle());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + nasaList.get(position).getTitle());

                Toast.makeText(mContext, nasaList.get(position).getTitle(),Toast.LENGTH_SHORT).show();

                onClickGetData();
            }

            private void onClickGetData() {
                Nasa selectedNasaItem =  nasaList.get(position);
                Intent detailActivity = new Intent(mContext, NasaDetails.class);
                detailActivity.putExtra(NasaDetails.EXTRA_NASA, (Parcelable) selectedNasaItem);
                mContext.startActivity(detailActivity);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (nasaList != null){
            return nasaList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageTitle;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageTitle = itemView.findViewById(R.id.image_title);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
