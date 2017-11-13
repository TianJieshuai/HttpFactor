package com.jiyun.shuaijie.httprequest.http.httpapis;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by shuaiJie on 2017/9/21.
 */

/**
 * TODO 注意Retrofit的api接口不能是泛型接口,否则会发生泛型擦除
 */
public interface NewsApi {
    /**
     * @param url 使用注解替换基础地址和网络接口
     *            也可以使用@Path 填充到注解的参数,一般为网络接口的项目路径/项目名 注意是用path不能动态替换基础地址
     * @param map 需要提交的键值对
     * @return 返回被观察者
     */
    @GET
    Observable<ResponseBody> sendGet(@Url String url, @QueryMap Map<String, String> map);

    /**
     * @param url 使用注解替换基础地址和网络接口
     *            也可以使用@Path 填充到注解的参数,一般为网络接口的项目路径/项目名 注意是用path不能动态替换基础地址
     * @return 返回被观察者
     */
    @GET
    Observable<ResponseBody> sendGet(@Url String url);

    /**
     * @param url 使用注解替换基础地址和网络接口
     *            也可以使用@Path 填充到注解的参数,一般为网络接口的项目路径/项目名 注意是用path不能动态替换基础地址
     * @param map 需要提交的键值对
     * @return 返回被观察者
     */
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> sendPost(@Url String url, @FieldMap Map<String, String> map);
}
