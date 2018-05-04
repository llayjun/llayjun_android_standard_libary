package com.standard.second.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.base.BaseFragment;
import com.standard.llayjun.same.RouterSamePath;
import com.standard.second.R;
import com.standard.second.presenter.ISecondPresenter;
import com.standard.second.presenter.SecondPresenter;

@Route(path = RouterSamePath.SECOND_FRAGMENT)
public class SecondFragment extends BaseFragment<SecondPresenter> implements ISecondPresenter.ISecondView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    protected void findViews(View view) {

    }

    @Override
    protected SecondPresenter createPresenter() {
        return new SecondPresenter(this);
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
