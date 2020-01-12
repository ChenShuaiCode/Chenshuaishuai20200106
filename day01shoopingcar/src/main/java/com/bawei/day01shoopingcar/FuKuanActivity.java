package com.bawei.day01shoopingcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.day01shoopingcar.adapter.DiZhiAdapter;
import com.bawei.day01shoopingcar.adapter.NeiAdapter;
import com.bawei.day01shoopingcar.adapter.TuiAdapter;
import com.bawei.day01shoopingcar.api.Api;
import com.bawei.day01shoopingcar.api.CartApiService;
import com.bawei.day01shoopingcar.api.DiZhiApiService;
import com.bawei.day01shoopingcar.entity.DiZhiEntity;
import com.bawei.day01shoopingcar.entity.EntityBean;
import com.bawei.day01shoopingcar.utils.OkHttpUtils;
import com.google.gson.Gson;

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

public class FuKuanActivity extends AppCompatActivity {

    private TextView tt_vv;
    private RecyclerView recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_kuan);
        tt_vv = (TextView) findViewById(R.id.tt);
        recy = (RecyclerView) findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
        requestCart();
        tt_vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FuKuanActivity.this,DingActivity.class));
            }
        });
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

        DiZhiApiService cartApiService = retrofit.create(DiZhiApiService.class);

        cartApiService.getCarts("13485", "157853171424313485")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiZhiEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(DiZhiEntity diZhiEntity) {
                        //展示数据
                        DiZhiAdapter diZhiAdapter = new DiZhiAdapter(FuKuanActivity.this, diZhiEntity.getResult());
                        recy.setAdapter(diZhiAdapter);
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
