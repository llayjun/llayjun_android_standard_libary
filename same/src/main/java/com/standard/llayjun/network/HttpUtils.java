package com.standard.llayjun.network;


import com.example.common.BuildConfig;
import com.example.common.network.retrofit.HttpUtil;
import com.standard.llayjun.ApiService;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * 网络请求工具类
 */
public class HttpUtils {
    private static ApiService mApiService;

    private static void checkNotNull() {
        if (mApiService == null) {
            HttpUtil.getInstance().setBaseUrl(BuildConfig.http);
            mApiService = HttpUtil.getInstance().createService(ApiService.class);
        }
    }

    public static ApiService getApi() {
        checkNotNull();
        return mApiService;
    }

    /**
     * 订阅 Api 请求
     *
     * @param observable 被观察者
     * @param observer   观察者
     */
    public static void subscribeApi(Observable observable, DisposableObserver observer) {
        HttpUtil.getInstance().subscribeApi(observable, observer);
    }

    /**
     * 清除所有订阅关系
     */
    public static void clear() {
        HttpUtil.getInstance().clear();
    }
}
