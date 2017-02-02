# Ampiri Android SDK 3.x Integration Guide

* [Ampiri Support](#ampiri-support)
* [Updating your Android Manifest](#updating-your-android-manifest)
* [Standard banners](#standard-banners)
* [Interstitials](#interstitial-ads)
* [Video](#video-ads)
* [Native Ads](#native-ads)
* [In Feed Ads](#in-feed-ads)
* [Ad events handling](#ad-events-handling)
* [Activity lifecycle events handling](#activity-lifecycle-events-handling)
* [User Data](#user-data)
* [Ad networks settings](#ad-networks-settings)
* [Log](#log)
* [Debug mode](#debug-mode)

## Ampiri Support

Additional documentation for integrating the Ampiri SDK with your Android app can be found by clicking the links. 

- [Ampiri.com Tutorials](http://www.ampiri.com/tutorials/) - Tutorials and Sign Up to Ampiri
- [Publisher's Self-Serve UI User Guide](https://ampiri.zendesk.com/hc/en-us/articles/213857245-Publisher-s-Self-Serve-UI-User-Guide) - Publisher's Interface
- [Ampiri SDK Android Quickstart](https://ampiri.zendesk.com/hc/en-us/articles/213431769-Ampiri-SDK-Android-Quickstart) - Basic Integration
- Ampiri SDK Reference Manual Coming Soon!


## Updating your Android Manifest

Under the main `<manifest>` element, add the following permissions.

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```

* ACCESS_COARSE_LOCATION (recommended) - Grants the SDK permission to access approximate location based on cell tower.
* ACCESS_FINE_LOCATION (recommended) - Grants the SDK permission to access a more accurate location based on GPS.

Although not technically required, the LOCATION permissions make it possible for the SDK to send location-based data to advertisers. Sending better
location data generally leads to better monetization.

* WRITE_EXTERNAL_STORAGE (optional) - Allows the SDK to cache all ad assets (creatives, custom frames, etc.) in external memory. This can maximize
performance by ensuring immediate delivery of ads and minimize network traffic used by the SDK by keeping cached
ad assets available even after the user closes the app.

* READ_PHONE_STATE (recommended) - Allows the SDK to handle calls interrupting video playback during videos.

> When using SDK as a library project, you shouldn't need to worry about merging AndroidManifest.xml changes or Proguard settings. If you run into problems,
make sure `manifestmerger.enabled` is set to `true` in `project.properties`

## Standard banners

> Note: All SDK method calls should be done from the main thread (Main thread, UI thread).

Add a banner to layout file, e.g.:
```xml
<FrameLayout
    android:id="@+id/ad_view"
    android:layout_width="320dp"
    android:layout_height="50dp"
    android:background="@android:color/white"/>
```

It is advisable to make the banner size in the layout the same as the required one (see below). Otherwise, the banner might be displayed incorrectly.

Add the following code to your activity:
```java
FrameLayout adView = (FrameLayout) view.findViewById(R.id.ad_view);
StandardAd standardAd = new StandardAd(this, adView, "YOUR_STANDARD_AD_UNIT_ID", BannerSize.BANNER_SIZE_320x50, adListener);
standardAd.loadAd();
```

Banners `320*50` are served by default. Available sizes:

* 320x50
* 728x90

### Standard banner auto-update

You can switch the banner auto-update function on or off; to do this, call `setAutorefreshEnabled()` method, e.g.:
```java
standardAd.setAutorefreshEnabled(false);
```

By default, auto-update is switched on. The auto-update period is set up via the admin panel.

From version 3.4 onwards, the SDK’s interface includes showAd() method. It is strongly recommended to call this method after the standard banner’s view became visible, as it will then trigger an impression event.

## Interstitial Ads

> Note: All SDK method calls should be done from the main thread (Main thread, UI thread).

### Interstitial ad initialization

Add the following code to your activity:
```java
InterstitialAd interstitialAd = new InterstitialAd(this, "YOUR_INTERSTITIAL_AD_UNIT_ID", adListener);
interstitialAd.loadAd();
```
After calling the `loadAd()` method, the interstitial download starts. If you call `loadAd()` again before the banner is fully served, the previous request processing is cancelled. In this case, only the last request will be processed.

When the banner download is completed, you can display the banner by calling `showAd()` method.
```java
interstitialAd.showAd();
```

To learn about download completion, subscribe to banner events (see [Ad events handling](#ad-events-handling)) or call method `isReady()`.
```java
interstitialAd.isReady();
```

If your application workflow allows showing full screen banners at any time and in any place, there are 2 additional ways to show it right after the loading has finished or with a custom delay after method invocation.

To load and show full screen banner right after it was loaded use:
```java
interstitialAd.loadAndShow()
```

To load and show full screen banner with a custom delay after method invocation use:
```java
interstitialAd.loadAndShowWithDelay()
```
The delay interval is specified via Admin UI interface.

If you want full control over when and where to show full screen banners, use the following steps:

1. Call `interstitialAd.loadAd()` in advance
2. Set `AdEventCallback` to handle banner events
3. When you want to show the banner, check that it is ready and show: `if (interstitialAd.isReady()) interstitialAd.showAd()`
4. Start loading next banner in `onAdClosed()` event handler of `AdEventCallback`

## Video Ads

> Note: All SDK method calls should be done from the main thread (Main thread, UI thread).

### Video ad initialization

Add the following code to your activity:
```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", adListener);
videoAd.loadAd();
```

Close button for video ads is supported for some networks. If you want to enable this button you should add boolean parameter in video ad constructor:
```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled);
or
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled, adListener);
```

After calling the `loadAd()` method, the video download starts. If you call `loadAd()` again before the video has started to show, new request processing is cancelled. Only one request will be processed.

When the video download is completed, you can display it by calling the `showAd()` method.
```java
videoAd.showAd();
```

To learn about download completion, subscribe to video banner events (see [Ad events handling](#ad-events-handling) section) or call method `isReady()`.
```java
videoAd.isReady();
```

## Native Ads

>Note: All SDK method calls should be done from the main thread (Main thread, UI thread).

Native ads are loaded via the `NativeAd` class, which has its own `Builder` class to customize it during creation:

```java
NativeAd nativeAd = new NativeAd.Builder()
  .setAdUnitId(YOUR_NATIVE_AD_UNIT_ID)
  .setCallback(adListener)
  .build(this);
```

To show native ads you can use two methods:

* Create an ad view programmatically from template and add it to the screen.
* Add `NativeAdView` view in the layout and bind loaded data to this view.

### Templates

Ampiri SDK provides 3 types of templates for native ads

* FeedCardNativeAdView - Icon, title, description, star rating, and CTA button
* StoryCardNativeAdView - Icon, image, title, description, star rating, and CTA button
* VideoCardNativeAdView - Icon, image/video/carousel, title, description, star rating, and CTA button

> Every template has a label that clearly indicates that it is an ad. For example "Ad" or "Sponsored".

If you want to use one of these templates, you can add the selected template in the creation of the `NativeAd`:

```java
.setAdViewBuilder(FeedCardNativeAdView.BUILDER);
```

Add a banner place to layout, e.g.:
``` xml
  <FrameLayout
    android:id="@+id/ad_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:visibility="gone"/>
```

After calling the `loadAd()` method, the ad download starts. If you call `loadAd()` again before the banner is fully served, new request processing is
ignored. In this case, only the last request will be processed.

When the banner download is completed, you can display the banner by calling `renderAdView()` method.

```java
adContainerView = (FrameLayout) view.findViewById(R.id.ad_container);

@Override
public void onAdLoaded() {
  adContainerView.setVisibility(View.VISIBLE);
  adContainerView.removeAllViews();
  adContainerView.addView(nativeAd.renderAdView());
}
```

### Create native UI

In order to create custom native ads ui, you will need to go through following steps:

* Create all needed views (icon view, main image view, text views, rating bar etc...)
* Pass the views to our SDK

You can either create your custom views in a layout `.xml`, or you can add elements in the code.

> All views should be placed in one child; this child itself should be placed in `NativeAdView`.

Custom layout `.xml`. For example:

``` xml
<com.ampiri.sdk.banner.NativeAdView android:id="@+id/native_ad"
 ...>
    <RelativeLayout ...>
        <ImageView android:id="@+id/native_ad_icon"
          ... />
        <ImageView android:id="@+id/native_ad_cover_image"
          ... />
        <FrameLayout android:id="@+id/native_ad_media_container"
          ... />
        <TextView android:id="@+id/native_ad_title"
          ... />
        <TextView android:id="@+id/native_ad_text"
          ... />
        <RatingBar android:id="@+id/native_ad_star_rating"
          ... />
        <Button android:id="@+id/native_ad_call_to_action"
          ... />
        <TextView android:id="@+id/native_ad_attribution"
          ... />
        <ImageView android:id="@+id/native_ad_choices_icon"
          android:layout_width="40dp"
          android:layout_height="40dp"
          android:padding="10dp"
          ... />
        <FrameLayout
          android:id="@+id/native_ad_choices_container"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:minHeight="20dp"
          android:minWidth="20dp"
          ... />
    </RelativeLayout>
</com.ampiri.sdk.banner.NativeAdView>
```

After you created all the views, please proceed by passing the views to our SDK. For example:

```java
  adView = (NativeAdView) view.findViewById(R.id.native_ad);

  adView.setIconView(R.id.native_ad_icon);
  adView.setCoverImageView(R.id.native_ad_cover_image);
  adView.setMediaContainerView(R.id.native_ad_media_container);
  adView.setTextView(R.id.native_ad_text);
  adView.setTitleView(R.id.native_ad_title);
  adView.setCallToActionView(R.id.native_ad_call_to_action);
  adView.setStarRatingView(R.id.native_ad_star_rating);
  adView.setAdAttributionView(R.id.native_ad_attribution);
  adView.setAdChoiceIconView(R.id.native_ad_choices_icon);
  adView.setAdChoiceContainerView(R.id.native_ad_choices_container);
```

Registering the native ad view in the creation of the `NativeAd`:

```java
.setAdView(adView);
```

After calling the `loadAd()` method, the ad download starts. If you call `loadAd()` again before the banner is fully served, new request processing is
ignored. In this case, only the last request will be processed.

When banner download is completed, you can display the banner by calling `showAd()` method.

To learn about download completion, subscribe to ad events (see [Ad events handling](#ad-events-handling) section) or call method `isReady()`.
```java
nativeAd.isReady();
```

### Customize native UI

With a native template, you can customize the following elements:

* Title
    * Text font (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
    * Text size
    * Text color
    * Max Ems (25 by default)
* Icon
    * Dimensions(width, height)
    * Margins (left, right)
* Star rating
    * Star size (SMALL, MEDIUM, LARGE)
* Attribution label
    * Default text
    * Text font (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
    * Text size
    * Text color
* Cover image
    * Background color
    * Background resource
    * Margins (left, right, bottom, top)
    * Alignment (undefine, top, bottom, left, right, center, center_vertical, center_horizontal)
* Description
    * Text font (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
    * Text size
    * Text color
    * Margins (left, right)
    * Max Ems (100 by default)
* Call To Action button
    * Default text
    * Text font (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
    * Text size
    * Text color
    * Max Ems (25 by default)
    * Background color
    * Background resource
    * Margins (left, right)

In order to customize these elements, you will need to build an attributes object and provide the following in the creation of the `NativeAd`:
```java
.setAdUnitId(AD_UNIT_ID)
.setAdViewBuilder(StoryCardNativeAdView.BUILDER)
.setAdViewAttributes(new NativeAdView.Attributes(this)
    .setTitle(new NativeAdView.Attributes.Setter<NativeAdView.Attributes.Title>() {
        @Override
        public NativeAdView.Attributes.Title set(@NonNull NativeAdView.Attributes.Title builder) {
            return builder
                    .setTextFont(Typeface.MONOSPACE)
                    .setTextColor(Color.RED)
                    .setMaxEms(20);
        }
    })
    .setStarRating(new NativeAdView.Attributes.Setter<NativeAdView.Attributes.StarRating>() {
        @Override
        public NativeAdView.Attributes.StarRating set(@NonNull NativeAdView.Attributes.StarRating builder) {
            return builder.setStarSize(Size.MEDIUM);
            }
        })
    .setAdAttribution(new NativeAdView.Attributes.Setter<NativeAdView.Attributes.AdAttribution>() {
        @Override
        public NativeAdView.Attributes.AdAttribution set(@NonNull final NativeAdView.Attributes.AdAttribution adAttribution) {
            return adAttribution.setDefaultText(R.string.sponsored);
        }
}))
```

Also, you can set style your `NativeAdView` layout by using following attributes:

|attribute name | format | description |
|---|---|---|
| `Title` |
| ampiriTitleTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Font of the Title text |
| ampiriTitleTextSize | reference,dimension | Size of the Title text|
| ampiriTitleTextColor | reference,color | Color of the Title text|
| ampiriTitleTextMaxLengthEms | integer | Maximum Ems of the Title text|
| `Icon` |
| ampiriIconWidth | reference,dimension | Icon view width |
| ampiriIconHeight | reference,dimension |Icon view height|
| ampiriIconMarginLeft | reference,dimension | Icon view left margin|
| ampiriIconMarginRight | reference,dimension | Icon view right margin|
| `Star Rating` |
| ampiriStarRatingSize | SMALL, MEDIUM, LARGE | Size of the rating bar stars. SMALL - 10dp for height and width, MEDIUM - 16dp, LARGE - 22dp|
| `Ad Attribution` |
| ampiriAdAttributionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Font of the Attribution label text |
| ampiriAdAttributionTextSize | reference,dimension | Size of the Ad Attribution text|
| ampiriAdAttributionTextColor | reference,color | Color of the Ad Attribution text|
| ampiriAdAttributionDefaultText | reference,string | Default Ad Attribution text|
| `Cover Image` |
| ampiriCoverImageBackgroundColor | reference,color | Cover Image background color|
| ampiriCoverImageBackgroundResource | reference | Cover Image background resource|
| ampiriCoverImageMarginLeft | reference,dimension | Cover Image left margin|
| ampiriCoverImageMarginRight | reference,dimension | Cover Image right margin|
| ampiriCoverImageMarginTop | reference,dimension | Cover Image top margin|
| ampiriCoverImageMarginBottom | reference,dimension | Cover Image bottom margin|
| ampiriCoverImageAlignment | undefine, top, bottom, left, right, center, center_vertical, center_horizontal | Cover Image alignment within the NativeAd view|
| `Description` |
| ampiriDescriptionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Font of the Description text |
| ampiriDescriptionTextSize | reference,dimension | Size of the Description text|
| ampiriDescriptionTextColor | reference,color | Color of the Description text|
| ampiriTitleTextMaxLengthEms | integer | Maximum Ems of the Description text|
| ampiriDescriptionMarginLeft | reference,dimension | Description view left margin|
| ampiriDescriptionMarginRight | reference,dimension | Description view right margin|
| `Call To Action` |
| ampiriCallToActionBackgroundColor |  reference,color | Color of the Call To Action button |
| ampiriCallToActionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Font of the Description text |
| ampiriCallToActionTextSize | reference,dimension | Size of the Call To Action button text|
| ampiriCallToActionTextColor | reference,color | Color of the Call To Action button text|
| ampiriCallToActionMarginLeft | reference,dimension | Call To Action button view left margin|
| ampiriCallToActionMarginRight | reference,dimension | Call To Action button view right margin|
| ampiriCallToActionMarginTop | reference,dimension | Call To Action button view top margin|
| ampiriCallToActionMarginBottom | reference,dimension | Call To Action button view bottom margin|
| ampiriCallToActionDefaultText | reference,string | Default Call To Action text|

You can set this attributes in your custom `NativeAdView` layout
``` xml
<com.ampiri.sdk.banner.NativeAdView android:id="@+id/native_ad"
    app:ampiriTitleTextFont="MONOSPACE"
    app:ampiriIconWidth="42dp"
    app:ampiriIconHeight="42dp"
    app:ampiriCallToActionTextColor="@android:color/lighter_gray"
 ...>
```

## In Feed Ads

> Note: All SDK method calls should be done from the main thread (Main thread, UI thread).

Add the following code to your activity:

```java
StreamAdAdapter adAdapter = new StreamAdAdapter.Builder()
  .setAdapter(new MainAdapter(this))
  .setAdUnitId("YOUR_NATIVE_AD_UNIT_ID")
  .setViewBuilder(FeedCardNativeAdView.BUILDER)
  .setEventCallback(this)
  .build(this);
listView.setAdapter(adAdapter);
adAdapter.loadAd();
```

After calling the `loadAd()` method, the in-feed ad download starts. If you call `loadAd()` again before the native ad is fully served, new request processing is cancelled. Only one request will be processed.

When in-feed ad download is completed, it will show automatically.

To learn about download completion, subscribe to ad events (see [Ad events handling](#ad-events-handling) section).


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

Ampiri SDK should be notified of when your `Activity` is resumed, paused, and destroyed by overriding the lifecycle methods and calling the appropriate methods
 on `Ampiri` class. The analytics SDK should be initialised and started before requiring for ads. To ensure that the metrics are not over-counted, it is
 highly recommended that the Ampiri SDK be called in the `Application` class.

```java
public class YourApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Ampiri.onApplicationCreated(this);
        //...
    }
    //...
}
```

Don't forget to add application name to your `AndroidManifest.xml` file.

```xml
    <application android:name=".YourApplication">
        <!-- activities goes here -->
    </application>
```

`onPause()`, `onResume()` and `onDestroy()` methods should be called depending on the activity lifecycle events.

Example:
```java
@Override
protected void onPause() {
    super.onPause();
    interstitialAd.onActivityPaused();
    standardAd.onActivityPaused();
    videoAd.onActivityPaused();
    nativeAd.onActivityPaused();
}

@Override
protected void onResume() {
    super.onResume();
    interstitialAd.onActivityResumed();
    standardAd.onActivityResumed();
    videoAd.onActivityResumed();
    nativeAd.onActivityResumed();
}

@Override
protected void onDestroy() {
    super.onDestroy();
    interstitialAd.onActivityDestroyed();
    standardAd.onActivityDestroyed();
    videoAd.onActivityDestroyed();
    nativeAd.onActivityDestroyed();
}
```

### API level 9 until 13

If your app `minSdkVersion` in gradle is between `9` and `13`, consider updating it to at least `14` to simplify the integration process in the long term.
Consult the official Android [dashboard](http://developer.android.com/about/dashboards/index.html) to know the latest market share of the major versions.

To provide proper analytics it is required to call certain Ampiri SDK methods every time any Activity resumes or pauses. In order to do so you should
follow these steps for each Activity of your app:

* In your Activity's `onResume` method call `Ampiri.onResume`. Create the method if needed.
* In your Activity's `onPause` method call `Ampiri.onPause`. Create the method if needed.

After these steps your activity should look like this:

```java
import com.ampiri.sdk.Ampiri;
// ...
public class YourActivity extends Activity {
    protected void onResume() {
        super.onResume();
        Ampiri.onActivityResumed(this);
    }
    protected void onPause() {
        super.onPause();
        Ampiri.onActivityPaused(this);
    }
    // ...
}
```

Repeat these steps for **every** Activity of your app. Don't forget these steps when you create new Activities in the future. Depending on your coding
style you might want to implement this in a common superclass of all your Activities.

## User Data

To pass user data to the Ampiri SDK, use the following static methods:
```java
Ampiri.setUserBirthday(data);
Ampiri.setUserGender(UserData.Gender.FEMALE);
Ampiri.setUserInterests(Arrays.asList("football", "auto", "cats")); // Just for example. Please set real interests.
```

## Ad networks settings

### AdMob

```java
Ampiri.addMediationAdapter(new AdMobMediation.Builder()
    .addTestDevice("HASHED_ID")
    .build());
```

### Facebook

```java
Ampiri.addMediationAdapter(new FacebookMediation.Builder()
    .addTestDevice("HASHED_ID")
    .build());
```

### AdColony

```java
Ampiri.addMediationAdapter(new AdColonyMediation.Builder()
    .setStore("Amazon")
    .build());
```

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

If you want to log debug information, please install `AmpiriLogger.setDebugMode(true)` (false by default), then you will see the logs under `Ampiri_SDK` tag.
It is recommended that this option should be used for integration test purposes.
