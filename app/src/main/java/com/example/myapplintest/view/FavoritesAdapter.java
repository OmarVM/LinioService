package com.example.myapplintest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplintest.R;
import com.example.myapplintest.model.Product;
import com.example.myapplintest.utils.Constants;
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
        Picasso.get().load(product.getImage()).into(holder.imageView);

        switch (product.getLinioPlusLevel()){
            case 1:
                holder.badgePlus.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.badgePlus48.setVisibility(View.VISIBLE);
                break;
        }

        if (product.getFreeShipping()){
            holder.badgeFreeShipping.setVisibility(View.VISIBLE);
        }

        switch (product.getConditionType()){
            case Constants.API_KEY_CONDITION_TYPE_NEW :
                holder.badgeNew.setVisibility(View.VISIBLE);
                break;
            case  Constants.API_KEY_CONDITION_TYPE_REFURBISHED:
                holder.badgeRefurbished.setVisibility(View.VISIBLE);
                break;
        }

        if(product.getImported()){
            holder.badgeInternational.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView, badgePlus, badgePlus48, badgeRefurbished, badgeNew, badgeInternational, badgeFreeShipping;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewMain);

            badgePlus = itemView.findViewById(R.id.fav_details_plus);
            badgePlus48 = itemView.findViewById(R.id.fav_details_plus_48);
            badgeRefurbished = itemView.findViewById(R.id.fav_details_refurbished);
            badgeNew = itemView.findViewById(R.id.fav_details_new);
            badgeInternational = itemView.findViewById(R.id.fav_details_international);
            badgeFreeShipping = itemView.findViewById(R.id.fav_details_free_shipping);
        }
    }
}
