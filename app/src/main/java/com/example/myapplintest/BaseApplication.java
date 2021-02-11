package com.example.myapplintest;

import android.app.Application;

import com.example.myapplintest.di.AppComponent;
import com.example.myapplintest.di.DaggerAppComponent;

public class BaseApplication extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().build();
    }
}
