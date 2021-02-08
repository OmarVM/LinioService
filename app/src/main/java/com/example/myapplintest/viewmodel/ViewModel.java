package com.example.myapplintest.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplintest.model.Product;
import com.example.myapplintest.model.Users;
import com.example.myapplintest.network.LinAPI;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData _mList = new MutableLiveData<List<Product>>();
    private CompositeDisposable _disposables = new CompositeDisposable();


    public LiveData<List<Product>> mList = _mList;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    private LinAPI mService = retrofit.create(LinAPI.class);

    public Single<List<Users>> mListObservable = mService.getUsers();

    public void loadData(){
        mListObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Users>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Users> usersList) {
                        Log.d("OMV", "List -> " + usersList.size());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("OMV", "List -> Error : " + e.getMessage());
                    }
                });
    }
}
