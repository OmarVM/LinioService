package com.example.myapplintest.di;

import com.example.myapplintest.viewmodel.ViewModelFavorites;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(ViewModelFavorites viewModelFavorites);
}
