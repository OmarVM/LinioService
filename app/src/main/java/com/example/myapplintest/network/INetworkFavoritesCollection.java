package com.example.myapplintest.network;

import androidx.lifecycle.LiveData;

import com.example.myapplintest.model.FavoritesCollection;

import java.util.List;

public interface INetworkFavoritesCollection {
    LiveData<List<FavoritesCollection>> getInfoCollection();
}
