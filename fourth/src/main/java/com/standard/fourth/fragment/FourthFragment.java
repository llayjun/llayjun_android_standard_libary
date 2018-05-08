package com.standard.fourth.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.base.BaseFragment;
import com.standard.fourth.R;
import com.standard.fourth.presenter.FourthPresenter;
import com.standard.fourth.presenter.IFourthPresenter;
import com.standard.llayjun.same.RouterSamePath;
import com.tongju.pay.aliay.AlipayUtil;

@Route(path = RouterSamePath.FOURTH_FRAGMENT)
public class FourthFragment extends BaseFragment<FourthPresenter> implements IFourthPresenter.IFourthView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    protected void findViews(View view) {
        view.findViewById(R.id.button_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlipayUtil.alipayUtil(activity, AlipayUtil.getAlipayInfo(), new AlipayUtil.OnAlipayCallBack() {
                    @Override
                    public void onAlipayCallBackSuccess(String pay) {
                        Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAlipayCallBackFailure(String pay) {
                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
