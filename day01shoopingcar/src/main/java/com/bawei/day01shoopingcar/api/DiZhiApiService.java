package com.bawei.day01shoopingcar.api;


import com.bawei.day01shoopingcar.entity.DiZhiEntity;
import com.bawei.day01shoopingcar.entity.EntityBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DiZhiApiService {

    @GET("http://172.17.8.100/small/user/verify/v1/receiveAddressList")
    Observable<DiZhiEntity> getCarts(@Header("userId") String uid, @Header("sessionId") String sid);

}
