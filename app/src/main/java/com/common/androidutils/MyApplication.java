package com.common.androidutils;

import com.example.common.base.BaseApplication;

public class MyApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        if (!BuildConfig.DEBUG) {
            FirstApplication firstApplication = new FirstAppication();
            firstApplication.onCreate(this);
            SecondApplication secondApplication = new SecondApplication();
            secondApplication.onCreate(this);
            ThirdApplication thirdApplication = new ThirdApplication();
            thirdApplication.onCreate(this);
            FourthApplication fourthApplication = new FourthApplication();
            fourthApplication.onCreate(this);
        }
    }

}
