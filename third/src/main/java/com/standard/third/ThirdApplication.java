package com.standard.third;

import com.example.common.base.ApplicationImpl;
import com.example.common.base.BaseApplication;
import com.tongju.push.PushApplication;

public class ThirdApplication implements ApplicationImpl {

    @Override
    public void onCreate(BaseApplication baseApplication) {
        PushApplication pushApplication = new PushApplication();
        pushApplication.onCreate(baseApplication);
    }

}
