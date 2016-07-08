package com.ampiri.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ampiri.sdk.banner.NativeAdView;
import com.ampiri.sdk.banner.StreamAdAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class InfeedActivity extends AdCallbackActivity {
    @NonNull
    private static final String AD_PLACE_ID = "e5cc8e6d-d674-402a-aeca-eda7856bd7af";
    @Nullable
    private StreamAdAdapter adAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeed);

        final ListView listView = (ListView) findViewById(R.id.list);
        if (listView != null) {
            final Context context = this;
            final MainAdapter adapter = new MainAdapter(this);
            adAdapter = new StreamAdAdapter.Builder()
                    .setAdapter(adapter)
                    .setAdPlaceId(AD_PLACE_ID)
                    .setViewBuilder(getNativeAdViewBuilder())
                    .setAdAttributionText(getString(R.string.ad_attribution_text))
                    .setEventCallback(this)
                    .build(context);
            listView.setAdapter(adAdapter);
            adAdapter.loadAd();
        }
    }

    @NonNull
    protected abstract NativeAdView.Builder getNativeAdViewBuilder();

    @Override
    public void onResume() {
        super.onResume();
        if (adAdapter != null) {
            adAdapter.onActivityResumed();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (adAdapter != null) {
            adAdapter.onActivityPaused();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adAdapter != null) {
            adAdapter.onActivityDestroyed();
        }
    }

    private static final class MainAdapter extends ArrayAdapter<String> {
        private static final int SIZE = 10;

        private MainAdapter(@NonNull final Context context) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, build(context));
        }

        @NonNull
        private static List<String> build(@NonNull final Context context) {
            final List<String> list = new ArrayList<>(SIZE);
            for (int i = 0; i < SIZE; ++i) {
                list.add(context.getString(R.string.in_feed_item, i));
            }
            return list;
        }
    }
}
