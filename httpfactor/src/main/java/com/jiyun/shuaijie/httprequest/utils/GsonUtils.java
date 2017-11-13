package com.jiyun.shuaijie.httprequest.utils;


import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by shuaiJie on 2017/9/28.
 */

public class GsonUtils {

    private static Gson gson;

    public static <T> T fromJson(String response, Type t) {
        return getGson().fromJson(response, t);
    }

    public static Gson getGson() {
        if (gson == null) synchronized (GsonUtils.class) {
            if (gson == null) gson = new Gson();
        }
        return gson;
    }

    public static String toJson(Object object) {
        return getGson().toJson(object);
    }
}
