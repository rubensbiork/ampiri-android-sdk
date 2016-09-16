package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.ampiri.sdk.banner.NativeAd;
import com.ampiri.sdk.banner.NativeAdView;
import com.ampiri.sdk.banner.VideoCardNativeAdView;

public class VideoNativeActivity extends AdCallbackActivity {
    @NonNull
    private static final String AD_UNIT_ID = "e5cc8e6d-d674-402a-aeca-eda7856bd7af";
    @Nullable
    private FrameLayout adContainerView;
    @Nullable
    private NativeAd nativeAd;

    @NonNull
    public static Intent buildIntent(@NonNull final Context context) {
        return new Intent(context, VideoNativeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);

        adContainerView = (FrameLayout) findViewById(R.id.ad_container);

        nativeAd = new NativeAd.Builder()
                .setAdUnitId(AD_UNIT_ID)
                .setAdViewBuilder(VideoCardNativeAdView.BUILDER)
                .setAdViewAttributes(new NativeAdView.Attributes()
                        .setAdAttributionText(R.string.ad_attribution_text)
                        .setDefaultCallToActionText(R.string.call_to_action_text))
                .setCallback(this)
                .build(this);
        nativeAd.loadAd();
    }

    @Override
    public void onAdLoaded() {
        if (adContainerView != null) {
            adContainerView.removeAllViews();
            if (nativeAd != null) {
                adContainerView.addView(nativeAd.renderAdView());
            }
            adContainerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (nativeAd != null) {
            nativeAd.onActivityResumed();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (nativeAd != null) {
            nativeAd.onActivityPaused();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (nativeAd != null) {
            nativeAd.onActivityDestroyed();
        }
    }
}
