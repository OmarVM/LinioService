package com.example.myapplintest.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.myapplintest.model.Product;
import com.example.myapplintest.usecase.NetworkListFavoritesImpl;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {

    public LiveData<List<Product>> mList = new NetworkListFavoritesImpl().getInfo();
}
