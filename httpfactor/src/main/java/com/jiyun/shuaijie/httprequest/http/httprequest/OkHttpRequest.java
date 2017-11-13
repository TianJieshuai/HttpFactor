package com.jiyun.shuaijie.httprequest.http.httprequest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.jiyun.shuaijie.httprequest.http.httpapis.CallBack;
import com.jiyun.shuaijie.httprequest.http.httputils.OkHttpUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static com.jiyun.shuaijie.httprequest.utils.GsonUtils.fromJson;
import static com.jiyun.shuaijie.httprequest.utils.InitMap.initOkHttpMap;
import static com.jiyun.shuaijie.httprequest.utils.InitMap.initStringMap;
import static com.jiyun.shuaijie.httprequest.utils.TypeUtils.getType;

/**
 * Created by shuaiJie on 2017/9/22.
 */

public class OkHttpRequest<T> implements HttpRequest<T> {

    private CallBack<T> callBack;
    public static final int SUCCESS = 1;
    public static final int FAILURE = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    callBack.onSuccess((T) msg.obj);
                    break;
                case FAILURE:
                    callBack.onFailure((Throwable) msg.obj);
                    break;
            }
        }
    };

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
        /*
         * callBack 提升为成员变量以便在Handler中调用
         */
        this.callBack = callBack;

        /*
         * 创建OkHttp3的Request对象
         */
        Request build = new Request.Builder().url(url + initStringMap(map)).build();

        /*
         * 使用OkHttpUtils单利类提供的newCall返回Call对象
         */
        Call call = OkHttpUtils.getInstance().newCall(build);

        /*
         * 使用Call对象发起同步或异步网络请求
         */
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                /*
                 * 因为OkHttp的请求在异步线程所以需要使用Handler发送到主线程
                 */
                Message msg = handler.obtainMessage(FAILURE);
                msg.obj = e;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                T o = fromJson(string, getType(callBack));

                /*
                 * 因为OkHttp的请求在异步线程所以需要使用Handler发送到主线程
                 */
                Message msg = handler.obtainMessage(SUCCESS);
                msg.obj = o;
                handler.sendMessage(msg);
            }
        });
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
        /*
         * callBack 提升为成员变量以便在Handler中调用
         */
        this.callBack = callBack;

        /*
         * 创建OkHttp3的Request对象
         */
        Request build = new Request.Builder().url(url).build();

        /*
         * 使用OkHttpUtils单利类提供的newCall返回Call对象
         */
        Call call = OkHttpUtils.getInstance().newCall(build);

        /*
         * 使用Call对象发起同步或异步网络请求
         */
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                /*
                 * 因为OkHttp的请求在异步线程所以需要使用Handler发送到主线程
                 */
                Message msg = handler.obtainMessage(FAILURE);
                msg.obj = e;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                T o = fromJson(string, getType(callBack));

                /*
                 * 因为OkHttp的请求在异步线程所以需要使用Handler发送到主线程
                 */
                Message msg = handler.obtainMessage(SUCCESS);
                msg.obj = o;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * @param context  上下文
     * @param url      网路地址
     * @param map      提交的键值对
     * @param callBack 回调监听
     */
    @Override
    public void sendPost(final Context context, String url, Map<String, String> map, final CallBack<T> callBack) {
        if (TextUtils.isEmpty(url) || map == null || map.size() == 0) {
            new NullPointerException("url=\"" + url + "\" map = null Or map.size() = 0");
        }
        /*
         * callBack 提升为成员变量以便在Handler中调用
         */
        this.callBack = callBack;

        /*
         * 创建OkHttp3的Request对象
         */
        Request build = new Request.Builder().url(url).post(initOkHttpMap(map)).build();

        /*
         * 使用OkHttpUtils单利类提供的newCall返回Call对象
         */
        Call call = OkHttpUtils.getInstance().newCall(build);

        /*
         * 使用Call对象发起同步或异步网络请求
         */
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                /*
                 * 因为OkHttp的请求在异步线程所以需要使用Handler发送到主线程
                 */
                Message msg = handler.obtainMessage(FAILURE);
                msg.obj = e;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                T o = fromJson(string, getType(callBack));

                /*
                 * 因为OkHttp的请求在异步线程所以需要使用Handler发送到主线程
                 */
                Message msg = handler.obtainMessage(SUCCESS);
                msg.obj = o;
                handler.sendMessage(msg);
            }
        });
    }

}
