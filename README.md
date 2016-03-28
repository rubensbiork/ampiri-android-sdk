# Ampiri Android Demo

Make more revenue with mobile traffic while spending less time.
Sign up for an account at [ampiri.com](https://ampiri.com)

## Requirements and Dependencies ##

* Android 2.3 (API Version 9) and up
* support-annotations, v23.0.1
* joor, v0.9.5
* gson, v2.2.4
* **Recommended** Google Play Services 8.3.0

We strongly recommend compiling your app against the **Google Play Services** in order to use the `Android Advertising ID` instead of the `Device ID`,
as required by Google. Failing to correctly use the `Android Advertising ID` may result in your submission to the Play Store being rejected.

Be sure that you have obtained all the crucial data provided by Ampiri:

* *adSpaceId* - for each distinguished ad space placement in the app

Please ask your account manager for further details. In case of using the values provided in the examples, no reward can be paid for the efforts of your company.

## Initialization ##

Save the `aar` files under _app_ module's `libs` folder (eg: `<project>/<app>/libs`)

Then add follow rows in your _app_ module `build.gradle` file:

```
#!groovy
repositories {
  flatDir {
    dirs 'libs'
  }
}

dependencies {
    compile(name: 'ampiri-sdk', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-adcolony', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-admob', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-applifier', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-chartboost', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-facebook', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-mopub', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-nativex', version:'2.2.0', ext: 'aar')
    compile(name: 'ampiri-sdk-mediation-vungle', version:'2.2.0', ext: 'aar')
    compile(name: 'unity-ads', ext: 'aar')

    compile 'com.google.android.gms:play-services-ads:8.3.0'
    compile 'com.google.code.gson:gson:2.2.4'
    compile 'org.jooq:joor:0.9.5'
}
```

Ampiri supports the following ad networks on the client-side:

To show *standard* banner ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v8.4.0
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.4.1
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.10.0

To show *interstitial* ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v8.4.0
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.4.1
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v5.5.3
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.10.0
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.3

To show *video* ads:

* `ampiri-sdk-mediation-adcolony` [AdColony](https://github.com/AdColony/AdColony-Android-SDK) v2.2.2
* `ampiri-sdk-mediation-applifier` [Unity Ads(Applifier)](https://github.com/Applifier/unity-ads-sdk) v1.5.3
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v5.5.3
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.3
* `ampiri-sdk-mediation-vungle` [Vungle](https://v.vungle.com/sdk) v3.3.3

To show *native* ads:

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v8.4.0
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.4.1
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.10.0

> We can't guarantee stable work of SDK if you use other versions of this network libraries. 

You should setup each external network on the Ampiri website, otherwise they will not be used for showing ads.

### Avoiding the 65K Limit ###

If adding third party network SDKs and adapters, if you receive a dex error, you may need to enable multidexing in your build.gradle file.

```
defaulConfig {
    multiDexEnable true
}
```
Please see the Android documentation [here](https://developer.android.com/tools/building/multidex.html).

### Supporting multiple API levels ###

Since the audience network library from facebook sdk supports API 11 (Android 3.0) and above (our SDK supports API 9 Android 2.3) you have to generate
different builds for your application. You can use flavors for that. Please add this code in your gradle config in android section:

```
#!groovy
productFlavors {
  flavor15 {
     minSdkVersion 15
     versionCode 151 // minSdkVersion 15 + versionCode 1
  }
  flavor9 {
     minSdkVersion 9
     versionCode 91 // minSdkVersion 9 + versionCode 1
  }
}
```
In dependencies module of gralde config add:

```
#!groovy
flavor15Compile(name: 'ampiri-sdk-mediation-facebook', version: '2.2.0', ext: 'aar')
```

Please see the Android documentation [here](http://developer.android.com/intl/ru/google/play/publishing/multiple-apks.html).

## Documentation

Documentation is available at [HOWTO](HOWTO.md)
