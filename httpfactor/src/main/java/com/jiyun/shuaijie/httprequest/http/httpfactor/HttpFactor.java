package com.jiyun.shuaijie.httprequest.http.httpfactor;

import com.jiyun.shuaijie.httprequest.http.httprequest.HttpRequest;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by shuaiJie on 2017/9/21.
 */

public class HttpFactor implements HttpFactorInterface {
    /**
     * 本类对象
     */
    private static HttpFactor factor;

    /**
     * 目的是让本类成为一个单利类
     */
    private HttpFactor() {
    }

    /**
     * 使用单利创建自己的对象
     *
     * @return 返回本类对象
     */
    public static HttpFactorInterface getInstance() {
        if (factor == null) synchronized (HttpFactor.class) {
            if (factor == null) factor = new HttpFactor();
        }
        return factor;
    }

    /**
     * @param clz HttpRequest的子类的class对象
     * @param <T> 代表传递过来的类类型
     * @return 返回HttpRequest对象
     */
    @Override
    public <T extends HttpRequest> HttpRequest create(Class<T> clz) {
        try {
            /**
             * 使用反射创建HttpRequest子类的对象
             */
            return (HttpRequest) Class.forName(clz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
