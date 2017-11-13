package com.jiyun.shuaijie.httprequest.http.httputils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by shuaiJie on 2017/9/22.
 */

public class OkHttpUtils {
    private static OkHttpUtils utils;
    private OkHttpClient client;

    private OkHttpUtils() {
        client = new OkHttpClient.Builder().build();
    }

    public static OkHttpUtils getInstance() {

        /**
         * 使用双重同步锁创建本类对象
         */
        if (utils == null) synchronized (OkHttpUtils.class) {
            if (utils == null) utils = new OkHttpUtils();
        }
        return utils;
    }

    /**
     * @param request
     * @return 调用OkHttpClient对象的newCall方法
     */
    public Call newCall(Request request) {
        return client.newCall(request);
    }
}
