package com.bawei.day01shoopingcar.api;

import com.bawei.day01shoopingcar.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApiService {

    @POST("small/user/v1/register")
    @FormUrlEncoded
    Observable<UserEntity> reg(@Field("phone") String phone, @Field("pwd") String pwd);


    @POST("small/user/v1/login")
    @FormUrlEncoded
    Observable<UserEntity> login(@Field("phone") String phone, @Field("pwd") String pwd);


}
