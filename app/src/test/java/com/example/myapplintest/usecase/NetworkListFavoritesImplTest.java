package com.example.myapplintest.usecase;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.myapplintest.model.Product;
import com.example.myapplintest.model.Users;
import com.example.myapplintest.network.LinAPI;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkListFavoritesImplTest {

    LinAPI apiMock = Mockito.mock(LinAPI.class);
    LiveData<List<Product>> listLiveData;
    NetworkListFavoritesImpl sut;
    Observer<List<Product>> observerList = Mockito.mock(Observer.class);
    LifecycleOwner lifecycleOwner = mock(LifecycleOwner.class);
    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(mock(LifecycleOwner.class));


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @BeforeClass
    public static void setupClass(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        RxJavaPlugins.setErrorHandler( e ->{
            if (e instanceof UndeliverableException){
                e = e.getCause();
            }
            //Log.d("TAG", "UndeliverableException recived, not sure what to do", e);
        });
    }

    @Before
    public void setup(){

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);

        Single<List<Users>> mSingleObject = Single.create(emitter -> emitter.onSuccess((List<Users>) createData()));

        when(apiMock.getUsers()).thenAnswer((Answer<Single<List<Users>>>) invocation -> mSingleObject);
        //doReturn(Single.just(listFavorites)).when(apiMock.getUsers());
    }

    @Test
    public void getCollectionsFromServiceSuccess(){
        sut = new NetworkListFavoritesImpl(apiMock);
        listLiveData = sut.getInfo();
        when(lifecycleOwner.getLifecycle()).thenAnswer((Answer<LifecycleRegistry>) invocation -> lifecycleRegistry);
        listLiveData.observe(lifecycleOwner, observerList);
        assertEquals(1, listLiveData.getValue().size());
    }


    private List<Users> createData(){
        ArrayList<Users> listUsers = new ArrayList();
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
        return listUsers;
    }
}
