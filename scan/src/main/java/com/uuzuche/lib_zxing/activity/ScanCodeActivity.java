package com.uuzuche.lib_zxing.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.RouterCommonPath;
import com.example.common.base.BaseActivity;
import com.example.common.base.BasePresenter;
import com.uuzuche.lib_zxing.R;
import com.uuzuche.lib_zxing.fragment.CaptureFragment;
import com.uuzuche.lib_zxing.util.CodeUtils;

@Route(path = RouterCommonPath.ROUTER_SCAN)
public class ScanCodeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        CaptureFragment captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.view_scan);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {

    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Toast.makeText(ScanCodeActivity.this, result, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAnalyzeFailed() {
        }
    };


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onClick(View view) {

    }

}
