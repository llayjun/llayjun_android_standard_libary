package com.standard.second.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.common.widget.scalerecycleview.CardAdapter;
import com.example.common.widget.scalerecycleview.CardScaleHelper;
import com.example.common.widget.scalerecycleview.ScaleRecyclerView;
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
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

@Route(path = RouterSamePath.SECOND_FRAGMENT)
public class SecondFragment extends BaseFragment<SecondPresenter> implements ISecondPresenter.ISecondView, View.OnClickListener {

    private SquareImageView mClipIv;//裁剪后显示
    private CameraOrChooseDialog mCameraOrChooseDialog;//拍照或者选图片dialog

    private ScaleRecyclerView mScaleRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpeedView();
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
        mScaleRecyclerView = view.findViewById(R.id.speed_recycle_view);
    }

    private void initSpeedView() {
        List<String> stringList = new ArrayList<>();
        stringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174265&di=ce1257013aa4c403e32fb7c92b2bd135&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F69%2F40%2F197fc7596ea416d86b027e3b945b6e04.jpg");
        stringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174265&di=f2d128bfdfdff7b1ace0db1d21c2f07a&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F68%2F533b0f587055002fb3bf517090805189.jpg");
        stringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174265&di=75b6193505f4173fb3ec1b1363f316bb&imgtype=0&src=http%3A%2F%2Fpic.97uimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2Fb6c3d3579f6747dff7cf9bd2d0fe57ba.jpg");
        stringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523274174263&di=09adb731b3ced0c4bc0e7000562d6bce&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F69%2F40%2Fad1ec9813fa678f4ade69ab4fbaccbce.jpg");
        mScaleRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        new CardScaleHelper().attachToRecyclerView(mScaleRecyclerView);
        CardAdapter adapter = new CardAdapter(stringList);
        mScaleRecyclerView.setAdapter(adapter);
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
        GlideUtil.loadImageRoundCorner(activity, message.getBitmap(), mClipIv, ResourceUtil.getDimen(R.dimen.x20));
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
