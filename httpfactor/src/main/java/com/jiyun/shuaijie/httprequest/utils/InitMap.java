package com.jiyun.shuaijie.httprequest.utils;

import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by shuaiJie on 2017/9/22.
 */

public class InitMap {
    /**
     * @param map 含有键值对的map集合
     * @return 返回拼接的好的GET请求体
     */
    public static String initStringMap(Map<String, String> map) {
        if (map==null){
            return "";
        }
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        StringBuffer buffer = new StringBuffer("?");
        for (int x = 0; iterator.hasNext(); x++) {
            Map.Entry<String, String> next = iterator.next();
            if (x == 0) {
                buffer.append(next.getKey() + "=" + next.getValue());
            } else {
                buffer.append("&" + next.getKey() + "=" + next.getValue());
            }
        }
        return buffer.toString();
    }

    public static FormBody initOkHttpMap(Map<String, String> map) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map==null){
            return builder.build();
        }
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            builder.add(next.getKey(), next.getValue());
        }
        return builder.build();
    }
}
