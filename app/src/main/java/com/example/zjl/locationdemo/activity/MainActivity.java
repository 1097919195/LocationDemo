package com.example.zjl.locationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.example.zjl.locationdemo.R;
import com.example.zjl.locationdemo.app.AppApplication;
import com.example.zjl.locationdemo.baiduSDK.MyLocationListener;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.LogUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.start_location)
    Button start_location;
    @BindView(R.id.stop_location)
    Button stop_location;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.display_info)
    TextView display_info;

    BDLocation location;
    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        startLocation();
    }

    private void startLocation() {
        start_location.setOnClickListener(v -> {
            AppApplication.getmLocationClient().start();
        });

        stop_location.setOnClickListener(v -> {
            AppApplication.getmLocationClient().stop();

            location = AppApplication.getmLocationClient().getLastKnownLocation();//放在start后面，防止还没有获取到BDLocation的情况
            if (location != null) {
                display_info.post(() ->
                        display_info.setText(location.getAddrStr())
                );
            }
        });

        clear.setOnClickListener(v ->
            display_info.setText("")
        );
    }
}
