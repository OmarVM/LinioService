package com.example.myapplintest.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplintest.R;
import com.example.myapplintest.model.FavoritesCollection;
import com.example.myapplintest.model.Product;
import com.example.myapplintest.viewmodel.ViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FavoritesCollectionAdapter adapterCollectionFav = new FavoritesCollectionAdapter(new ArrayList<>());
    private RecyclerView recyclerViewCollections;

    private FavoritesAdapter adapterFav = new FavoritesAdapter(new ArrayList<>());
    private RecyclerView recyclerViewProducts;

    private TextView total_list_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModel mViewModel = new ViewModelProvider(this).get(ViewModel.class);

        //List Collections
        recyclerViewCollections = findViewById(R.id.recycler_list_collections);
        recyclerViewCollections.setHasFixedSize(true);
        recyclerViewCollections.setAdapter(adapterCollectionFav);
        recyclerViewCollections.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));

        //List Favorites
        recyclerViewProducts = findViewById(R.id.recycler_list_products);
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setAdapter(adapterFav);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));

        total_list_fav = findViewById(R.id.no_full_list_favorites);

        mViewModel.mListCollections.observe(this, favoritesCollections -> {
                adapterCollectionFav.updateList((ArrayList<FavoritesCollection>) favoritesCollections);
                });

        mViewModel.mList.observe(this, products -> {
            adapterFav.updateList((ArrayList<Product>) products);
            String numberToString = " (" + Integer.toString(products.size()) + ")";
            total_list_fav.setText(numberToString);
        });
    }
}