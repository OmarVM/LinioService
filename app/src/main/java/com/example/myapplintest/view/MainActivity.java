package com.example.myapplintest.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplintest.R;
import com.example.myapplintest.model.Product;
import com.example.myapplintest.viewmodel.ViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FavoritesAdapter adapterFav = new FavoritesAdapter(new ArrayList<>());
    private RecyclerView recyclerViewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModel mViewModel = new ViewModelProvider(this).get(ViewModel.class);

        recyclerViewProducts = findViewById(R.id.recycler_list_products);
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setAdapter(adapterFav);
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));

        mViewModel.mList.observe(this, products -> {
            Log.d("OVM","MainActiviy - " + products.size());
            adapterFav.updateList((ArrayList<Product>) products);
        });
    }
}