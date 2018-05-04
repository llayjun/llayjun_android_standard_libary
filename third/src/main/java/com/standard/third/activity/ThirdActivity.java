package com.standard.third.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.common.base.BaseActivity;
import com.example.common.base.BasePresenter;
import com.standard.third.fragment.ThirdFragment;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {

    }

    private void addFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ThirdFragment thirdFragment = new ThirdFragment();
        transaction.replace(android.R.id.content, thirdFragment);
        transaction.commit();
    }

}
