package com.xing.mvpsamle.test;

import android.content.Context;
import android.util.Log;

import com.xing.mvpsamle.base.BaseObserver;
import com.xing.mvpsamle.base.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class MeiziPresenter extends BasePresenter<MeiziView> {

    private static final String TAG = "MeiziPresenter";
    private Context mContext;

    public MeiziPresenter(Context context) {
        mContext = context;
    }

    public void getMeiziList(String type, int pageSize, int curPage) {
        addSubscribe(apiService.getMeiziList(type, pageSize, curPage), new BaseObserver<List<MeiziBean>>(mContext) {
            @Override
            protected void onSuccess(List<MeiziBean> data) {
                if (isViewAttached()) {
                    getView().onSuccess(data);
                }
            }
        });
    }


}
