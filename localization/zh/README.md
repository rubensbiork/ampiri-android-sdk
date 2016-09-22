# Ampiri Android Demo

为了提高你的app收入来源和节约时间：请在以下网址注册一个帐户[Ampiri.com](https://ampiri.com)

视频教程整合在[ ampiri.com/tutorials](http://www.ampiri.com/tutorials/ ）

## Requirements and Dependencies ##

* Android 2.3 (API Version 9) and up
* Support-annotations, v24.2.0
* Support-v4, v24.2.0
* Joor, v0.9.6
* **Recommended** Google Play Services 9.4.0

我们强烈建议使用**Google Play services**编译你的app，从而按照Google的要求使用`Android Advertising ID`代替`Device ID`。没有正确使用`Android Advertising ID`可能导致你向Play Store的提交被拒绝。

确保你已经获得了Ampiri提供的所有重要数据：

*adUnitId* -  用于app中每一种不同的广告空间布置

请向你的帐户管理员了解进一步细节。如果发行商使用来自示例的ID，则不会付费。

## New in this Version
对于最新版中的增加、修复、和改进的完整列表，请查看增加、修复、和改进的完整列表，请查看[changelog](CHANGELOG.md)。

- 增加  `void setStore(String store)`在 `AdColonyMediation.Builder`类
- 更新以下附加组件
	- AppLovin SDK 更新到 6.3.0
- 其它错误修复

## Initialization ##

可通过以下方式获得Ampiri SDK ：

1. jCenter AAR (**Recommended**)
2. Zipped AAR

### Gradle Integration ###

在your _app_ module `build.gradle` 文件中增加以下行：

```
repositories {
  maven { url "http://ampiri.bintray.com/maven" }
  flatDir {
    dirs 'libs'
  }
}

depedencies {
  compile 'com.ampiri.sdk:ampiri-sdk:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-adcolony:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-admob:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-applifier:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-applovin:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-chartboost:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-facebook:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-mopub:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-nativex:3.2.4'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-vungle:3.2.4'
  compile 'com.google.android.gms:play-services-ads:9.4.0'
}
```

### Including the local .aar libraries ###

将 `aar` 文件保存在 _app_ module's `libs` 文件夹下 (例如： `<project>/<app>/libs`)

然后在your _app_ module `build.gradle` 文件中增加以下行：

```
repositories {
  flatDir {
    dirs 'libs'
  }
}

dependencies {
  compile(name: 'ampiri-sdk', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-adcolony', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-admob', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-applovin', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-applifier', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-chartboost', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-facebook', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-mopub', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-nativex', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-vungle', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-mraid', version:'3.2.4', ext: 'aar')
  compile(name: 'ampiri-sdk-vast', version:'3.2.4', ext: 'aar')
  compile(name: 'unity-ads', ext: 'aar')
  compile 'com.google.android.gms:play-services-ads:9.4.0'
  compile 'com.facebook.android:audience-network-sdk:4.13.0'
  compile 'com.mopub:mopub-sdk:4.9.0@aar', {
    transitive = true
  }
  compile 'com.google.code.gson:gson:2.7'
  compile 'org.jooq:joor:0.9.6'
}
```


Ampiri 在客户端一侧支持以下广告网络：


为了显示 *standard* 标准横幅广告：

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.4.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.13.0, API 11: Android 3.0 (Honeycomb)

为了显示  *interstitial* 插播广告：

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.4.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.4.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.13.0, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.6.3, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.3.0, API 9: Android 2.3 (Gingerbread)

为了显示 *video* 视频广告：

		
* `ampiri-sdk-mediation-adcolony` [AdColony](https://github.com/AdColony/AdColony-Android-SDK) v2.3.5, API 14: Android 4.0 (Ice Cream Sandwich)
* `ampiri-sdk-mediation-applifier` [Unity Ads(Applifier)](https://github.com/Applifier/unity-ads-sdk) v1.5.6, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.4.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.6.3, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-vungle` [Vungle](https://v.vungle.com/sdk) v3.3.5, API 11: Android 3.0 (Honeycomb)

为了显示  *native* 原生广告：

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.4.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.13.0, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.3.0, API 9: Android 2.3 (Gingerbread)


>  如果你使用这些网络库的其它版本，我们不能保证SDK的功能稳定。

你应当在Ampiri网站设置外部网络，否则它们不会用于显示广告。


### Eclipse集成###
自google（已废弃） （ https://developer.android.com/studio/tools/sdk/eclipse-adt.html ） Eclipse的支持下，对于SDK整合我们建议使用Android Studio.

### Avoiding the 65K Limit ###

如果你在增加第三方网络SDK和适配器时收到一个dex错误，你可能需要在你的build.gradle文件中启用multidexing。

```
defaultConfig {
    multiDexEnable true
}
```

请见Android 文档 [这里](https://developer.android.com/tools/building/multidex.html)。

## Documentation

文档可以在[HOWTO](HOWTO.md)中获得。
