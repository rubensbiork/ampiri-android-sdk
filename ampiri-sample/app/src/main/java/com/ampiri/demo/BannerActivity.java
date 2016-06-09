package com.ampiri.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.ampiri.sdk.banner.StandardAd;
import com.ampiri.sdk.mediation.BannerSize;

public abstract class BannerActivity extends AdCallbackActivity {
    @NonNull
    private static final String AD_PLACE_ID = "00000000-0000-0000-0000-000000000001";
    @Nullable
    private StandardAd standardAd;
    @Nullable
    private CheckBox autoUpdateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        final RelativeLayout bannerView = getBannerView();
        if (bannerView != null) {
            bannerView.setVisibility(View.VISIBLE);

            standardAd = new StandardAd(this, bannerView, AD_PLACE_ID, getBannerSize(), this);
            standardAd.loadAd();

            autoUpdateView = (CheckBox) findViewById(R.id.auto_update_switch);
            if (autoUpdateView != null) {
                autoUpdateView.setChecked(standardAd.isAutoRefreshEnable());
                autoUpdateView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        standardAd.setAutorefreshEnabled(isChecked);
                    }
                });
            }
        }
    }

    @Nullable
    protected abstract RelativeLayout getBannerView();

    @NonNull
    protected abstract BannerSize getBannerSize();

    @Override
    public void onResume() {
        super.onResume();
        if (standardAd != null) {
            standardAd.onActivityResumed();
            if (autoUpdateView != null) {
                autoUpdateView.setChecked(standardAd.isAutoRefreshEnable());
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (standardAd != null) {
            standardAd.onActivityPaused();
        }
    }

    @Override
    public void onDestroy() {
        if (standardAd != null) {
            standardAd.onActivityDestroyed();
        }
        super.onDestroy();
    }
}
