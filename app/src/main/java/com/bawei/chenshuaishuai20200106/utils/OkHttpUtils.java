package com.bawei.chenshuaishuai20200106.utils;


import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author: asus
 * @date: 20/1/6
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler= new Handler();
    private OkHttpUtils(){
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }
    public void doGet(String url,OkHttpCallBack okHttpCallBack){
        Request request=new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (okHttpCallBack!=null){
                    okHttpCallBack.error(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();
                if (okHttpCallBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallBack.scuuess(string);
                        }
                    });

                }
            }
        });

    }
    public interface OkHttpCallBack{
        void scuuess(String string);
        void error(Throwable throwable);
    }
}
