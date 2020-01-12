package com.bawei.day01shoopingcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.day01shoopingcar.adapter.TuiAdapter;
import com.bawei.day01shoopingcar.api.Api;
import com.bawei.day01shoopingcar.api.CartApiService;
import com.bawei.day01shoopingcar.entity.EntityBean;
import com.bawei.day01shoopingcar.utils.OkHttpUtils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView recy;
    private CheckBox che_aa;
    private TuiAdapter adapter;
    private TextView text_pr;
    private TextView tv_fu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy = (XRecyclerView) findViewById(R.id.recy_wai);
        che_aa = (CheckBox) findViewById(R.id.che_aa);
        text_pr = (TextView) findViewById(R.id.text_pr);
        tv_fu = (TextView) findViewById(R.id.tv_fu);
        recy.setLayoutManager(new LinearLayoutManager(this));
        tv_fu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FuKuanActivity.class));
            }
        });

        requestCart();
        che_aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {

                    if (che_aa.isChecked()) {
                        for (EntityBean.Cart cart : adapter.getList()) {
                            cart.isCartChecked = true;

                            for (EntityBean.Cart.Product product : cart.shoppingCartList) {
                                product.isProductChecked = true;
                            }
                        }
                    } else {
                        for (EntityBean.Cart cart : adapter.getList()) {
                            cart.isCartChecked = false;

                            for (EntityBean.Cart.Product product : cart.shoppingCartList) {
                                product.isProductChecked = false;
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                totalPrice();
            }
        });


    }

    public void totalPrice() {
        double totalPrice = 0;
        for (EntityBean.Cart cart : adapter.getList()) {
            //得到所有商品对象
            for (EntityBean.Cart.Product product : cart.shoppingCartList) {
                totalPrice += product.price;
            }
        }
        if (che_aa.isChecked()) {
            text_pr.setText("¥:" + totalPrice);
        } else {
            text_pr.setText("¥:0");
        }
    }

    private void requestCart() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder(
        ).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))


                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        CartApiService cartApiService = retrofit.create(CartApiService.class);


        cartApiService.getCarts("13485", "157853171424313485")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EntityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EntityBean entityBean) {
                        //展示数据
                        adapter = new TuiAdapter(MainActivity.this, entityBean.result);
                        recy.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}