package com.ampiri.demo;

import android.support.v4.app.FragmentActivity;

import com.ampiri.sdk.Ampiri;

public abstract class AdLifecycleActivity extends FragmentActivity {

    @Override
    protected void onResume() {
        super.onResume();
        Ampiri.onActivityResumed(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Ampiri.onActivityPaused(this);
    }
}
