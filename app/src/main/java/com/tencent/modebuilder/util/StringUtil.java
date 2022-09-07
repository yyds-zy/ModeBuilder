package com.tencent.modebuilder.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/7
 * Describe:
 */
public class StringUtil {

    public static final String TAG = "StringUtil";
    /**
     * String转String[]
     * @param data
     * @return
     */
    public static String[] getStringArray(String data) {
        List<String> list = new ArrayList<>();
        list.clear();
        int size = 0;
        if (data == null) return null;
        String trim = data.trim();
        if (trim.substring(0,1).equals("{") && trim.substring(trim.length()-1,trim.length()).equals("}")) {
            String substring = trim.substring(1, data.length() - 1);
            String[] split = substring.trim().split(",");
            for (int i = 0; i < split.length; i++) {
                String trim_str = split[i].trim();
                String indexStr = trim_str.substring(trim_str.indexOf("\"")+1,trim_str.indexOf("\"")+trim_str.length()-1);
                Log.d(TAG, indexStr);
                list.add(indexStr);
            }
            size = list.size();
        }
        String[] result = list.toArray(new String[size]);
        return result;
    }

    /**
     * 检查数据格式
     * @param data
     * @return
     */
    public static boolean getDataFormat(String data) {
        if (data == null) return false;
        String trim = data.trim();
        if (trim.substring(0,1).equals("{") && trim.substring(trim.length()-1,trim.length()).equals("}")) {
            return true;
        } else {
            return false;
        }
    }
}
