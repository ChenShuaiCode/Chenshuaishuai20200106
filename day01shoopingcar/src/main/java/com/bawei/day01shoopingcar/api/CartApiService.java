package com.bawei.day01shoopingcar.api;


import com.bawei.day01shoopingcar.entity.DiZhiEntity;
import com.bawei.day01shoopingcar.entity.EntityBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CartApiService {

    @GET("small/order/verify/v1/findShoppingCart")
    Observable<EntityBean> getCarts(@Header("userId") String uid, @Header("sessionId") String sid);

}
