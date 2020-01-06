package com.bawei.chenshuaishuai20200106.contract;

import com.bawei.chenshuaishuai20200106.model.entity.LeftEntity;

/**
 * @author: asus
 * @date: 20/1/6
 */
public interface ShopContract {
    interface IModel{
        void getHome(String url,ModelCallBack modelCallBack);
        interface ModelCallBack{
            void sussecc(LeftEntity leftEntity);
            void error(Throwable throwable);
        }
    }
    interface IView{
        void sussecc(LeftEntity leftEntity);
        void error(Throwable throwable);
    }
    interface IPresenter{
        void gethome(String url);
    }
}
