package com.example.myapplintest.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplintest.R;
import com.example.myapplintest.model.FavoritesCollection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesCollectionAdapter extends RecyclerView.Adapter<FavoritesCollectionAdapter.FavoritesCollectionViewHolder> {

    private ArrayList<FavoritesCollection> mList;

    public FavoritesCollectionAdapter(ArrayList<FavoritesCollection> mList) {
        this.mList = mList;
    }

    public void updateList(ArrayList<FavoritesCollection> newList){
        mList.clear();
        mList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritesCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_products, parent, false);
        return new FavoritesCollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesCollectionViewHolder holder, int position) {
        FavoritesCollection favorite = mList.get(position);
        holder.description.setText(favorite.getDescription());
        if (!favorite.getListImages().isEmpty() && !favorite.getDescription().isEmpty()){
            String noString = Integer.toString(favorite.getListImages().size());
            holder.noItems.setText(noString);

            for (int i=0; i< favorite.getListImages().size(); i++){
                downloadImages(favorite.getListImages().get(i), i, holder);
            }
        }else{
            holder.description.setText("Add new Collection");
            holder.noItems.setText("0");
            holder.img_first.setImageResource(R.drawable.ic_favorite_placeholder);
            holder.img_second.setImageResource(R.drawable.ic_favorite_placeholder);
            holder.img_third.setImageResource(R.drawable.ic_favorite_placeholder);
        }
    }

    private void downloadImages(String url, int imgNumber, FavoritesCollectionViewHolder holder){
        if (!TextUtils.isEmpty(url)){
            switch (imgNumber){
                case 0 :
                    Picasso.get().load(url).into(holder.img_first);
                    break;
                case 1 :
                    Picasso.get().load(url).into(holder.img_second);
                    break;
                case 2 :
                    Picasso.get().load(url).into(holder.img_third);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FavoritesCollectionViewHolder extends RecyclerView.ViewHolder{
        ImageView img_first, img_second, img_third;
        TextView description, noItems;

        public FavoritesCollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description_fav_collection);
            noItems = itemView.findViewById(R.id.no_items_collection);
            img_first = itemView.findViewById(R.id.iv_collection_fav_first);
            img_second = itemView.findViewById(R.id.iv_collection_fav_second);
            img_third = itemView.findViewById(R.id.iv_collection_fav_third);
        }
    }
}
