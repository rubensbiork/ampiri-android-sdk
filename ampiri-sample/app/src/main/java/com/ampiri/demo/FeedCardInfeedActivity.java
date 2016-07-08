package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.ampiri.sdk.banner.FeedCardNativeAdView;
import com.ampiri.sdk.banner.NativeAdView;

public class FeedCardInfeedActivity extends InfeedActivity {

    @NonNull
    public static Intent buildIntent(@NonNull final Context context) {
        return new Intent(context, FeedCardInfeedActivity.class);
    }

    @NonNull
    protected NativeAdView.Builder getNativeAdViewBuilder() {
        return FeedCardNativeAdView.BUILDER;
    }
}
