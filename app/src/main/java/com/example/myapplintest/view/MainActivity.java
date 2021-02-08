package com.example.myapplintest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.myapplintest.R;
import com.example.myapplintest.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModel mViewModel = new ViewModelProvider(this).get(ViewModel.class);
        mViewModel.loadData();
    }
}