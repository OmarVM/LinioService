package com.example.myapplintest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplintest.R;
import com.example.myapplintest.model.Product;
import com.example.myapplintest.viewmodel.ViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModel mViewModel = new ViewModelProvider(this).get(ViewModel.class);

        mViewModel.mList.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d("OVM","MainActiviy - " + products.size());
            }
        });
    }
}