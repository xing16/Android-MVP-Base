package com.xing.mvpsamle.http;

import com.xing.mvpsamle.base.BaseResponse;
import com.xing.mvpsamle.test.MeiziBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/9/15.
 */

public interface ApiService {

    @GET("{type}/{pageSize}/{curPage}")
    Observable<BaseResponse<List<MeiziBean>>> getMeiziList(@Path("type") String type,
                                                           @Path("pageSize") int pageSize,
                                                           @Path("curPage") int curPage);


}
