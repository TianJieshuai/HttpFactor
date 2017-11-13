package com.jiyun.shuaijie.httprequest.http.httprequest;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.jiyun.shuaijie.httprequest.http.httpapis.CallBack;
import com.jiyun.shuaijie.httprequest.http.httputils.VolleyUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.jiyun.shuaijie.httprequest.utils.GsonUtils.fromJson;
import static com.jiyun.shuaijie.httprequest.utils.InitMap.initStringMap;
import static com.jiyun.shuaijie.httprequest.utils.TypeUtils.getType;

/**
 * Created by shuaiJie on 2017/9/21.
 */

public class VolleyHttpRequest<T> implements HttpRequest<T> {

    /**
     * 如果服务器请求头中不包含charset信息使用的编码
     */
    private static String charset = "UTF-8";

    public VolleyHttpRequest setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    /**
     * @param context  上下文
     * @param url      网路地址
     * @param map      提交的键值对
     * @param callBack 回调监听
     */
    @Override
    public void sendGet(final Context context, String url, Map<String, String> map, final CallBack<T> callBack) {
        if (TextUtils.isEmpty(url)) {
            new NullPointerException("url=\"" + url + "\"");
        }

        /**
         * 创建Volley的StringRequest对象
         */
        StringRequest stringRequest = new StringRequest(url + initStringMap(map), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T o = fromJson(response, getType(callBack));
                callBack.onSuccess(o);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailure(new Throwable(error));
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                String parsed = null;
                try {
                    parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                } catch (UnsupportedEncodingException e) {
                    try {
                        parsed = new String(response.data, charset);
                    } catch (UnsupportedEncodingException e1) {
                        callBack.onFailure(e1);
                    }
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        /**
         * 使用VolleyUtils将StringRequest对象加入到网络队列
         */
        VolleyUtils.getInstance(context).add(stringRequest);
    }

    /**
     * 无键值对Get请求
     *
     * @param context  上下文
     * @param url      网路地址
     * @param callBack 回调监听
     */
    @Override
    public void sendGet(Context context, String url, final CallBack<T> callBack) {
        if (TextUtils.isEmpty(url)) {
            new NullPointerException("url=\"" + url + "\"");
        }

        /**
         * 创建Volley的StringRequest对象
         */
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T o = fromJson(response, getType(callBack));
                callBack.onSuccess(o);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailure(new Throwable(error));
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                String parsed = null;
                try {
                    parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                } catch (UnsupportedEncodingException e) {
                    try {
                        parsed = new String(response.data, charset);
                    } catch (UnsupportedEncodingException e1) {
                        callBack.onFailure(e1);
                    }
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        /**
         * 使用VolleyUtils将StringRequest对象加入到网络队列
         */
        VolleyUtils.getInstance(context).add(stringRequest);
    }

    /**
     * @param context  上下文
     * @param url      网路地址
     * @param map      提交的键值对
     * @param callBack 回调监听
     */
    @Override
    public void sendPost(final Context context, String url, final Map<String, String> map, final CallBack<T> callBack) {
        if (TextUtils.isEmpty(url) || map == null || map.size() == 0) {
            new NullPointerException("url=\"" + url + "\" map = null Or map.size() = 0");
        }
        /**
         * 创建Volley的StringRequest对象
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T o = fromJson(response, getType(callBack));
                callBack.onSuccess(o);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailure(new Throwable(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed = null;
                try {
                    parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                } catch (UnsupportedEncodingException e) {
                    try {
                        parsed = new String(response.data, charset);
                    } catch (UnsupportedEncodingException e1) {
                        callBack.onFailure(e1);
                    }
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        /**
         * 使用VolleyUtils将StringRequest对象加入到网络队列
         */
        VolleyUtils.getInstance(context).add(stringRequest);
    }
}
