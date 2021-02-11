package com.example.myapplintest.di;

import com.example.myapplintest.network.LinAPI;
import com.example.myapplintest.usecase.NetworkListFavoritesImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(LinAPI.URL_MAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public LinAPI getServiceAPI(Retrofit retrofit){
        return retrofit.create(LinAPI.class);
    }

    @Provides
    @Singleton
    public NetworkListFavoritesImpl getListFavorites(LinAPI service){
        return new NetworkListFavoritesImpl(service);
    }
}