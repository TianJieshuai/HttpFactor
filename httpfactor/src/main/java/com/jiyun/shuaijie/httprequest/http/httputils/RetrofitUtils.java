package com.jiyun.shuaijie.httprequest.http.httputils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jiyun.shuaijie.httprequest.utils.GsonUtils.getGson;

/**
 * Created by shuaiJie on 2017/9/21.
 */

public class RetrofitUtils {
    private static RetrofitUtils utils;
    private Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.0.103";

    private RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    public static RetrofitUtils getInstance() {

        /**
         * 使用双重同步锁创建本类对象
         */
        if (utils == null) synchronized (RetrofitUtils.class) {
            if (utils == null) utils = new RetrofitUtils();
        }
        return utils;
    }

    /**
     * @param service 需要实例化的Api接口
     * @param <T>     无需理会,一般由service自身类型
     * @return 用Retrofit对象的create方法
     */
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
