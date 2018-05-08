package com.standard.third.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.example.common.base.BaseFragment;
import com.standard.llayjun.same.RouterSamePath;
import com.standard.third.R;
import com.standard.third.presenter.IThirdPresenter;
import com.standard.third.presenter.ThirdPresenter;
import com.tongju.push.CustomNotificationUtil;

@Route(path = RouterSamePath.THIRD_FRAGMENT)
public class ThirdFragment extends BaseFragment<ThirdPresenter> implements IThirdPresenter.IThirdView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    protected void findViews(View view) {
        view.findViewById(R.id.button_push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliyunPush();
            }
        });
    }

    private void aliyunPush() {
        CPushMessage cPushMessage = new CPushMessage();
        cPushMessage.setTitle("阿里云推送");
        cPushMessage.setContent("阿里云推送服务");
        CustomNotificationUtil.buildNotification(activity, cPushMessage);
    }


    @Override
    protected ThirdPresenter createPresenter() {
        return new ThirdPresenter(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

}
