package com.bawei.day01shoopingcar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.WindowContentFrameStats;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.day01shoopingcar.api.Api;
import com.bawei.day01shoopingcar.api.UserApiService;
import com.bawei.day01shoopingcar.entity.UserEntity;
import com.blankj.utilcode.util.EncryptUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class LoginActivity extends AppCompatActivity {

    private EditText ed_phone;
    private EditText ed_pwd;
    private Button btn_deng;
    private Button btn_zhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        btn_deng = (Button) findViewById(R.id.btn_deng);
        btn_zhu = (Button) findViewById(R.id.btn_zhu);
        //注册
        btn_zhu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserApiService userApiService = retrofit.create(UserApiService.class);
                String pwd = EncryptUtils.encryptMD5ToString(ed_pwd.getText().toString()).substring(0, 8);
                userApiService.reg(ed_phone.getText().toString(), pwd)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<UserEntity>() {
                            @Override
                            public void accept(UserEntity userEntity) throws Exception {
                                Toast.makeText(LoginActivity.this, userEntity.message, Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
            }
        });
        //登录
        btn_deng.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()

                        .baseUrl(Api.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserApiService userApiService = retrofit.create(UserApiService.class);
                String pwd = EncryptUtils.encryptMD5ToString(ed_pwd.getText().toString()).substring(0,8);
                System.out.println("pwd==="+pwd);

                userApiService.login(ed_phone.getText().toString(),pwd)


                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe(new Consumer<UserEntity>() {
                            @Override
                            public void accept(UserEntity userEntity) throws Exception {
                                Toast.makeText(LoginActivity.this, userEntity.message, Toast.LENGTH_SHORT).show();
                                if ("0000".equals(userEntity.status)){
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    LoginActivity.this.finish();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
            }
        });
    }
}