package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ampiri.sdk.banner.VideoAd;

public class VideoActivity extends AdCallbackActivity {
    @NonNull
    private static final String AD_PLACE_ID = "032c0809-0335-4e98-8f8c-8e522f291d0f";
    @Nullable
    private VideoAd videoAd;
    @Nullable
    private Button showView;

    @NonNull
    public static Intent buildIntent(@NonNull final Context context) {
        return new Intent(context, VideoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        showView = (Button) findViewById(R.id.video_show);
        if (showView != null) {
            showView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (videoAd != null) {
                        videoAd.showAd();
                    }
                    showView.setEnabled(false);
                }
            });
        }
        findViewById(R.id.video_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoAd != null) {
                    videoAd.loadAd();
                }
                showView.setEnabled(false);
            }
        });
        findViewById(R.id.video_show_on_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoAd != null) {
                    videoAd.loadAndShow();
                }
                showView.setEnabled(false);
            }
        });

        videoAd = new VideoAd(this, AD_PLACE_ID, this);
        if (videoAd.isReady() && showView != null) {
            showView.setEnabled(true);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoAd != null) {
            videoAd.onActivityResumed();
            if (videoAd.isReady() && showView != null) {
                showView.setEnabled(true);
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (videoAd != null) {
            videoAd.onActivityPaused();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (videoAd != null) {
            videoAd.onActivityDestroyed();
        }
    }

    @Override
    public void onAdLoaded() {
        super.onAdLoaded();
        if (showView != null) {
            showView.setEnabled(true);
        }
    }

    @Override
    public void onAdClosed() {
        super.onAdClosed();
        if (showView != null) {
            showView.setEnabled(false);
        }
    }
}
