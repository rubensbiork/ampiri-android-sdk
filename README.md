# Ampiri Android Demo

Boost your app's revenue streams and save time: sign up for an account at [Ampiri.com](https://ampiri.com)

There is the integration tutorial video on [ampiri.com/tutorials](http://www.ampiri.com/tutorials/)

## Requirements and Dependencies ##

* Android 2.3 (API Version 9) and up
* support-annotations, v25.0.0
* support-v4, v25.0.0
* joor, v0.9.6
* **Recommended** Google Play Services 9.8.0

We strongly recommend compiling your app using **Google Play services**, in order to use the `Android Advertising ID` instead of the `Device ID`,
as required by Google. Failing to correctly use the `Android Advertising ID` may result in your submission to the Play Store being rejected.

Be sure that you have obtained all the crucial data provided by Ampiri:

*adUnitId* - for each distinguished ad space placement in the app

Please ask your account manager for further details. If the publisher uses the IDs from the examples, there will be no payout.

## New in this Version
Please view the [changelog](CHANGELOG.md) for a complete list of additions, fixes, and enhancements in the latest release.

- Baidu Ads Network SDK v5.6 integration
- VAST 3.0 support for video ads.
- New templates for native ads. More customization options for templates.
- Cool down for ads requests
- Cached data source for multiple `StreamAdAdapter` instances
- Removed the need for AAR file by UnityAds. You don't need to put Unity-ads-x.x.x.aar to your libs folder.
- Added
  - `setStarRating(NativeAdView.Attributes.Setter<NativeAdView.Attributes.StarRating> starRating)` to `NativeAdView.Attributes` class
  - `setAdAttribution(NativeAdView.Attributes.Setter<NativeAdView.Attributes.AdAttribution> adAttribution)` to `NativeAdView.Attributes` class
  - `setTitle(NativeAdView.Attributes.Setter<NativeAdView.Attributes.Title> title)` to `NativeAdView.Attributes` class
  - `setIcon(NativeAdView.Attributes.Setter<NativeAdView.Attributes.Icon> icon)` to `NativeAdView.Attributes` class
  - `setCoverImage(NativeAdView.Attributes.Setter<NativeAdView.Attributes.CoverImage> coverImage)` to `NativeAdView.Attributes` class
  - `setDescription(NativeAdView.Attributes.Setter<NativeAdView.Attributes.Description> description)` to `NativeAdView.Attributes` class
  - `setCallToAction(@NonNull NativeAdView.Attributes.Setter<NativeAdView.Attributes.CallToAction> callToAction)` to `NativeAdView.Attributes` class
- Removed
  - `setAdAttributionText(String adAttributionText)` from `NativeAdView.Attributes` class
  - `setDefaultCallToActionText(String defaultCallToActionText)` from `NativeAdView.Attributes` class
- Fixed:
    - Miscellaneous bug fixes


## Initialization ##

The Ampiri SDK is available via:

1. jCenter AAR (**Recommended**)
2. Zipped AAR

### Gradle Integration ###

Add the following rows in your _app_ module `build.gradle` file:

```
repositories {
  maven { url "http://ampiri.bintray.com/maven" }
}

dependencies {
  compile 'com.ampiri.sdk:ampiri-sdk:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-adcolony:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-admob:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-unityads:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-applovin:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-chartboost:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-facebook:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-mopub:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-nativex:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-vungle:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-baidu:3.3.1'

  compile 'com.google.android.gms:play-services-ads:9.8.0'
}
```

### Including the local .aar libraries ###

Save the `aar` files under _app_ module's `libs` folder (eg: `<project>/<app>/libs`)

Then add follow rows in your _app_ module `build.gradle` file:

```
repositories {
  flatDir {
    dirs 'libs'
  }
}

dependencies {
  compile(name: 'ampiri-sdk', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-adcolony', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-admob', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-applovin', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-unityads', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-chartboost', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-facebook', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-mopub', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-nativex', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-vungle', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-baidu', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-mraid', version:'3.3.1', ext: 'aar')
  compile(name: 'ampiri-sdk-vast', version:'3.3.1', ext: 'aar')

  compile 'com.google.android.gms:play-services-ads:9.8.0'
  compile 'com.facebook.android:audience-network-sdk:4.16.1'
  compile 'com.mopub:mopub-sdk:4.9.0@aar', {
    transitive = true
  }
  compile 'com.google.code.gson:gson:2.7'
  compile 'org.jooq:joor:0.9.6'
}
```

Then add follow rows in your _app_ module `proguard-rules.pro` file:

```
-keep class com.ampiri.** { *; }
-dontwarn com.ampiri.**
```

Please see the Android documentation [here](https://developer.android.com/studio/build/shrink-code.html).

## Ad networks ##

Ampiri supports the following ad networks on the client-side:

To show *standard* banner ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.8.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.16.1, API 11: Android 3.0 (Honeycomb)

To show *interstitial* ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.8.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.5.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.16.1, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.8, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.3.2, API 9: Android 2.3 (Gingerbread)

To show *video* ads:

* `ampiri-sdk-mediation-adcolony` [AdColony](https://github.com/AdColony/AdColony-Android-SDK) v2.3.6, API 14: Android 4.0 (Ice Cream Sandwich)
* `ampiri-sdk-mediation-unityads` [Unity Ads](https://github.com/Applifier/unity-ads-sdk) v2.0.4, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.5.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.8, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-vungle` [Vungle](https://v.vungle.com/sdk) v4.0.2, API 11: Android 3.0 (Honeycomb)

To show *native* ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.8.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.16.1, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.3.2, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-baidu` [Baidu](http://mssp.baidu.com/app/static/main.html#/sdk) v5.6, API 8: Android 2.2 (Froyo)


> We can't guarantee stable functionality of the SDK if you use other versions of these network libraries. 

You should set up each external network on the Ampiri website, otherwise they will not be used for showing ads.

### Eclipse Integration ###

Since Google [deprecated] (https://developer.android.com/studio/tools/sdk/eclipse-adt.html) the Eclipse support, we recommend to use Android Studio for the SDK integration.

### Avoiding the 65K Limit ###

If you receive a dex error while adding third party network SDKs and adapters, you may need to enable multidexing in your build.gradle file.

```
defaultConfig {
  ...
  multiDexEnabled true
  ...
}
```

Then in your manifest add the `MultiDexApplication` class from the multidex support library to the application element.:

```
<application
  ...
  android:name="android.support.multidex.MultiDexApplication">
  ...
</application>
```

Please see the Android documentation [here](https://developer.android.com/tools/building/multidex.html).

## Documentation

Documentation is available at [HOWTO](HOWTO.md)

