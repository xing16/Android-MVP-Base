package com.xing.mvpsamle.test;

import android.content.Context;
import android.util.Log;

import com.xing.mvpsamle.base.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2018/9/15.
 */

public class MeiziPresenter extends BasePresenter<MeiziView> {

    private static final String TAG = "MeiziPresenter";
    private Context mContext;

    public MeiziPresenter(Context context) {
        mContext = context;
    }

    public void getMeiziList() {
        if (isViewAttached()) {
            ModelManager.getInstance(mContext).getMeiziList();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMeiziEvent(MeiziEvent meiziEvent) {
        Log.d(TAG, "onMeiziEvent: ");
        if (meiziEvent == null) {
            return;
        }
        if(isViewAttached()) {
            getView().onSuccess(meiziEvent.getMeiziBeanList());
        }
    }

}
