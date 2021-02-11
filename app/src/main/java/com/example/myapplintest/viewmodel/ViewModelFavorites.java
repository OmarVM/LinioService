package com.example.myapplintest.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.myapplintest.model.FavoritesCollection;
import com.example.myapplintest.model.Product;
import com.example.myapplintest.usecase.NetworkListFavoritesImpl;

import java.util.List;

import javax.inject.Inject;

public class ViewModelFavorites extends androidx.lifecycle.ViewModel {

    @Inject
    public NetworkListFavoritesImpl repoRequest;
    public LiveData<List<Product>> mList;
    public LiveData<List<FavoritesCollection>> mListCollections;


    public void startOperation(){
        mList = repoRequest.getInfo();
        mListCollections = repoRequest.getInfoCollection();
    }
}
