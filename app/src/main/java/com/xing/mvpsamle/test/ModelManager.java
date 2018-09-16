package com.xing.mvpsamle.test;

import android.content.Context;

import com.xing.mvpsamle.base.BaseObserver;
import com.xing.mvpsamle.http.RetrofitClient;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/15.
 */

public class ModelManager {

    private Context mContext;
    private static ModelManager instance;

    private ModelManager(Context context) {
        this.mContext = context;
    }

    public static ModelManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ModelManager.class) {
                if (instance == null) {
                    instance = new ModelManager(context);
                }
            }
        }
        return instance;
    }


    public void getMeiziList() {
        RetrofitClient.getInstance(mContext)
                .getApiService()
                .getMeiziList("福利", 10, 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<MeiziBean>>(mContext) {
                    @Override
                    protected void onOberverNext(List<MeiziBean> data) {
                        EventBus.getDefault().post(new MeiziEvent(data));
                    }
                });

    }


}
