package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ampiri.sdk.banner.InterstitialAd;

public class InterstitialActivity extends AdCallbackActivity {
    @NonNull
    private static final String AD_PLACE_ID = "00000000-0000-0000-0000-000000000000";
    @Nullable
    private InterstitialAd interstitialAd;
    @Nullable
    private Button showView;

    @NonNull
    public static Intent buildIntent(@NonNull final Context context) {
        return new Intent(context, InterstitialActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        showView = (Button) findViewById(R.id.interstitial_show);
        if (showView != null) {
            showView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (interstitialAd != null) {
                        interstitialAd.showAd();
                        showView.setEnabled(false);
                    }
                }
            });
        }
        findViewById(R.id.interstitial_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd != null) {
                    interstitialAd.loadAd();
                    showView.setEnabled(false);
                }
            }
        });
        findViewById(R.id.interstitial_show_on_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd != null) {
                    interstitialAd.loadAndShow();
                    showView.setEnabled(false);
                }
            }
        });
        findViewById(R.id.interstitial_delay_show_on_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd != null) {
                    interstitialAd.loadAndShowWithDelay();
                }
                showView.setEnabled(false);
            }
        });

        interstitialAd = new InterstitialAd(this, AD_PLACE_ID, this);
        if (interstitialAd.isReady() && showView != null) {
            showView.setEnabled(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (interstitialAd != null) {
            interstitialAd.onActivityDestroyed();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (interstitialAd != null) {
            interstitialAd.onActivityPaused();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (interstitialAd != null) {
            interstitialAd.onActivityResumed();
            if (interstitialAd.isReady() && showView != null) {
                showView.setEnabled(true);
            }
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
