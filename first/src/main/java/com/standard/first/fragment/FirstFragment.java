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
import com.example.common.adapter.ImagePageAdapter;
import com.example.common.base.BaseFragment;
import com.example.common.dialog.ConfirmDialogFragment;
import com.example.common.listener.OnPositiveClickListener;
import com.example.common.widget.banner.CircleIndicator;
import com.example.common.widget.banner.LoopViewPager;
import com.standard.first.R;
import com.standard.first.presenter.FirstPresenter;
import com.standard.first.presenter.IFirstPresenter;
import com.standard.llayjun.same.RouterSamePath;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

@Route(path = RouterSamePath.FIRST_FRAGMENT)
public class FirstFragment extends BaseFragment<FirstPresenter> implements IFirstPresenter.IFirstView, View.OnClickListener {

    private LoopViewPager loopViewPager;
    private CircleIndicator circleIndicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    protected void findViews(View view) {
        view.findViewById(R.id.button_scan).setOnClickListener(this);
        view.findViewById(R.id.button_dialog).setOnClickListener(this);
        loopViewPager = view.findViewById(R.id.loop_view_pager);
        circleIndicator = view.findViewById(R.id.circle_indicator);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
    }

    private void initBanner() {
        List<String> urls = new ArrayList<>();
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174265&di=ce1257013aa4c403e32fb7c92b2bd135&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F69%2F40%2F197fc7596ea416d86b027e3b945b6e04.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174265&di=f2d128bfdfdff7b1ace0db1d21c2f07a&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F68%2F533b0f587055002fb3bf517090805189.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174265&di=75b6193505f4173fb3ec1b1363f316bb&imgtype=0&src=http%3A%2F%2Fpic.97uimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2Fb6c3d3579f6747dff7cf9bd2d0fe57ba.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174263&di=09adb731b3ced0c4bc0e7000562d6bce&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F69%2F40%2Fad1ec9813fa678f4ade69ab4fbaccbce.jpg");
        loopViewPager.setAdapter(new ImagePageAdapter(urls));
        loopViewPager.setLooperPic(true);
        circleIndicator.setViewPager(loopViewPager);
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
