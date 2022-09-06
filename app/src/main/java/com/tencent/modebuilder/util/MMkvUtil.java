package com.tencent.modebuilder.util;


import android.content.Context;

import com.tencent.mmkv.MMKV;

public class MMkvUtil {
    private static final String TAG = "MMkvUtil";

    public static final String PREFIX = "prefix";
    public static final String KEY_PREFIX = "prefix_key";
    public static final String SUFFIX = "suffix";
    public static final String KEY_SUFFIX = "suffix_key";
    public static final String CONTENT = "content";
    public static final String KEY_CONTENT = "content_key";
    private MMkvUtil() { }

    private static volatile MMkvUtil sInstance = null;

    public static MMkvUtil getInstance() {
        if (sInstance == null) {
            synchronized (MMkvUtil.class) {
                if (sInstance == null) {
                    sInstance = new MMkvUtil();
                }
            }
        }
        return sInstance;
    }

    public void initMmkv(Context context){
        MMKV.initialize(context);
    }

    public void EncodeStringValue(String mmapID,String key,String value){
        MMKV mmkv = MMKV.mmkvWithID(mmapID);
        mmkv.encode(key,value);
    }

    public String DecodeStringValue(String mmapID,String key){
        MMKV mmkv = MMKV.mmkvWithID(mmapID);
        return mmkv.decodeString(key);
    }

    public void EncodeBoolValue(String mmapID,String key,boolean value){
        MMKV mmkv = MMKV.mmkvWithID(mmapID);
        mmkv.encode(key,value);
    }

    public boolean DecodeBoolValue(String mmapID,String key){
        MMKV mmkv = MMKV.mmkvWithID(mmapID);
        return mmkv.decodeBool(key);
    }

    public void EncodeLongValue(String mmapID,String key,long value){
        MMKV mmkv = MMKV.mmkvWithID(mmapID);
        mmkv.encode(key,value);
    }

    public Long DecodeLongValue(String mmapID,String key){
        MMKV mmkv = MMKV.mmkvWithID(mmapID);
        return mmkv.decodeLong(key);
    }
}
