package com.bawei.chenshuaishuai20200106.view;


import androidx.recyclerview.widget.RecyclerView;

import com.bawei.chenshuaishuai20200106.R;
import com.bawei.chenshuaishuai20200106.adapter.LeftAdapter;
import com.bawei.chenshuaishuai20200106.adapter.RightAdapter;
import com.bawei.chenshuaishuai20200106.base.BaseActivity;
import com.bawei.chenshuaishuai20200106.contract.ShopContract;
import com.bawei.chenshuaishuai20200106.model.entity.LeftEntity;
import com.bawei.chenshuaishuai20200106.model.entity.RightEntity;
import com.bawei.chenshuaishuai20200106.presenter.ShopPresenter;
import com.bawei.chenshuaishuai20200106.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ShopContract.IView {

    private RecyclerView recy_left;
    private RecyclerView recy_right;
    @Override
    protected void initdata() {
        ShopPresenter shopPresenter = new ShopPresenter((ShopContract.IView) this);
        shopPresenter.gethome("http://blog.zhaoliang5156.cn/baweiapi/category");
        OkHttpUtils.getInstance().doGet("http://blog.zhaoliang5156.cn/baweiapi/shopByCategory?category=生活", new OkHttpUtils.OkHttpCallBack() {
            @Override
            public void scuuess(String string) {
                RightEntity rightEntity = new Gson().fromJson(string, RightEntity.class);
                List<RightEntity.DataBean> data = rightEntity.getData();
                //RightAdapter adapter = new RightAdapter(this, data);

            }

            @Override
            public void error(Throwable throwable) {

            }
        });




    }

    @Override
    protected void initview() {
        recy_left = (RecyclerView) findViewById(R.id.left_recy);
        recy_right = (RecyclerView) findViewById(R.id.right_recy);
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void sussecc(LeftEntity leftEntity) {
        /*List<String> category = leftEntity.getCategory();
        recy_left.setAdapter(new LeftAdapter(this,category));*/
    }

    @Override
    public void error(Throwable throwable) {

    }
}
