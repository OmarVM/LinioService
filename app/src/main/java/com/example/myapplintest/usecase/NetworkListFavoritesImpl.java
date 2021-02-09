package com.example.myapplintest.usecase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplintest.model.Product;
import com.example.myapplintest.model.Users;
import com.example.myapplintest.network.INetworkListFavorites;
import com.example.myapplintest.network.LinAPI;

import java.util.ArrayList;
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

public class NetworkListFavoritesImpl implements INetworkListFavorites {

    private CompositeDisposable _disposables = new CompositeDisposable();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(LinAPI.URL_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    private LinAPI mService = retrofit.create(LinAPI.class);
    public Single<List<Users>> mListObservable = mService.getUsers();
    private MutableLiveData _mList = new MutableLiveData<List<Product>>();

    @Override
    public LiveData<List<Product>> getInfo() {
        ArrayList<Product> mArr = new ArrayList<>();
        mListObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Users>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Users> usersList) {
                        Log.d("OVM", "List -> " + usersList.get(0).products.toString());
                        for(Product product: usersList.get(0).products.values()){
                            mArr.add(product);
                            Log.d("OVM", "List -> " + product.toString());
                        }
                        _mList.setValue(mArr);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("OVM", "List -> Error : " + e.getMessage());
                    }
                });

        return _mList;
    }
}
