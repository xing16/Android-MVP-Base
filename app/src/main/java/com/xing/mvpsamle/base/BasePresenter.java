package com.xing.mvpsamle.base;

import com.xing.mvpsamle.http.ApiService;
import com.xing.mvpsamle.http.RetrofitClient;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/15.
 */

public abstract class BasePresenter<V extends BaseView> {

    protected V view;
    // 管理订阅关系，用于取消订阅
    private CompositeDisposable compositeDisposable;

    protected ApiService apiService = RetrofitClient.getInstance().getApiService();

    public BasePresenter() {
        EventBus.getDefault().register(this);
    }


    /**
     * 绑定 View ，一般在初始化时调用
     *
     * @param view
     */
    public void attachView(V view) {
        this.view = view;
    }

    /**
     * 解除绑定 View,一般在 onDestroy 中调用
     */
    public void detachView() {
        this.view = null;
        unsubscribe();
    }

    /**
     * 是否绑定了View,一般在发起网络请求之前调用
     *
     * @return
     */
    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }


    /**
     * 添加新的订阅关系到管理类中
     */
    public void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }

    /**
     * 取消订阅
     */
    public void unsubscribe() {
        if (compositeDisposable == null) {
            compositeDisposable.dispose();
        }
    }

}
