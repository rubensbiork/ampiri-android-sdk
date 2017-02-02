package com.ampiri.demo;

import android.support.multidex.MultiDexApplication;

import com.ampiri.sdk.Ampiri;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Ampiri.onApplicationCreated(this);
    }
}
