package com.xing.mvpsamle.test;

import android.content.Context;
import android.content.SharedPreferences;

import butterknife.internal.Utils;

/**
 * Created by Administrator on 2018/9/15.
 */

public class SharedPreferenceHelper {

    private static final String SP_FILE_NAME = "sp_data";

    public static String get(Context context,String key,String defaultVal) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key,defaultVal);
    }
}
