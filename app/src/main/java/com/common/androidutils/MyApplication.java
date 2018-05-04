package com.common.androidutils;

import com.example.common.base.BaseApplication;
import com.standard.first.FirstApplication;

public class MyApplication extends BaseApplication {

    FirstApplication firstApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        firstApplication = new FirstApplication();
        firstApplication.onCreate(this);
    }

}
