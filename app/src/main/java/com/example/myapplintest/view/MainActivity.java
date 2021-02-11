package com.example.myapplintest.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplintest.BaseApplication;
import com.example.myapplintest.R;
import com.example.myapplintest.model.FavoritesCollection;
import com.example.myapplintest.model.Product;
import com.example.myapplintest.viewmodel.ViewModelFavorites;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FavoritesCollectionAdapter adapterCollectionFav = new FavoritesCollectionAdapter(new ArrayList<>());
    private RecyclerView recyclerViewCollections;

    private FavoritesAdapter adapterFav = new FavoritesAdapter(new ArrayList<>());
    private RecyclerView recyclerViewProducts;

    private TextView total_list_fav;

    private ViewModelFavorites mViewModelFavorites;
    private LottieAnimationView lottieHeader;
    private LinearLayout ll_txt_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModelFavorites = new ViewModelProvider(this).get(ViewModelFavorites.class);
        BaseApplication.getAppComponent().inject(mViewModelFavorites);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        lottieHeader = findViewById(R.id.lottie_animation_loading_head);
        ll_txt_products = findViewById(R.id.ll_txt_list_products);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mViewModelFavorites.startOperation();
        mViewModelFavorites.mListCollections.observe(this, favoritesCollections -> {
            adapterCollectionFav.updateList((ArrayList<FavoritesCollection>) favoritesCollections);
            lottieHeader.setVisibility(View.GONE);
            recyclerViewCollections.setVisibility(View.VISIBLE);
        });

        mViewModelFavorites.mList.observe(this, products -> {
            adapterFav.updateList((ArrayList<Product>) products);
            String numberToString = " (" + Integer.toString(products.size()) + ")";
            total_list_fav.setText(numberToString);
            ll_txt_products.setVisibility(View.VISIBLE);
            recyclerViewProducts.setVisibility(View.VISIBLE);
        });
    }
}