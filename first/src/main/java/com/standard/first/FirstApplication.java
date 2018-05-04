package com.standard.first;

import com.example.common.base.ApplicationImpl;
import com.example.common.base.BaseApplication;
import com.mob.MobSDK;

public class FirstApplication implements ApplicationImpl {

    @Override
    public void onCreate(BaseApplication baseApplication) {
        MobSDK.init(baseApplication);
    }

}
