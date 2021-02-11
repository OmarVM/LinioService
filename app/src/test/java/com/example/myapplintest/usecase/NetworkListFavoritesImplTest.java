package com.example.myapplintest.usecase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.myapplintest.model.Product;
import com.example.myapplintest.model.Users;
import com.example.myapplintest.network.LinAPI;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NetworkListFavoritesImplTest {

    @Mock
    LinAPI apiMock = Mockito.mock(LinAPI.class);
    ArrayList<Users> listUsers = new ArrayList();
    LiveData<List<Product>> listLiveData;
    NetworkListFavoritesImpl sut;
    Observer<List<Product>> observerList = new Observer<List<Product>>() {
        @Override
        public void onChanged(List<Product> products) {
            Log.d("OVM", "Inside MUTABLE ->" + products.size());
        }
    };


    @BeforeClass
    public static void setupClass(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void setup(){

        Product product = new Product();
        product.setId(12345);
        product.setName("Báscula Digital Omron HN 289 - Aqua");
        product.setConditionType("new");
        product.setFreeShipping(true);
        product.setImported(true);
        product.setLinioPlusLevel(2);
        product.setImage("https://i.linio.com/p/c97a50b1c6c7e3bb2c48c1b4104d5975-product.jpg");

        Users user = new Users();
        user.setDescription("Description");
        HashMap<String, Product> mMap = new HashMap<>();
        mMap.put("asdegrg", product);
        user.setProducts(mMap);
        listUsers.add(user);

        Single<List<Users>> mSingleObject = Single.create(new SingleOnSubscribe<List<Users>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Users>> emitter) throws Throwable {
                emitter.onSuccess((List<Users>) listUsers);
            }
        });

        when(apiMock.getUsers()).thenAnswer(new Answer<Single<List<Users>>>() {
            @Override
            public Single<List<Users>> answer(InvocationOnMock invocation) throws Throwable {
                return mSingleObject;
            }
        });
        //doReturn(Single.just(listFavorites)).when(apiMock.getUsers());
        sut = new NetworkListFavoritesImpl(apiMock);

    }

    @Test
    public void getCollectionsFromServiceSuccess(){
        listLiveData = sut.getInfo();
        listLiveData.observeForever(observerList);
        assertNotNull(listLiveData.getValue().size());
    }

}
