package com.xing.mvpsamle.test;

import com.xing.mvpsamle.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/9/16.
 */

public interface MeiziView extends BaseView {

    void showLoading();

    void hideLoading();

    void onSuccess(List<MeiziBean> data);

    void onFailure();
}
