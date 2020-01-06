package com.bawei.chenshuaishuai20200106.api;

import com.bawei.chenshuaishuai20200106.model.entity.LeftEntity;
import com.bawei.chenshuaishuai20200106.model.entity.RightEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author: asus
 * @date: 20/1/6
 */
public interface ApiService {
    @GET("http://blog.zhaoliang5156.cn/baweiapi/category")
    Observable<LeftEntity> getCls();


    @GET("http://blog.zhaoliang5156.cn/baweiapi/shopByCategory?category=生活")
    Observable<RightEntity> getRight(@QueryMap HashMap<String, String> params);
}
