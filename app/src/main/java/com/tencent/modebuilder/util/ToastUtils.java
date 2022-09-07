package com.tencent.modebuilder.util;

import android.widget.Toast;

import com.tencent.modebuilder.MyApplication;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/7
 * Describe:
 */
public final class ToastUtils {

    public static void toast(int resId){
        Toast.makeText(MyApplication.application, MyApplication.application.getString(resId), Toast.LENGTH_SHORT).show();
    }
}
