package com.example.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.base.BaseActivity;
import com.example.common.base.BaseFragment;
import com.example.main.R;
import com.example.main.presenter.IMainPresenter;
import com.example.main.presenter.MainPresenter;
import com.standard.llayjun.same.RouterSamePath;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainPresenter.IMainView {

    private BaseFragment firstFragment;
    private BaseFragment secondFragment;
    private BaseFragment thirdFragment;
    private BaseFragment fourthFragment;

    private android.widget.RadioButton rbfirst;
    private android.widget.RadioButton rbsecond;
    private android.widget.RadioButton rbthird;
    private android.widget.RadioButton rbfour;
    private android.widget.RadioButton rbfive;
    private RadioGroup rgmain;

    int nowIndex = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void findViews() {
        this.rgmain = findViewById(R.id.rg_main);
        this.rbfirst = findViewById(R.id.rb_first);
        this.rbsecond = findViewById(R.id.rb_second);
        this.rbthird = findViewById(R.id.rb_third);
        this.rbfour = findViewById(R.id.rb_four);
    }

    @Override
    protected void init() {
        firstFragment = (BaseFragment) ARouter.getInstance().build(RouterSamePath.FIRST_FRAGMENT).navigation();
        secondFragment = (BaseFragment) ARouter.getInstance().build(RouterSamePath.SECOND_FRAGMENT).navigation();
        thirdFragment = (BaseFragment) ARouter.getInstance().build(RouterSamePath.THIRD_FRAGMENT).navigation();
        fourthFragment = (BaseFragment) ARouter.getInstance().build(RouterSamePath.FOURTH_FRAGMENT).navigation();
        rgmain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_first) {
                    setChoiceItem(0);
                } else if (checkedId == R.id.rb_second) {
                    setChoiceItem(1);
                } else if (checkedId == R.id.rb_third) {
                    setChoiceItem(2);
                } else if (checkedId == R.id.rb_four) {
                    setChoiceItem(3);
                }
            }
        });
        rbfirst.setChecked(true);
    }

    public void setChoiceItem(int index) {
        if (nowIndex == index) return;
        nowIndex = index;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction, firstFragment, secondFragment, thirdFragment, fourthFragment);
        switch (index) {
            case 0:
                if (firstFragment.isAdded()) {
                    transaction.show(firstFragment);
                } else {
                    if (!firstFragment.isAdded()) {
                        transaction.add(R.id.main_content, firstFragment);
                    }
                }
                break;
            case 1:
                if (secondFragment.isAdded()) {
                    transaction.show(secondFragment);
                } else {
                    if (!secondFragment.isAdded()) {
                        transaction.add(R.id.main_content, secondFragment);
                    }
                }
                break;
            case 2:
                if (thirdFragment.isAdded()) {
                    transaction.show(thirdFragment);
                } else {
                    if (!thirdFragment.isAdded()) {
                        transaction.add(R.id.main_content, thirdFragment);
                    }
                }
                break;
            case 3:
                if (fourthFragment.isAdded()) {
                    transaction.show(fourthFragment);
                } else {
                    if (!fourthFragment.isAdded()) {
                        transaction.add(R.id.main_content, fourthFragment);
                    }
                }
                break;
        }
        transaction.commit();
        fragmentManager.executePendingTransactions();
    }

    private void hideFragments(FragmentTransaction transaction, Fragment... fragments) {
        for (Fragment fragment : fragments)
            if (fragment != null && fragment.isAdded())
                transaction.hide(fragment);
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

    //退出时的时间
    private long mExitTime;

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
