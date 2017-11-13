package com.jiyun.shuaijie.httprequest.http.httpfactor;

import com.jiyun.shuaijie.httprequest.http.httprequest.HttpRequest;

/**
 * Created by shuaiJie on 2017/9/21.
 */

public interface HttpFactorInterface {
    /**
     * @param clz HttpRequest的子类的class对象
     * @param <T> 代表传递过来的类类型
     * @return 返回HttpRequest对象
     */
    <T extends HttpRequest> HttpRequest create(Class<T> clz);
}
