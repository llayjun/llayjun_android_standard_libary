package com.standard.fourth.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.base.BaseFragment;

import com.standard.fourth.R;
import com.standard.fourth.presenter.FourthPresenter;
import com.standard.fourth.presenter.IFourthPresenter;
import com.standard.llayjun.same.RouterSamePath;

@Route(path = RouterSamePath.FOURTH_FRAGMENT)
public class FourthFragment extends BaseFragment<FourthPresenter> implements IFourthPresenter.IFourthView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    protected void findViews(View view) {

    }

    @Override
    protected FourthPresenter createPresenter() {
        return new FourthPresenter(this);
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
