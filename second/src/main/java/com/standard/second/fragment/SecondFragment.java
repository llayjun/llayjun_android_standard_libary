package com.standard.second.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.Constant;
import com.example.common.RouterCommonPath;
import com.example.common.base.BaseFragment;
import com.example.common.dialog.CameraOrChooseDialog;
import com.example.common.sutils.utils.EventBusUtil;
import com.example.common.sutils.utils.GlideUtil;
import com.example.common.sutils.utils.ResourceUtil;
import com.example.common.widget.album.CameraUtil;
import com.example.common.widget.album.MClipImageGetBitmap;
import com.example.common.widget.view.SquareImageView;
import com.standard.llayjun.same.RouterSamePath;
import com.standard.second.R;
import com.standard.second.presenter.ISecondPresenter;
import com.standard.second.presenter.SecondPresenter;
import com.tongju.share.util.ShareUtil;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

@Route(path = RouterSamePath.SECOND_FRAGMENT)
public class SecondFragment extends BaseFragment<SecondPresenter> implements ISecondPresenter.ISecondView, View.OnClickListener {

    private SquareImageView mClipIv;//裁剪后显示
    private CameraOrChooseDialog mCameraOrChooseDialog;//拍照或者选图片dialog

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    protected void findViews(View view) {
        view.findViewById(R.id.button_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.showShare(activity);
            }
        });
        mClipIv = view.findViewById(R.id.clip_iv);
        mClipIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.clip_iv) {
            if (null == mCameraOrChooseDialog)
                mCameraOrChooseDialog = new CameraOrChooseDialog(SecondFragment.this, 1);
            mCameraOrChooseDialog.show(getChildFragmentManager(), "");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case Constant.REQUEST_CODE_CHOOSE:
                List<Uri> uriList = Matisse.obtainResult(data);
                Uri headUri = uriList.get(0);
                ARouter.getInstance().build(RouterCommonPath.ROUTER_COMMON_IMAGE_CLIP)
                        .withParcelable(Constant.EXTRA_CLIP_IMAGE, headUri)
                        .navigation();
                break;
            case Constant.REQUEST_CODE_TAKE_PHOTO:
                File file = CameraUtil.getImageFile(CameraUtil.mImageCacheName);
                Uri uri = Uri.fromFile(file);
                if (null == uri)
                    return;
                ARouter.getInstance().build(RouterCommonPath.ROUTER_COMMON_IMAGE_CLIP)
                        .withParcelable(Constant.EXTRA_CLIP_IMAGE, uri)
                        .navigation();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClipImageGetBitmapEvent(MClipImageGetBitmap message) {
        GlideUtil.loadImageRound(activity, message.getBitmap(), mClipIv, ResourceUtil.getDimen(R.dimen.x20));
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusUtil.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }

}
