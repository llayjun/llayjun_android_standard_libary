package com.example.common.network.interceptor;


import com.example.common.sutils.utils.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 离线读取本地缓存，在线获取最新数据(读取单个请求的请求头，亦可统一设置)
 */
public class OfflineCacheControlInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (!NetUtil.isConnected()) {
            originalRequest = originalRequest.newBuilder()
                    // 强制使用缓存
                    .cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response response = chain.proceed(originalRequest);
        if (NetUtil.isConnected()) {
            // 有网络的时候，读取接口上的 @Headers 里的配置，你可以在这里进行统一的设置
            String cacheControl = originalRequest.cacheControl().toString();
            return response.newBuilder()
                    // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Pragma")
                    .header("Cache-Control", cacheControl)
                    .build();
        } else {
            // 设置缓存的最大失效时间未 30 天
            int maxStale = 60 * 60 * 24 * 30;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
