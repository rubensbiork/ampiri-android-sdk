package com.ampiri.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ampiri.sdk.banner.NativeAd;
import com.ampiri.sdk.banner.StoryCardNativeAdView;

public class NativeFragment extends AdCallbackFragment {
    @NonNull
    private static final String AD_UNIT_ID = "e5cc8e6d-d674-402a-aeca-eda7856bd7af";
    @Nullable
    private FrameLayout adContainerView;
    @Nullable
    private NativeAd nativeAd;

    @NonNull
    public static Fragment newInstance() {
        return new NativeFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (adContainerView != null) {
            nativeAd = new NativeAd.Builder()
                    .setAdUnitId(AD_UNIT_ID)
                    .setAdViewBuilder(StoryCardNativeAdView.BUILDER)
                    .setCallback(this)
                    .build(getContext());
            nativeAd.loadAd();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adContainerView = (FrameLayout) inflater.inflate(R.layout.fragment_native_ad, container, false);
        return adContainerView;
    }

    @Override
    public void onAdLoaded() {
        super.onAdLoaded();
        if (adContainerView != null) {
            adContainerView.removeAllViews();
            adContainerView.setVisibility(View.VISIBLE);
            if (nativeAd != null) {
                adContainerView.addView(nativeAd.renderAdView());
            }
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
