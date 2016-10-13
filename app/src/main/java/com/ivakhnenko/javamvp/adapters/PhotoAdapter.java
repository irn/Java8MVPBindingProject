package com.ivakhnenko.javamvp.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ivakhnenko.javamvp.databinding.PhotoItemBinding;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 29.09.16.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PlaceHolder> {

    private List<Bitmap> mList;

    public PhotoAdapter(List<Bitmap> mPlacesList) {
        super();
        this.mList = mPlacesList;
    }

    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final PhotoItemBinding itemBinding = PhotoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        PlaceHolder holder = new PlaceHolder(itemBinding.getRoot());
        return holder;
    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        holder.binding.setBitmap(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class PlaceHolder extends RecyclerView.ViewHolder {
        PhotoItemBinding binding;

        public PlaceHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    @BindingAdapter("bind:imageBitmap")
    public static void loadImage(ImageView iv, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }
}
