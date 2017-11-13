package com.jiyun.shuaijie.httprequest.http.httputils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shuaiJie on 2017/9/21.
 */

public class VolleyUtils {
    private static VolleyUtils utils;
    private RequestQueue queue;

    /**
     * @param context 上下文对象
     */
    private VolleyUtils(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    /**
     * @param context 上下文对象
     * @return 返回本类对象
     */
    public static VolleyUtils getInstance(Context context) {

        /**
         * 使用双重同步锁创建本类对象
         */
        if (utils == null) synchronized (VolleyUtils.class) {
            if (utils == null) utils = new VolleyUtils(context);
        }
        return utils;
    }

    /**
     * @param request 需要添加到队列的Request对象
     * @param <T>     无需理会,一般由Request自带泛型
     * @return 返回值 Request<String>
     */
    public <T> Request<T> add(Request<T> request) {
        return queue.add(request);
    }
}
