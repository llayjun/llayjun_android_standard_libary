package com.example.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    public static BaseApplication application;
    private List<Activity> activityList;
    private Activity currentActivity;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        application = this;
        activityList = new ArrayList<>();
        //init arouter
        initARouter();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityList.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        this.currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 获取最后一个activity
     *
     * @return
     */
    public Activity getLastActivity() {
        if (activityList == null || activityList.isEmpty()) {
            return null;
        }
        return activityList.get(activityList.size() - 1);
    }
}
