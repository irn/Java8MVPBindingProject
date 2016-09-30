package com.ivakhnenko.javamvp.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.Place;
import com.ivakhnenko.javamvp.databinding.PlaceItemBinding;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 29.09.16.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceHolder> {

    private List<Place> mPlacesList;

    public PlacesAdapter(List<Place> mPlacesList) {
        super();
        this.mPlacesList = mPlacesList;
    }

    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlaceItemBinding placeItemBinding = PlaceItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        placeItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        PlaceHolder holder = new PlaceHolder(placeItemBinding.getRoot());
        return holder;
    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        holder.binding.setPlace(mPlacesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlacesList != null ? mPlacesList.size() : 0;
    }

    public static class PlaceHolder extends RecyclerView.ViewHolder {
        PlaceItemBinding binding;

        public PlaceHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
