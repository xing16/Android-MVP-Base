package com.xing.mvpsamle.http;

import android.content.Context;
import android.net.ParseException;
import android.widget.Toast;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * Created by Administrator on 2018/9/15.
 */

public class ExceptionHandler {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static void handleException(Context context, Throwable e) {
        String errmsg;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    errmsg = "网络错误";
                    break;
            }
            errmsg = errmsg + ":" + httpException.code();
        } else if (e instanceof ApiException) {
            ApiException exception = ((ApiException) e);
            errmsg = exception.getErrmsg();
            // 服务端返回的错误码：40001=token失效，重新登录; 40002=账号在其他设备上登录，40003=密码错误
            int errcode = exception.getErrcode();
            // 根据业务逻辑处理异常信息，如：token失效，跳转至登录界面
            handleServerException(context, errcode);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            errmsg = "解析错误";
        } else if (e instanceof ConnectException) {
            errmsg = "连接失败";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            errmsg = "证书验证失败";
        } else if (e instanceof ConnectTimeoutException) {
            errmsg = "连接超时";
        } else if (e instanceof java.net.SocketTimeoutException) {
            errmsg = "连接超时";
        } else {
            errmsg = "未知错误";
        }
        Toast.makeText(context, errmsg, Toast.LENGTH_LONG).show();
    }

    private static final int BIZ_TO_LOGIN = 40001;

    /**
     * 根据业务逻辑处理异常信息
     */
    private static void handleServerException(Context context, int errcode) {
        switch (errcode) {
            case BIZ_TO_LOGIN:
                gotoLoginPage(context);
                break;
            default:
                break;
        }
    }

    /**
     * 跳转至登录界面
     *
     * @param context
     */
    private static void gotoLoginPage(Context context) {

    }
}
