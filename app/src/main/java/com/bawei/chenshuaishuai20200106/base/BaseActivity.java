package com.bawei.chenshuaishuai20200106.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author: asus
 * @date: 20/1/6
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        ButterKnife.bind(this);
        initview();
        initdata();
    }

    protected abstract void initdata();

    protected abstract void initview();

    protected abstract int layoutid();
}
