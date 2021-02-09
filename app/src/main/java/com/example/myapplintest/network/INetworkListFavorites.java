package com.example.myapplintest.network;

import androidx.lifecycle.LiveData;
import com.example.myapplintest.model.Product;

import java.util.List;

public interface INetworkListFavorites {
    LiveData<List<Product>> getInfo();
}
