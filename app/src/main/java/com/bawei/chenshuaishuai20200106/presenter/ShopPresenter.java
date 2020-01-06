package com.bawei.chenshuaishuai20200106.presenter;

import com.bawei.chenshuaishuai20200106.contract.ShopContract;
import com.bawei.chenshuaishuai20200106.model.ShopModel;
import com.bawei.chenshuaishuai20200106.model.entity.LeftEntity;

/**
 * @author: asus
 * @date: 20/1/6
 */
public class ShopPresenter implements ShopContract.IPresenter {
    private ShopContract.IView iView;
    private ShopModel model;


    public ShopPresenter(ShopContract.IView iView) {
        this.iView = iView;
        this.model = new ShopModel();
    }

    @Override
    public void gethome(String url) {
        model.getHome(url, new ShopContract.IModel.ModelCallBack() {
            @Override
            public void sussecc(LeftEntity leftEntity) {
                iView.sussecc(leftEntity);
            }

            @Override
            public void error(Throwable throwable) {
                iView.error(throwable);
            }
        });
    }
}