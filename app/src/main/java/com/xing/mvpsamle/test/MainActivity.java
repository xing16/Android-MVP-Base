package com.xing.mvpsamle.test;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xing.mvpsamle.R;
import com.xing.mvpsamle.base.BaseMVPActivity;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity<MeiziView, MeiziPresenter> implements MeiziView {

    @BindView(R.id.tv_result)
    TextView resultTxtView;

    @BindView(R.id.pb_loading)
    ProgressBar loadingProgressBar;


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
        presenter.getMeiziList("福利", 20, 1);
    }

    @Override
    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<MeiziBean> data) {
        Toast.makeText(this, data.get(0).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {

    }
}
