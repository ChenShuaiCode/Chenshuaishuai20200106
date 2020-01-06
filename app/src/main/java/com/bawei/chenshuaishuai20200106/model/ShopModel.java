package com.bawei.chenshuaishuai20200106.model;

import com.bawei.chenshuaishuai20200106.contract.ShopContract;
import com.bawei.chenshuaishuai20200106.model.entity.LeftEntity;
import com.bawei.chenshuaishuai20200106.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author: asus
 * @date: 20/1/6
 */
public class ShopModel implements ShopContract.IModel {
    @Override
    public void getHome(String url, ModelCallBack modelCallBack) {
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.OkHttpCallBack() {
            @Override
            public void scuuess(String string) {
                LeftEntity leftEntity = new Gson().fromJson(string, LeftEntity.class);
                modelCallBack.sussecc(leftEntity);
            }

            @Override
            public void error(Throwable throwable) {
                modelCallBack.error(throwable);

            }
        });

    }
}
