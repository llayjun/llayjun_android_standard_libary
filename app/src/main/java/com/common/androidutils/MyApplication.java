package com.common.androidutils;

import com.example.common.base.BaseApplication;
import com.standard.second.SecondApplication;

public class MyApplication extends BaseApplication {

    SecondApplication secondApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        secondApplication = new SecondApplication();
        secondApplication.onCreate(this);
    }

}
