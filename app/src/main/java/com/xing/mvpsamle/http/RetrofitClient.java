package com.xing.mvpsamle.http;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Administrator on 2018/9/15.
 */

public class RetrofitClient {

    private static final String API_HOST = "https://gank.io/api/data/";
    private static final int[] certificates = {};
    private Context mContext;
    private static RetrofitClient instance;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;
    private ApiService apiService;

    private RetrofitClient(Context context) {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
//                .sslSocketFactory(HttpsFactory.getSSLSocketFactory(context, certificates))
                .addInterceptor(InterceptorUtils.logInterceptor())
                .addInterceptor(InterceptorUtils.headerInterceptor(context))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(RxGsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }


    public static RetrofitClient getInstance(Context context) {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient(context);
                }
            }
        }
        return instance;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public ApiService getApiService() {
        return apiService;
    }

}
