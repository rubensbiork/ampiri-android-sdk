# Ampiri Android SDK 3.x Integration Guide

* [Updating your Android Manifest](#markdown-header-updating-your-android-manifest)
* [Standard banners](#markdown-header-standard-banners)
* [Interstitials](#markdown-header-interstitial-ads)
* [Video](#markdown-header-video-ads)
* [Native Ads](#markdown-header-native-ads)
* [Ad events handling](#markdown-header-ad-events-handling)
* [Activity lifecycle events handling](#markdown-header-activity-lifecycle-events-handling)
* [User Data](#markdown-header-user-data)
* [User profiling](#markdown-header-user-profiling)
* [Log](#markdown-header-log)
* [Debug mode](#markdown-header-debug-mode)
* [Test devices](#markdown-header-test-devices)

## Updating your Android Manifest

Under the main `<manifest>` element, add the following permissions.

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
```

* ACCESS_COARSE_LOCATION (recommended) - Grants the SDK permission to access approximate location based on cell tower.
* ACCESS_FINE_LOCATION (recommended) - Grants the SDK permission to access a more accurate location based on GPS.

Although not technically required, the LOCATION permissions make it possible for the SDK to send location-based data to advertisers. Sending better
location data generally leads to better monetization.

* WRITE_EXTERNAL_STORAGE (optional) - Allows the SDK to cache all ad assets (creatives, custom frames, etc.) in external memory. This can maximize 
performance by ensuring immediate delivery of ads and minimize network traffic used by the SDK by keeping cached
ad assets available even after the user closes the app.

* READ_PHONE_STATE (recommended) - Allows the SDK to handle calls interrupting video playback during videos.

* RECEIVE_BOOT_COMPLETED (recommended) - Allows the analytics engine to be able to run even if the device was rebooted.

> When using SDK as a library project, you shouldn't need to worry about merging AndroidManifest.xml changes or Proguard settings. If you run into problems, 
make sure `manifestmerger.enabled` is set to `true` in `project.properties`

## Updating your Application class

This step is only required if you extended the `android.app.Application` class. If you specified a custom class in the attribute `android:name`
for the `<application>` tag in `AndroidManifest.xml`, you should add a process verification to the beginning of that class’s `onCreate` method, like this:

```java
public class … extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (AmpiriService.isServiceProcess(this)) {
            return;
        }
        // Your custom initialization code here
    }
}
```

## Standard banners

> Attention! All SDK method calls should be done from the main thread (Main thread, UI tread).

Add a banner to layout file, e.g.:
```xml
<FrameLayout
    android:id="@+id/ad_view"
    android:layout_width="320dp"
    android:layout_height="50dp"
    android:background="@android:color/white"/>
```

It is advisable to make the banner size in the layout the same as the required one (see below). Otherwise, the banner might be displayed incorrectly.

Add the folowing code to your activity:
```java
FrameLayout adView = (FrameLayout) view.findViewById(R.id.ad_view);
StandardAd standardAd = new StandardAd(this, adView, "YOUR_STANDARD_AD_PLACE_ID", BannerSize.BANNER_SIZE_320x50, adListener);
standardAd.loadAd();
```

Banners `320*50` are served by default. Available sizes:

* 320x50
* 728x90

### Standard banner auto-update

You can switch banner auto-update on or off; to do this, call `setAutorefreshEnabled()` method, e.g.:
```java
standardAd.setAutorefreshEnabled(false);
```

By default, auto-update is switched on. Auto-update period is set up via the admin panel.

## Interstitial Ads

> Attention! All SDK method calls should be done from the main thread (Main thread, UI tread).

### Interstitial ad initialization

Add the following code to your activity:
```java
InterstitialAd interstitialAd = new InterstitialAd(this, "YOUR_INTERSTITIAL_AD_PLACE_ID", adListener);
interstitialAd.loadAd();
```
After calling the `loadAd()` method, interstitial download starts. If you call `loadAd()` again before the banner is fully served, previous request processing is cancelled. In this case, only the last request will be processed.

When banner download is completed, you can display the banner by calling `showAd()` method.
```java
interstitialAd.showAd();
```

To learn about download completion, subscribe to banner events (see [Ad events handling](#markdown-header-ad-events-handling)) or call method `isReady()`.
```java
interstitialAd.isReady();
```

If your application workflow allows to show fullscreen banner in any time and any place, there are 2 additional ways to show it right after the loading was finished or with custom delay after method invocation.

To load and show fullscreen banner right after it was loaded use:
```java
interstitialAd.loadAndShow()
```

To load and show fullscreen banner with custom delay after method invocation use:
```java
interstitialAd.loadAndShowWithDelay()
```
Delay interval is specified via Admin UI interface.

If you want full control over when and where to show fullscreen banner, use the following steps:

1. Call `interstitialAd.loadAd()` in advance
2. Set `AdEventCallback` to handle banner events
3. When you want to show banner, check that it is ready and show: `if (interstitialAd.isReady()) interstitialAd.showAd()`
4. Start loading next banner in `onAdClosed()` event handler of `AdEventCallback`

## Video Ads

> Attention! All SDK method calls should be done from the main thread (Main thread, UI tread).

### Video ad initialization

Add the folowing code to your activity:
```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_PLACE_ID", adListener);
videoAd.loadAd();
```

After calling the `loadAd()` method, video download starts. If you call `loadAd()` again before the video is started to show, new request processing is cancelled. Only one request will be processed.

When video download is completed, you can display it by calling `showAd()` method.
```java
videoAd.showAd();
```

To learn about download completion, subscribe to video banner events (see [Ad events handling](#markdown-header-ad-events-handling) section) or call method `isReady()`.
```java
videoAd.isReady();
```

## Native Ads

> Attention! All SDK method calls should be done from the main thread (Main thread, UI tread).

In order to start using native ads, you will need to go through following steps:
 
* Create all needed views (icon view, main image view, text views, rating bar etc...)
* Put your views together so that they look similar to your app content
* Pass the views to our SDK
* and start requesting

You can either create your custom views in a layout `.xml`, or you can add elements in code.

> All views should be placed in one child; this child itself should be placed in one child.

The custom layout `.xml`. For example:

``` xml
<FrameLayout android:id="@+id/native_ad"
 ...>
    <RelativeLayout ...>
        <ImageView android:id="@+id/native_ad_icon"
          ... />
        <ImageView android:id="@+id/native_ad_image"
          ... />
        <TextView android:id="@+id/native_ad_title"
          ... />
        <TextView android:id="@+id/native_ad_text"
          ... />
        <RatingBar android:id="@+id/native_ad_star_rating"
          ... />
        <Button android:id="@+id/native_ad_call_to_action"
          ... />
        <ImageView android:id="@+id/native_ad_choices"
          android:layout_width="40dp"
          android:layout_height="40dp"
          android:padding="10dp"
          ... />
    </RelativeLayout>
</FrameLayout>
```

After you created all the views, please proceed by passing the views to our SDK. For example:

```java
NativeAdRenderer viewRenderer = new NativeAdRenderer() {
    @Override
    public NativeAdViewAssets renderAdView(@NonNull final View view, @NonNull final NativeAdData.AdData adData) {
        final ImageView iconView = (ImageView) view.findViewById(R.id.native_ad_icon);
        if (adData.iconUrl != null) {
            // Downloading and setting the ad icon.
            Picasso.with(view.getContext()).load(adData.iconUrl).into(iconView);
            iconView.setVisibility(View.VISIBLE);
        }
        
        final ImageView imageView = (ImageView) view.findViewById(R.id.native_ad_image);
        if (adData.imageUrl != null) {
            // Downloading and setting the ad main image.
            Picasso.with(view.getContext()).load(adData.imageUrl).into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
        
        final TextView titleView = (TextView) view.findViewById(R.id.native_ad_title);
        if (adData.title != null) {
            titleView.setText(adData.title);
            titleView.setVisibility(View.VISIBLE);
        }

        final TextView textView = (TextView) view.findViewById(R.id.native_ad_text);
        if (adData.text != null) {
            textView.setText(adData.text);
            textView.setVisibility(View.VISIBLE);
        }

        final RatingBar starRatingView = (RatingBar) view.findViewById(R.id.native_ad_star_rating);
        if (adData.starRating != null) {
            starRatingView.setStepSize(0.1F);
            starRatingView.setIsIndicator(true);
            starRatingView.setNumStars((int) adData.starRating.scale);
            starRatingView.setRating((float) adData.starRating.value);
            starRatingView.setVisibility(View.VISIBLE);
        }

        if (adData.adChoice != null) {
            final ImageView adChoiceView = (ImageView) view.findViewById(R.id.native_ad_choices_icon);
            adChoiceView.setContentDescription(adData.adChoice.iconCaption);
            adChoiceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.BROWSABLE");
                    intent.setData(Uri.parse(adData.adChoice.clickUrl));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                }
            });
            adChoiceView.setVisibility(View.VISIBLE);
            // Downloading and setting the adChoice icon.
            Picasso.with(getActivity()).load(adData.adChoice.iconUrl).into(adChoiceView);
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
}
```

Add the following code to your activity:

```java
NativeAd nativeAd = new NativeAd(this, "YOUR_NATIVE_AD_PLACE_ID", viewRenderer, adListener);
nativeAd.loadAd();
```

After calling the `loadAd()` method, native ad download starts. If you call `loadAd()` again before the native ad is fully served, new request processing is cancelled. Only one request will be processed.

When native ad download is completed, you can display it by calling `renderAdView()` method.
```java
ViewGroup adView = (ViewGroup) view.findViewById(R.id.native_ad);
nativeAd.renderAdView(adView);
```

To learn about download completion, subscribe to ad events (see [Ad events handling](#markdown-header-ad-events-handling) section) or call method `isReady()`.
```java
nativeAd.isReady();
```

> Every ad should have a sign that clearly indicates that it is an ad. For example "Ad" or "Sposored".

## Ad events handling

To receive events from ad, you should implement an event listener interface `AdEventCallback`.

Listener example:
```java
AdEventCallback adListener = new AdEventCallback() {
    @Override
    public void onAdLoaded() {
    }
 
    @Override
    public void onAdFailed(@NonNull final ResponseStatus responseStatus) {
    }
 
    @Override
    public void onAdOpened() {
    }
 
    @Override
    public void onAdClicked() {
    }
 
    @Override
    public void onAdClosed() {
    }
 
    @Override
    public void onAdCompleted() {
    }
};
```

## Activity lifecycle events handling

`onPause()`, `onResume()` and `onDestroy()` methods should be called depending on the Activity lifecycle events.

Example:
```java
@Override
protected void onPause() {
    super.onPause();
    interstitialAd.onPause();
    standardAd.onPause();
    videoAd.onPause();
    nativeAd.onPause();
}

@Override
protected void onResume() {
    super.onResume();
    interstitialAd.onResume();
    standardAd.onResume();
    videoAd.onResume();
    nativeAd.onResume();
}
 
@Override
protected void onDestroy() {
    super.onDestroy();
    interstitialAd.onDestroy();
    standardAd.onDestroy();
    videoAd.onDestroy();
    nativeAd.onDestroy();
}
```

## User Data

To pass user data to Ampiri SDK use the following static methods:
```java
Ampiri.setUserBirthday(data);
Ampiri.setUserGender(UserData.Gender.FEMALE);
Ampiri.setUserInterests(Arrays.asList("football", "auto", "cats")); // Just for example. Please set real interests.
```

## User profiling

In order to improve conversions rate of the ads you may enable user data profiling. In each of your app’s activities (or in their custom superclass, if you
created one), call `appStarted` and `appClosed`:

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AmpiriInsights.appStarted(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AmpiriInsights.appClosed(this);
    }
```

> It is extremely important that `appStarted` be called before any activity is started. That’s why you should call it in `onCreate`. If any activity in your
app is in the started state before calling `appStarted`, the session lifetime results you will see in your dashboard will be incorrect.

## Log

The default log level is INFO. From the adb shell, you can change the log level to DEBUG, VERBOSE etc. using this command:

```
setprop log.tag.Ampiri_SDK DEBUG
```

```
setprop log.tag.MRAID DEBUG
```

```
setprop log.tag.VAST DEBUG
```

## Debug mode

If you want to log Debug information, please install `AmpiriLogger.setDebugMode(true)` (false by default), then you will see the logs under `Ampiri_SDK` tag.
It is recommended to use this option for integration test purposes.

## Test devices

### AdMob

```java
Ampiri.addMediationAdapter(new AdMobMediation.Builder()
    .addTestDevice("784289AB4B95404108C39EB9280D8900")
    .build());
```

### Facebook

```java
Ampiri.addMediationAdapter(new FacebookMediation.Builder()
    .addTestDevice("4096e0b2fe4611dfdbc4bc4cc53697f3")
    .build());
```