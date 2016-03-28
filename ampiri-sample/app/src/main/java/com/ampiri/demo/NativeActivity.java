package com.ampiri.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ampiri.sdk.banner.NativeAd;
import com.ampiri.sdk.mediation.NativeAdData;
import com.ampiri.sdk.mediation.NativeAdRenderer;
import com.ampiri.sdk.mediation.NativeAdViewAssets;
import com.ampiri.sdk.utils.AmpiriLogger;
import com.squareup.picasso.Picasso;

public class NativeActivity extends AdCallbackActivity {
    @NonNull
    private static final String AD_PLACE_ID = "00000000-0000-0000-0000-000000000007";
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

        nativeAd = new NativeAd(this, AD_PLACE_ID, new NativeAdRenderer() {
            @Override
            public NativeAdViewAssets renderAdView(@NonNull final View view, @NonNull final NativeAdData.AdData adData) {
                final ImageView iconView = (ImageView) view.findViewById(R.id.native_ad_icon_url);
                if (iconView != null) {
                    if (!TextUtils.isEmpty(adData.iconUrl)) {
                        iconView.setVisibility(View.VISIBLE);
                        Picasso.with(NativeActivity.this).load(adData.iconUrl).into(iconView);
                    } else {
                        iconView.setVisibility(View.INVISIBLE);
                        Picasso.with(NativeActivity.this).cancelRequest(iconView);
                    }
                }
                final ImageView imageView = (ImageView) view.findViewById(R.id.native_ad_image_url);
                if (imageView != null) {
                    if (!TextUtils.isEmpty(adData.imageUrl)) {
                        imageView.setVisibility(View.VISIBLE);
                        AmpiriLogger.debug("Image URL" + adData.imageUrl);
                        Picasso.with(NativeActivity.this).load(adData.imageUrl).into(imageView);
                    } else {
                        imageView.setVisibility(View.INVISIBLE);
                        Picasso.with(NativeActivity.this).cancelRequest(imageView);
                    }
                }
                final TextView textView = (TextView) view.findViewById(R.id.native_ad_text);
                if (textView != null) {
                    textView.setText(adData.text);
                    textView.setVisibility(TextUtils.isEmpty(adData.text) ? View.INVISIBLE : View.VISIBLE);
                }
                final TextView titleView = (TextView) view.findViewById(R.id.native_ad_title);
                if (titleView != null) {
                    titleView.setText(adData.title);
                    titleView.setVisibility(TextUtils.isEmpty(adData.title) ? View.INVISIBLE : View.VISIBLE);
                }
                final TextView callToActionView = (TextView) view.findViewById(R.id.native_ad_call_to_action);
                if (callToActionView != null) {
                    callToActionView.setText(adData.callToAction);
                    callToActionView.setVisibility(TextUtils.isEmpty(adData.callToAction) ? View.INVISIBLE : View.VISIBLE);
                }
                final RatingBar starRatingView = (RatingBar) view.findViewById(R.id.native_ad_star_rating);
                if (starRatingView != null) {
                    starRatingView.setStepSize(0.1F);
                    starRatingView.setIsIndicator(true);
                    if (adData.starRating != null) {
                        starRatingView.setNumStars((int) adData.starRating.scale);
                        starRatingView.setRating((float) adData.starRating.value);
                        starRatingView.setVisibility(View.VISIBLE);
                    } else {
                        starRatingView.setVisibility(View.INVISIBLE);
                    }
                }
                final ImageView adChoiceIconView = (ImageView) view.findViewById(R.id.native_ad_choices_icon);
                if (adChoiceIconView != null) {
                    if (adData.adChoice != null && !TextUtils.isEmpty(adData.adChoice.iconUrl)) {
                        adChoiceIconView.setVisibility(View.VISIBLE);
                        adChoiceIconView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                final Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                intent.addCategory("android.intent.category.BROWSABLE");
                                intent.setData(Uri.parse(adData.adChoice.clickUrl));
                                NativeActivity.this.startActivity(intent);
                            }
                        });
                        Picasso.with(NativeActivity.this).load(adData.adChoice.iconUrl).into(adChoiceIconView);
                    } else {
                        adChoiceIconView.setVisibility(View.INVISIBLE);
                        Picasso.with(NativeActivity.this).cancelRequest(adChoiceIconView);
                    }
                }
                if (adData.adChoice != null && !TextUtils.isEmpty(adData.adChoice.iconCaption)) {
                    final TextView adChoiceCaptionView = (TextView) view.findViewById(R.id.native_ad_choices_caption);
                    if (adChoiceCaptionView != null) {
                        adChoiceCaptionView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                final Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                intent.addCategory("android.intent.category.BROWSABLE");
                                intent.setData(Uri.parse(adData.adChoice.clickUrl));
                                NativeActivity.this.startActivity(intent);
                            }
                        });
                        adChoiceCaptionView.setText(adData.adChoice.iconCaption);
                        adChoiceCaptionView.setVisibility(TextUtils.isEmpty(adData.callToAction) ? View.INVISIBLE : View.VISIBLE);
                    }
                }
                return new NativeAdViewAssets.Builder()
                        .setIconView(iconView)
                        .setImageView(imageView)
                        .setTitleView(titleView)
                        .setTextView(textView)
                        .setCallToActionView(callToActionView)
                        .setStarRatingView(starRatingView)
                        .build();
            }
        }, this);
        nativeAd.loadAd();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (nativeAd != null) {
            nativeAd.onResume();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (nativeAd != null) {
            nativeAd.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (nativeAd != null) {
            nativeAd.onDestroy();
        }
    }
}
