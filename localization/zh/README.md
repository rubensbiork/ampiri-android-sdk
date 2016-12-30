## Ampiri 支持

通过单击链接可以找到有关将Ampiri SDK与Android应用集成的其他文档。

- [Ampiri.com Tutorials](http://www.ampiri.com/tutorials/) - 视频教程整合加注册
- [Publisher's Self-Serve UI User Guide](https://ampiri.zendesk.com/hc/en-us/articles/213857245-Publisher-s-Self-Serve-UI-User-Guide) - 出版商界面
- [Ampiri SDK Android Quickstart](https://ampiri.zendesk.com/hc/en-us/articles/213431769-Ampiri-SDK-Android-Quickstart) - 基本集合
- Ampiri SDK 参考手册即将来领!


## 需求和依赖 ##

* Android 2.3 (API Version 9) and up
* Support-annotations, v25.0.1
* Support-v4, v25.0.1
* Joor, v0.9.6
* **Recommended** Google Play Services 9.8.0

我们强烈建议使用**Google Play services**编译你的app，从而按照Google的要求使用`Android Advertising ID`代替`Device ID`。没有正确使用`Android Advertising ID`可能导致你向Play Store的提交被拒绝。

确保你已经获得了Ampiri提供的所有重要数据：

*adUnitId* -  用于app中每一种不同的广告空间布置

请向你的帐户管理员了解进一步细节。如果发行商使用来自示例的ID，则不会付费。

## New in this Version
- 改进
    - cached data source 是独立于 activity's lifecycle
    - 在infeed里，第一个广告加载后会直接显示。
    
- 更新
    - support-annotations JAR 到 25.0.1
    - support-v4 AAR 到 25.0.1
    - Facebook Audience Network SDK 到 4.17.0
- 修改:
    - 当横幅被视为如图所示但是在屏幕上看不见的错误。
    - 其他错误修复


## 初始化 ##

可通过以下方式获得Ampiri SDK ：

1. jCenter AAR (**Recommended**)
2. Zipped AAR

### Gradle集合  ###

在your _app_ module `build.gradle` 文件中增加以下行：

```
repositories {
  maven { url "http://ampiri.bintray.com/maven" }
}

dependencies {
  compile 'com.ampiri.sdk:ampiri-sdk:3.3.1'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-adcolony:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-admob:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-unityads:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-applovin:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-chartboost:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-facebook:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-mopub:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-nativex:3.3.2'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-vungle:3.3.2'
  compile 'com.google.android.gms:play-services-ads:9.8.0'
}
```

### 添加本地  .aar libraries ###

将 `aar` 文件保存在 _app_ module's `libs` 文件夹下 (例如： `<project>/<app>/libs`)

然后在your _app_ module `build.gradle` 文件中增加以下行：

```
repositories {
  flatDir {
    dirs 'libs'
  }
}

dependencies {
  compile(name: 'ampiri-sdk', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-adcolony', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-admob', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-applovin', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-unityads', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-chartboost', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-facebook', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-mopub', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-nativex', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-vungle', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-mraid', version:'3.3.2', ext: 'aar')
  compile(name: 'ampiri-sdk-vast', version:'3.3.2', ext: 'aar')
  
  compile 'com.google.android.gms:play-services-ads:9.8.0'
  compile 'com.facebook.android:audience-network-sdk:4.17.0'
  compile 'com.mopub:mopub-sdk:4.9.0@aar', {
    transitive = true
  }
  compile 'com.google.code.gson:gson:2.8.0'
  compile 'org.jooq:joor:0.9.6'
}
```

然后在您的_app_ module `proguard-rules.pro` 文件中添加以下的下行：
```
-keep class com.ampiri.** { *; }
-dontwarn com.ampiri.**
```
请看Android文件[这里](https://developer.android.com/studio/build/shrink-code.html).

## 广告网络 ##

Ampiri 在客户端一侧支持以下广告网络：


为了显示 *standard* 标准横幅广告：

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.8.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.17.0, API 11: Android 3.0 (Honeycomb)

为了显示  *interstitial* 插屏广告：

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.8.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.5.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.17.0, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.8, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.3.2, API 9: Android 2.3 (Gingerbread)

为了显示 *video* 视频广告：


* `ampiri-sdk-mediation-adcolony` [AdColony](https://github.com/AdColony/AdColony-Android-SDK) v2.3.6, API 14: Android 4.0 (Ice Cream Sandwich)
* `ampiri-sdk-mediation-unityads` [Unity Ads](https://github.com/Applifier/unity-ads-sdk) v1.5.6, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-chartboost` [Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android) v6.5.1, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-nativex` [NativeX](https://github.com/nativex/NativeX-Android-SDK) v5.5.8, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-vungle` [Vungle](https://v.vungle.com/sdk) v4.0.2, API 11: Android 3.0 (Honeycomb)

为了显示  *native* 原生广告：

* `ampiri-sdk-mediation-admob` [Google Mobile Ads](https://developers.google.com/admob/android/quick-start) v9.8.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-mopub` [MoPub](https://github.com/mopub/mopub-android-sdk) v4.9.0, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-facebook` [Facebook Audience](https://developers.facebook.com/docs/audience-network) v4.17.0, API 11: Android 3.0 (Honeycomb)
* `ampiri-sdk-mediation-applovin` [AppLovin](https://github.com/AppLovin/Android-Demo-App) v6.3.2, API 9: Android 2.3 (Gingerbread)
* `ampiri-sdk-mediation-baidu` [Baidu](http://mssp.baidu.com/app/static/main.html#/sdk) v5.6, API 8: Android 2.2 (Froyo)


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


然后在 manifest 里添加 multidex support library 的 `MultiDexApplication` 类到 application element.:
```
<application
...
android:name="android.support.multidex.MultiDexApplication">
...
</application>
```

请见Android 文档 [这里](https://developer.android.com/tools/building/multidex.html)。

## 文档

文档可以在[HOWTO](HOWTO.md)中获得。

