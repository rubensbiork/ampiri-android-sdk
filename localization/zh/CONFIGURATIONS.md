# Ampiri SDK Advanced Features

Here you can find advanced SDK topics.

## Contents

* [Ampiri 支持](#ampiri-支持)
* [平台和广告类支持](#平台和广告类支持)
* [Eclipse 集成](#Eclipse-集成)
* [广告平台测试设置](#广告平台测试设置)
* [避免65k限制](#避免65k限制)
* [日志](#日志)
* [调试模式](#调试模式)

## Ampiri 支持

通过单击链接可以找到有关将Ampiri SDK与Android应用集成的其他文档。

- [Ampiri.com Tutorials](http://www.ampiri.com/tutorials/) - 视频教程整合加注册
- [Publisher's Self-Serve UI User Guide](https://ampiri.zendesk.com/hc/en-us/articles/213857245-Publisher-s-Self-Serve-UI-User-Guide) - 出版商界面
- [Ampiri SDK Android Quickstart](https://ampiri.zendesk.com/hc/en-us/articles/213431769-Ampiri-SDK-Android-Quickstart) - 基本集合
- [Ampiri SDK Android Integration Manual](https://ampiri.zendesk.com/hc/en-us/articles/115000510445-Ampiri-SDK-Android-Integration-Manual) - 参考手册
- [Zendesk Support](https://ampiri.zendesk.com/hc/en-us) - 支持和常见问题

## 平台和广告类支持 ##

Ampiri 在客户端一侧支持以下广告平台：

为了显示标准横幅广告：

| 特定  | 平台  | 版本  |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/mopub-android-sdk)|v4.12.0, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-facebook`| [Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-inlocomedia`| [InLocoMedia](http://docs.inlocomedia.com)| v2.5.0, API 14: Android 4.0 (ICE_CREAM_SANDWICH)|

为了显示插屏广告：

| 特定  | 平台  | 版本  |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/mopub-android-sdk)|v4.12.0, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-chartboost`|[Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android)|v6.6.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-facebook`|[Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-nativex`|[NativeX](https://github.com/nativex/NativeX-Android-SDK)|v5.5.9, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-applovin`|[AppLovin](https://github.com/AppLovin/Android-Demo-App)|v6.4.2, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-inlocomedia`| [InLocoMedia](http://docs.inlocomedia.com)| v2.5.0, API 14: Android 4.0 (ICE_CREAM_SANDWICH)|

为了显示视频广告：

| 特定  | 平台  | 版本  |
|----------|----------|----------|
|`ampiri-sdk-mediation-adcolony`|[AdColony](https://github.com/AdColony/AdColony-Android-SDK)|v2.3.6, API 14: Android 4.0 (Ice Cream Sandwich)|
|`ampiri-sdk-mediation-unityads`|[Unity Ads](https://github.com/Applifier/unity-ads-sdk)|v2.0.8, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-chartboost`|[Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android)|v6.6.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-nativex`|[NativeX](https://github.com/nativex/NativeX-Android-SDK)|v5.5.9, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-vungle`|[Vungle](https://v.vungle.com/sdk)|v4.0.3, API 11: Android 3.0 (Honeycomb)|

为了显示原生广告：

| 特定  | 平台  | 版本  |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/mopub-android-sdk)|v4.12.0, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-facebook`|[Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-applovin`|[AppLovin](https://github.com/AppLovin/Android-Demo-App)|v6.4.2, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-baidu`|[Baidu](http://mssp.baidu.com/app/static/main.html#/sdk)|v5.6, API 8: Android 2.2 (Froyo)|
|`ampiri-sdk-mediation-inlocomedia`| [InLocoMedia](http://docs.inlocomedia.com)| v2.5.0, API 14: Android 4.0 (ICE_CREAM_SANDWICH)|

> 如果您使用其他版本的网络库，我们不能保证SDK的稳定功能。

您必须在Ampiri网站上设置每个第三方平台，否则它们不会用于展示广告。

## 用户数据

将用户数据传递到Ampiri SDK，请使用以下静态方法：
```java
Ampiri.setUserBirthday(data);
Ampiri.setUserGender(UserData.Gender.FEMALE);
Ampiri.setUserInterests(Arrays.asList("football", "auto", "cats")); // Just for example. Please set real interests.
```

## 广告平台测试设置  

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

## 日志 

默认日志等级为INFO。从adb shell中，你可以使用此命令将日志等级改为DEBUG、VERBOSE等：

```
setprop log.tag.Ampiri_SDK DEBUG
```

```
setprop log.tag.MRAID DEBUG
```

```
setprop log.tag.VAST DEBUG
```

## 调试模式

如果你希望记录日志调试信息，请安装 Ampiri.setDebugMode(true) (默认为false)，然后你将在 Ampiri_SDK 标签下看到日志。 建议将此选项用于整合测试。

## Eclipse 集成  ##

自google（已废弃） （ https://developer.android.com/studio/tools/sdk/eclipse-adt.html ） Eclipse的支持下，对于SDK整合我们建议使用Android Studio.

### 避免65k限制 ###

如果你在增加第三方网络SDK和适配器时收到一个dex错误，你可能需要在你的build.gradle文件中启用multidexing。

defaultConfig {
  ...
  multiDexEnabled true
  ...
}

然后在 manifest 里添加 multidex support library 的 MultiDexApplication 类到 application element:

```
<application
  ...
  android:name="android.support.multidex.MultiDexApplication">
  ...
</application>
```

请看 Android 文档 [这里](https://developer.android.com/tools/building/multidex.html).

## 更多信息  :
要转到基本SDK集成，请单击[这](Configurations.md)


