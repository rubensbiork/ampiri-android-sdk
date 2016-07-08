package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.ampiri.sdk.banner.NativeAdView;
import com.ampiri.sdk.banner.VideoCardNativeAdView;

public class VideoCardInfeedActivity extends InfeedActivity {

    @NonNull
    public static Intent buildIntent(@NonNull final Context context) {
        return new Intent(context, VideoCardInfeedActivity.class);
    }

    @NonNull
    protected NativeAdView.Builder getNativeAdViewBuilder() {
        return VideoCardNativeAdView.BUILDER;
    }
}
