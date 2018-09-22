package com.xing.mvpsamle.test;

import android.widget.Toast;

import com.xing.mvpsamle.R;
import com.xing.mvpsamle.base.BaseMVPActivity;

import java.util.List;

public class MainActivity extends BaseMVPActivity<MeiziPresenter> implements MeiziView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected MeiziPresenter createPresenter() {
        return new MeiziPresenter(mContext);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.attachView(this);
        presenter.getMeiziList();
    }

    @Override
    public void onSuccess(List<MeiziBean> data) {
        Toast.makeText(this, data.get(0).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {

    }
}
