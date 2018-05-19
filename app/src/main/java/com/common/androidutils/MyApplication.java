package com.common.androidutils;

import com.example.common.base.BaseApplication;
import com.example.common.sutils.utils.SUtils;
import com.standard.first.FirstApplication;
import com.standard.fourth.FourthApplication;
import com.standard.second.SecondApplication;
import com.standard.third.ThirdApplication;

public class MyApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        if (!Boolean.valueOf(BuildConfig.AppModule)) {
            FirstApplication firstApplication = new FirstApplication();
            firstApplication.onCreate(this);
            SecondApplication secondApplication = new SecondApplication();
            secondApplication.onCreate(this);
            ThirdApplication thirdApplication = new ThirdApplication();
            thirdApplication.onCreate(this);
            FourthApplication fourthApplication = new FourthApplication();
            fourthApplication.onCreate(this);
        }
        SUtils.initialize(this);
    }

}
