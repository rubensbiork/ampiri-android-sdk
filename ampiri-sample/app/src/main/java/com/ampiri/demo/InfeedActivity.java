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
    private static final String AD_UNIT_ID = "e5cc8e6d-d674-402a-aeca-eda7856bd7af";
    @Nullable
    private StreamAdAdapter adAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeed_ad);

        final ListView listView = (ListView) findViewById(R.id.list);
        if (listView != null) {
            final Context context = this;
            final MainAdapter adapter = new MainAdapter(this);
            adAdapter = new StreamAdAdapter.Builder()
                    .setAdapter(adapter)
                    .setAdUnitId(AD_UNIT_ID)
                    .setViewBuilder(getNativeAdViewBuilder())
                    .setAdViewAttributes(new NativeAdView.Attributes()
                            .setStarRating(new NativeAdView.Attributes.Setter<NativeAdView.Attributes.StarRating>() {
                                @Override
                                public NativeAdView.Attributes.StarRating set(@NonNull NativeAdView.Attributes.StarRating starRating) {
                                    return starRating.setStarRatingStyle(NativeAdView.Attributes.StarRating.Style.MEDIUM);
                                }
                            })
                            .setAdAttribution(new NativeAdView.Attributes.Setter<NativeAdView.Attributes.AdAttribution>() {
                                @Override
                                public NativeAdView.Attributes.AdAttribution set(@NonNull NativeAdView.Attributes.AdAttribution adAttribution) {
                                    return adAttribution.setDefaultText(R.string.ad_attribution_text);
                                }
                            })
                            .setCallToAction(new NativeAdView.Attributes.Setter<NativeAdView.Attributes.CallToAction>() {
                                @Override
                                public NativeAdView.Attributes.CallToAction set(@NonNull NativeAdView.Attributes.CallToAction callToAction) {
                                    return callToAction
                                            .setDefaultText(R.string.call_to_action_text);
                                }
                            })
                    )
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
