package com.example.myapplication.data.remote.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.ui.NasaDetails;
import com.example.myapplication.R;
import com.example.myapplication.data.remote.model.Nasa;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    public List<Nasa> nasaList;
    private Context mContext;

    /** In case we wish to set the data manually
     * In our application this isn't the case.
     * @param nasaList
     */
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

    /** Function to bind data to each view
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(nasaList.get(position).getUrl()).apply(RequestOptions.centerCropTransform())
                .into(holder.image);

        holder.imageTitle.setText(nasaList.get(position).getTitle());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            /** Listener everytime we cllick on our view
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + nasaList.get(position).getTitle());

                onClickGetData();
            }

            /** Function to create an intent on click and pass it to the detailActivity.
             *
             */
            private void onClickGetData() {
                Nasa selectedNasaItem =  nasaList.get(position);
                Intent detailActivity = new Intent(mContext, NasaDetails.class);
                detailActivity.putExtra(NasaDetails.EXTRA_NASA, selectedNasaItem);
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

    /** View Holder will link view elements for each element of our recycler view
     *
     */
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
