package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.ampiri.sdk.banner.NativeAd;
import com.ampiri.sdk.banner.StoryCardNativeAdView;

public class NativeActivity extends AdCallbackActivity {
    @NonNull
    private static final String AD_PLACE_ID = "00000000-0000-0000-0000-000000000007";
    @Nullable
    private FrameLayout adContainerView;
    @Nullable
    private NativeAd nativeAd;

    @NonNull
    public static Intent buildIntent(@NonNull final Context context) {
        return new Intent(context, NativeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);

        adContainerView = (FrameLayout) findViewById(R.id.ad_container);

        nativeAd = new NativeAd.Builder()
                .setAdPlaceId(AD_PLACE_ID)
                .setAdViewBuilder(StoryCardNativeAdView.BUILDER)
                .setAdAttributionText(getString(R.string.ad_attribution_text))
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
