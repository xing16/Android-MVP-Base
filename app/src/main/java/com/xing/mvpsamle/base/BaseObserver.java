package com.xing.mvpsamle.base;

import android.content.Context;

import com.xing.mvpsamle.http.ExceptionHandler;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2018/9/16.
 */

public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {

    private Context mContext;
    protected Disposable disposable;

    public BaseObserver(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
        T data = baseResponse.getResults();
        // 将服务端获取到的正常数据传递给上层调用方
        onOberverNext(data);
    }

    /**
     * 回调正常数据
     *
     * @param data
     */
    protected abstract void onOberverNext(T data);


    /**
     * 异常处理，包括两方面数据：
     * (1) 服务端没有没有返回数据，HttpException，如网络异常，连接超时;
     * (2) 服务端返回了数据，但 errcode!=0,即服务端返回的data为空，如 密码错误,App登陆超时,token失效
     */
    @Override
    public void onError(Throwable e) {
        ExceptionHandler.handleException(mContext, e);
    }

    @Override
    public void onComplete() {

    }
}
