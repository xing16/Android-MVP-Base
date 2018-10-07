package com.xing.mvpsamle.http;

import android.content.Context;

import java.io.IOException;

import butterknife.internal.Utils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/9/15.
 */

public class InterceptorUtils {


    public static Interceptor headerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
//                String token = SharedPreferenceHelper.get(context, "token", "");
                String token = "";
                Request originalRequest = chain.request();
                Request request = originalRequest.newBuilder()
                        .addHeader("os", "android")
                        .addHeader("token", token)
                        .addHeader("device", "")
                        .build();
                return chain.proceed(request);
            }
        };
    }

    public static Interceptor logInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        };
    }
}
