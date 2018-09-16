package com.xing.mvpsamle.base;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/9/15.
 */

public abstract class BasePresenter<V extends BaseView> {

    protected V view;
    // 管理订阅关系，用于取消订阅
    private CompositeDisposable compositeDisposable;

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
     *
     * @param disposable
     */
    public void addSubscribe(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    /**
     * 取消订阅
     */
    public void unsubscribe() {
        compositeDisposable.clear();
    }

}
