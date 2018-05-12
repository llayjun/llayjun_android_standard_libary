package com.standard.first.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.RouterCommonPath;
import com.example.common.base.BaseFragment;
import com.example.common.dialog.ConfirmDialog;
import com.example.common.dialog.ConfirmDialogFragment;
import com.example.common.listener.OnNegativeClickListener;
import com.example.common.listener.OnPositiveClickListener;
import com.standard.first.R;
import com.standard.first.presenter.FirstPresenter;
import com.standard.first.presenter.IFirstPresenter;
import com.standard.llayjun.same.RouterSamePath;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

@Route(path = RouterSamePath.FIRST_FRAGMENT)
public class FirstFragment extends BaseFragment<FirstPresenter> implements IFirstPresenter.IFirstView, View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    protected void findViews(View view) {
        view.findViewById(R.id.button_scan).setOnClickListener(this);
        view.findViewById(R.id.button_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_scan) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if (aBoolean) {
                        ARouter.getInstance().build(RouterCommonPath.ROUTER_SCAN).navigation();
                    }
                }
            });
        } else if (id == R.id.button_dialog) {
            new ConfirmDialogFragment()
                    .setTitle("该项目有更新，请及时更新")
                    .setConfirmClick("确定", new OnPositiveClickListener() {
                        @Override
                        public void onPositiveClick(View view, String content) {

                        }
                    })
                    .show(getChildFragmentManager(), "");
//            new ConfirmDialog.Builder(activity)
//                    .setTitle("该项目有更新，请及时更新")
//                    .setConfirmClick("确定", new OnPositiveClickListener() {
//                        @Override
//                        public void onPositiveClick(View view, String content) {
//
//                        }
//                    })
//                    .create()
//                    .show();
        }
    }

    @Override
    protected FirstPresenter createPresenter() {
        return new FirstPresenter(this);
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
