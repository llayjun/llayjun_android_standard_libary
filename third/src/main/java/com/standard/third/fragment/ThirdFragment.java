package com.standard.third.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.base.BaseFragment;
import com.standard.llayjun.same.RouterSamePath;
import com.standard.third.R;
import com.standard.third.presenter.IThirdPresenter;
import com.standard.third.presenter.ThirdPresenter;

@Route(path = RouterSamePath.THIRD_FRAGMENT)
public class ThirdFragment extends BaseFragment<ThirdPresenter> implements IThirdPresenter.IThirdView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    protected void findViews(View view) {

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
