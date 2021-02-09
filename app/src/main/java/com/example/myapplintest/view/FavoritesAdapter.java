package com.example.myapplintest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplintest.R;
import com.example.myapplintest.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private ArrayList<Product> mList;

    public FavoritesAdapter(ArrayList<Product> mList) {
        this.mList = mList;
    }

    public void updateList(ArrayList<Product> newList){
        mList.clear();
        mList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Product product = mList.get(position);
        Picasso.get().load(product.image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewMain);
        }
    }
}
