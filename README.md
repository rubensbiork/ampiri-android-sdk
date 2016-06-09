# Ampiri Android Demo

Make more revenue with mobile traffic while spending less time.
Sign up for an account at [ampiri.com](https://ampiri.com)

## Requirements and Dependencies ##

* Android 2.3 (API Version 9) and up
* support-annotations, v23.4.0
* support-v4, v23.4.0
* joor, v0.9.5
* **Recommended** Google Play Services 9.0.2

We strongly recommend compiling your app against the **Google Play Services** in order to use the `Android Advertising ID` instead of the `Device ID`,
as required by Google. Failing to correctly use the `Android Advertising ID` may result in your submission to the Play Store being rejected.

Be sure that you have obtained all the crucial data provided by Ampiri:

*adSpaceId* - for each distinguished ad space placement in the app

Please ask your account manager for further details. In case of using the values provided in the examples, no reward can be paid for the efforts of your company.

## New in this Version
Please view the [changelog](CHANGELOG.md) for a complete list of additions, fixes, and enhancements in the latest release.

- Added `StreamAdAdapter` for support native ads in `ListView` and `GridView`
- Added customizable templates for native ads
- Simplified APIs for inserting native ads
- Removed
	- References to `RECEIVE_BOOT_COMPLETED` permission
	- `AmpiriInsights` class
	- `AmpiriService` class
	- `NativeAdRenderer` class
	- `NativeAdViewAssets` class
- Renamed
    - `onResume()` to `onActivityResumed()`
    - `onPause()` to `onActivityPaused()`
    - `onDestroy()` to `onActivityDestroyed()`
- Updated the following dependencies:
	- AdColony SDK to 2.3.5
	- AppLovin SDK to 6.2.2
	- MoPub SDK to 4.7.0
	- Google Play Services to 9.0.2
	- Facebook Audience Network SDK to 4.12.1
	- NativeX SDK to 5.5.6.3
- Miscellaneous bug fixes

## Initialization ##

The Ampiri SDK is available via:

1. jCenter AAR (**Recommended**)
2. Zipped AAR

### Gradle Integration ###

Add follow rows in your _app_ module `build.gradle` file:

```
repositories {
  maven { url "http://ampiri.bintray.com/maven" }
  flatDir {
    dirs 'libs'
  }
}

depedencies {
  compile 'com.ampiri.sdk:ampiri-sdk:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-adcolony:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-admob:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-applifier:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-applovin:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-chartboost:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-facebook:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-mopub:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-nativex:3.1.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-vungle:3.1.0'

  compile 'com.google.android.gms:play-services-ads:9.0.2'
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
  compile(name: 'ampiri-sdk', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-adcolony', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-admob', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-applovin', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-applifier', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-chartboost', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-facebook', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-mopub', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-nativex', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-vungle', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mraid', version:'3.1.0', ext: 'aar')
  compile(name: 'ampiri-sdk-vast', version:'3.1.0', ext: 'aar')

  compile(name: 'unity-ads', ext: 'aar')

  compile 'com.google.android.gms:play-services-ads:9.0.2'
  compile 'com.facebook.android:audience-network-sdk:4.12.1'
  compile 'com.mopub:mopub-sdk:4.7.0@aar', {
    transitive = true
  }
  compile 'com.google.code.gson:gson:2.6.2'
  compile 'org.jooq:joor:0.9.5'
}
```

Ampiri supports the following ad networks on the client-side:

To show *standard* banner ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.0.2, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.7.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.12.1, API 11: Android 3.0 (Honeycomb)

To show *interstitial* ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.0.2, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.7.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.4.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.12.1, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.6, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.1.5, API 9: Android 2.3 (Gingerbread)

To show *video* ads:

* `ampiri-sdk-mediation-adcolony` [AdColony](https://github.com/AdColony/AdColony-Android-SDK) v2.3.4, API 14: Android 4.0 (Ice Cream Sandwich)
* `ampiri-sdk-mediation-applifier` [Unity Ads(Applifier)](https://github.com/Applifier/unity-ads-sdk) v1.5.6, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.4.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.6.3, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-vungle` [Vungle](https://v.vungle.com/sdk) v3.3.5, API 11: Android 3.0 (Honeycomb)

To show *native* ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.0.2, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.7.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.12.1, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.2.2, API 9: Android 2.3 (Gingerbread)

> We can't guarantee stable work of SDK if you use other versions of this network libraries. 

You should setup each external network on the Ampiri website, otherwise they will not be used for showing ads.

### Avoiding the 65K Limit ###

If adding third party network SDKs and adapters, if you receive a dex error, you may need to enable multidexing in your build.gradle file.

```
defaultConfig {
    multiDexEnable true
}
```
Please see the Android documentation [here](https://developer.android.com/tools/building/multidex.html).

## Documentation

Documentation is available at [HOWTO](HOWTO.md)
