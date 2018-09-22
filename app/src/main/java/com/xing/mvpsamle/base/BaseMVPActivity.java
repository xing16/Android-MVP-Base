package com.xing.mvpsamle.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity {
    protected Context mContext;
    protected P presenter;
    protected Unbinder unbinder;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mContext = this;
        presenter = createPresenter();
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract int getLayoutResId();

    protected abstract P createPresenter();

    protected void initView() {
    }

    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // butternife 取消绑定
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        // Activity 销毁时取消所有的订阅
        if (presenter != null) {
            presenter.detachView();
            presenter.unsubscribe();
            presenter = null;
        }
    }


}
