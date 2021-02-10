package com.example.myapplintest.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.myapplintest.model.FavoritesCollection;
import com.example.myapplintest.model.Product;
import com.example.myapplintest.usecase.NetworkListFavoritesImpl;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private NetworkListFavoritesImpl repoRequest = new NetworkListFavoritesImpl();

    public LiveData<List<Product>> mList = repoRequest.getInfo();
    public LiveData<List<FavoritesCollection>> mListCollections = repoRequest.getInfoCollection();

}
