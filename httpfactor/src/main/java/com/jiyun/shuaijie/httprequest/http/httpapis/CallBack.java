package com.jiyun.shuaijie.httprequest.http.httpapis;

/**
 * Created by shuaiJie on 2017/9/28.
 */

public interface CallBack<T> {
    /**
     * @param t 泛型,让实现类去决定类型
     */
    void onSuccess(T t);

    /**
     * @param e 错误信息
     */
    void onFailure(Throwable e);
}
