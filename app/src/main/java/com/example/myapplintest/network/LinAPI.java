package com.example.myapplintest.network;

import com.example.myapplintest.model.Users;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface LinAPI {

    String URL_MAIN = "https://raw.githubusercontent.com";

    @GET("/linio-mobile-devs/linio-test-files/main/wishlist.json")
    Single<List<Users>> getUsers();
}
