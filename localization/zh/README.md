[Português](localization/pt/README.md)

# Ampiri SDK 集成 

提升收入和节省时间：请在[Ampiri.com](https://ampiri.com)注册帐户

## Contents

* [Ampiri支持](#ampiri支持)
* [平台和广告类支持](#平台和广告类支持)
* [需求和依赖](#需求和依赖)
* [添加AmpiriSDK和第三方平台到项目](#添加ampirisdk和第三方平台到项目)
* [更新Android Manifest](#更新android-manifest)
* [标准横幅广告初始化和活动](#标准横幅广告初始化)
* [插屏广告初始化和活动](#插屏广告初始化和活动)
* [视频广告初始化和活动](#视频广告初始化和活动)
* [原生和Feed广告，模版和UI](#原生和feed广告模版和ui)
* [广告回调](#广告回调)
* [活动生命周期](#活动生命周期)
* [Demo（示例）应用/测试](#demo示例应用测试)

## Ampiri支持
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
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/mopub-android-sdk)|v4.12.0, API 16: Android 4.1 (JELLY_BEAN)|
|`ampiri-sdk-mediation-facebook`| [Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 15: Android 4.0.3 (ICE_CREAM_SANDWICH_MR1)|
|`ampiri-sdk-mediation-inlocomedia`| [InLocoMedia](http://docs.inlocomedia.com)| v2.5.0, API 14: Android 4.0 (ICE_CREAM_SANDWICH)|

为了显示插屏广告：

| 特定  | 平台  | 版本  |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/mopub-android-sdk)|v4.12.0, API 16: Android 4.1 (JELLY_BEAN)|
|`ampiri-sdk-mediation-chartboost`|[Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android)
|v6.6.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-facebook`|[Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 15: Android 4.0.3 (ICE_CREAM_SANDWICH_MR1)|
|`ampiri-sdk-mediation-nativex`|[NativeX](https://github.com/nativex/NativeX-Android-SDK)|v5.5.9, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-applovin`|[AppLovin](https://github.com/AppLovin/Android-Demo-App)|v6.4.2, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-inlocomedia`| [InLocoMedia](http://docs.inlocomedia.com)| v2.5.0, API 14: Android 4.0 (ICE_CREAM_SANDWICH)|

为了显示视频广告：

| 特定  | 平台  | 版本  |
|----------|----------|----------|
|`ampiri-sdk-mediation-adcolony`|[AdColony](https://github.com/AdColony/AdColony-Android-SDK)|v2.3.6, API 14: Android 4.0 (Ice Cream Sandwich)|
|`ampiri-sdk-mediation-unityads`|[Unity Ads](https://github.com/Applifier/unity-ads-sdk)|v2.0.8, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-chartboost`|[Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android)
|v6.6.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-nativex`|[NativeX](https://github.com/nativex/NativeX-Android-SDK)|v5.5.9, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-vungle`|[Vungle](https://v.vungle.com/sdk)|v4.0.3, API 11: Android 3.0 (Honeycomb)|

为了显示原生广告：

| 特定  | 平台  | 版本  |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/mopub-android-sdk)|v4.12.0, API 16: Android 4.1 (JELLY_BEAN)|
|`ampiri-sdk-mediation-facebook`|[Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 15: Android 4.0.3 (ICE_CREAM_SANDWICH_MR1)|
|`ampiri-sdk-mediation-applovin`|[AppLovin](https://github.com/AppLovin/Android-Demo-App)|v6.4.2, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-baidu`|[Baidu](http://mssp.baidu.com/app/static/main.html#/sdk)|v5.6, API 8: Android 2.2 (Froyo)|
|`ampiri-sdk-mediation-inlocomedia`| [InLocoMedia](http://docs.inlocomedia.com)| v2.5.0, API 14: Android 4.0 (ICE_CREAM_SANDWICH)|

> 如果您使用其他版本的网络库，我们不能保证SDK的稳定功能。

您必须在Ampiri网站上设置每个第三方平台，否则它们不会用于展示广告。

## 需求和依赖 ##

* Android 2.3 (API Version 9) and up
* support-annotations, v25.2.0
* support-v4, v25.2.0
* **推荐** Google Play Services 10.0.1

我们强烈建议使用Google Play services编译你的app，从而按照Google的要求使用Android Advertising ID代替Device ID。没有正确使用Android Advertising ID可能导致你向Play Store的提交被拒绝。

确保你已经获得了Ampiri提供的所有重要数据：

adUnitId - 用于app中每一种不同的广告空间布置

请向你的帐户管理员了解进一步细节。如果发行商使用来自示例的ID，则不会付费。

## 添加AmpiriSDK和第三方平台到项目 ##
可通过以下方式获取Ampiri SDK ：

1. jCenter AAR (**Recommended**)
2. Zipped AAR

在your app module build.gradle 文件中增加以下行：

```
repositories {
  maven { url "http://ampiri.bintray.com/maven" }
}

dependencies {
  compile 'com.ampiri.sdk:ampiri-sdk:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-adcolony:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-admob:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-unityads:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-applovin:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-chartboost:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-facebook:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-inlocomedia:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-mopub:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-nativex:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-vungle:3.7.0'
  compile 'com.ampiri.sdk:ampiri-sdk-mediation-baidu:3.7.0'

  compile 'com.google.android.gms:play-services-ads:10.0.1'
}
```

### 添加本地 .aar libraries ###

将 aar 文件保存在 app module's libs 文件夹下 (例如： <project>/<app>/libs)

然后在your app module build.gradle 文件中增加以下行：

```
repositories {
  flatDir {
    dirs 'libs'
  }
}

dependencies {
  compile(name: 'ampiri-sdk', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-adcolony', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-admob', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-applovin', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-unityads', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-chartboost', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-facebook', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-inlocomedia', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-mopub', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-nativex', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-vungle', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mediation-baidu', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-mraid', version:'3.7.0', ext: 'aar')
  compile(name: 'ampiri-sdk-vast', version:'3.7.0', ext: 'aar')

  compile 'com.google.android.gms:play-services-ads:10.0.1'
  compile 'com.facebook.android:audience-network-sdk:4.20.0'
  compile('com.mopub:mopub-sdk-banner:4.12.0@aar') {
      transitive = true
  }
  compile('com.mopub:mopub-sdk-interstitial:4.12.0@aar') {
      transitive = true
  }
  compile('com.mopub:mopub-sdk-native-static:4.12.0@aar') {
      transitive = true
  }
  compile 'com.inlocomedia.android:android-sdk:2.5.0'
  compile 'com.google.code.gson:gson:2.8.0'
}
```

然后在您的app module proguard-rules.pro 文件中添加以下的下行：

```
-keep class com.ampiri.** { *; }
-dontwarn com.ampiri.**
```

## 更新Android Manifest ##

在主 <manifest> 元素下，增加以下许可。

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```
* ACCESS_COARSE_LOCATION (recommended) - Grants the SDK permission to access approximate location based on cell tower.
* ACCESS_FINE_LOCATION (recommended) - Grants the SDK permission to access a more accurate location based on GPS.

尽管技术上没有要求，LOCATION许可使SDK能够向广告商发送基于位置的数据。发送更好的位置数据通常会带来更好的经济效益。

* WRITE_EXTERNAL_STORAGE (optional) - 允许SDK将所有的有用广告数据（创新、用户框架、等等）缓存在外部存储器。这就能通过确保立即投放广告使效果最大化，并通过使缓存的广告数据可用（即使在用户关闭app之后），尽量减少SDK使用的网络流量。

* READ_PHONE_STATE（推荐） - 允许SDK处理视频期间的电话中断视频回放。

当将SDK作为库工程使用时，你无需担心合并AndroidManifest.xml变动或Proguard设置。如果你遇到问题，确保在project.properties中将manifestmerger.enabled设置为true。

请看Android[文档](https://developer.android.com/studio/build/shrink-code.html).

## 标准横幅广告初始化和活动 ##
> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

对布局文件增加一个横幅广告，例如：
```xml
<FrameLayout
    android:id="@+id/ad_view"
    android:layout_width="320dp"
    android:layout_height="50dp"
    android:background="@android:color/white"/>
```
我们建议在布局中使横幅广告的尺寸与要求的相同（见下文）。否则横幅广告可能会不正确显示。

请在您的Activity里添加以下代码：
```java
FrameLayout adView = (FrameLayout) view.findViewById(R.id.ad_view);
StandardAd standardAd = new StandardAd(this, adView, "YOUR_STANDARD_AD_UNIT_ID", BannerSize.BANNER_SIZE_320x50, adListener);
standardAd.loadAd();
```

默认情况下，投放横幅“320 * 50”。可用尺寸：

* 320*50
* 728*90

### 标准横幅自动更新

您可以打开或关闭横幅广告的自动更新功能；为此，调用setAutorefreshEnabled()方法，例如
```java
standardAd.setAutorefreshEnabled(false);
```
在默认情况下，自动更新打开。通过管理员面板设置自动更新时段。

从3.4版本开始，SDK的接口包括`showAd`方法。我们强烈建议在标准横幅广告视图显示后调用此方法，因为这样会触发展示事件。

## 插屏广告初始化和活动 ##

> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

### 插屏广告初始化

请在您的Activity里添加以下代码：
```java
InterstitialAd interstitialAd = new InterstitialAd(this, "YOUR_INTERSTITIAL_AD_UNIT_ID", adListener);
interstitialAd.loadAd();
```
在调用`loadAd()`方法之后，广告下载会立刻开始。如果您在广告被完全处理之前再次调用`loadAd()`，则新的请求处理就被忽略。在这种情况下，只有最后一个请求才会生效。

当插屏广告下载完成后，你可以通过调用`showAd()`方法显示插屏广告。
```java
interstitialAd.showAd();
```
为了得知下载是否完成，订阅插屏广告事件（ 请见[AD事件处理](#AD事件处理) ）或调用方法`isReady()`。
```java
interstitialAd.isReady();
```
如果您的申请工作流程允许在任何时间和任何位置显示全屏插屏广告，那么还有两种方法可以在加载完成后或在调用方法设置延迟后正确显示。

如果需要加载全屏插屏广告，并在加载后立即显示，则使用：
```java
interstitialAd.loadAndShow()
```
如果需要加载全屏插屏广告，并在调用方法设定延迟后显示，则使用：
```java
interstitialAd.loadAndShowWithDelay()
```
通过Admin UI接口指定延迟间隔。

如果你想要完全控制全屏插屏广告显示的时间和位置，使用以下步骤：

1. 提前调用 `interstitialAd.loadAd()`
2. 设置 `AdEventCallback` 处理插屏广告事件。
3. 当你希望显示插屏广告时，检查是否准备就绪，并显示：
```
if (interstitialAd.isReady()) 
    interstitialAd.showAd() 
```
4. 在AdEventCallback的事件处理器 onAdClosed()中开始加载下一个插屏广告。

## 视频广告初始化和活动 ##
> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

### 视频广告初始化

请在您的Activity里添加以下代码：
```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", adListener);
videoAd.loadAd();
```
视频广告关闭按钮，支持某些网络。如果要启用这个按钮，你应该在视频广告中添加构造布尔参数:

```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled);
或 
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled, adListener);
```

在调用`loadAd()`方法之后，广告下载会立刻开始。如果您在广告被完全处理之前再次调用`loadAd()`，则新的请求处理就被忽略。在这种情况下，只有最后一个请求才会生效。

当插屏广告下载完成后，你可以通过调用`showAd()`方法显示视频广告
```java
videoAd.showAd();
```
为了得知下载是否完成，订阅视频广告事件（ 请见[AD事件处理](#AD事件处理) ）或调用方法`isReady()`。
```java
videoAd.isReady();
```

## 原生和Feed广告，模版和UI ##

>注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

通过NativeAd类加载原生广告，该类在创建期间具有自己的Builder类进行定制：

```java
NativeAd nativeAd = new NativeAd.Builder()
  .setAdUnitId(YOUR_NATIVE_AD_UNIT_ID)
  .setCallback(adListener)
  .build(this);
```

为了使用原生广告，你可以使用两种方法：

* 通过编程方法从模板创建一个广告视图，并将其加入到屏幕。
* 在布局中增加 NativeAdView 视图，并将加载的数据捆绑到此视图。

### 模板

Ampiri SDK 对原生广告提供了三类模板

* FeedCardNativeAdView - 图标、标题、说明、星级评定、和CTA按钮
* StoryCardNativeAdView - 图像、图标、标题、说明、星级评定、和CTA按钮
* VideoCardNativeAdView - 图像、图标/视频/ 图片轮播、标题、说明、星级评定、和CTA按钮

> 每一个模板具有一个标签，清晰的标明了这是一个广告。例如"Ad" 或"Sponsored"。

如果您希望使用模板之一，你可以在NativeAd创建时增加所选的模板： `NativeAd`:

```java
.setAdViewBuilder(FeedCardNativeAdView.BUILDER);
```
对布局增加一个横幅广告位置，例如：
``` xml
  <FrameLayout
    android:id="@+id/ad_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:visibility="gone"/>
```

在调用`loadAd()`方法之后，广告下载会立刻开始。如果您在广告被完全处理之前再次调用`loadAd()`，则新的请求处理就被忽略。在这种情况下，只有最后一个请求会被处理。

当原生广告下载完成后，你可以通过调用`renderAdView()`方法显示广告。

```java
adContainerView = (FrameLayout) view.findViewById(R.id.ad_container);

@Override
public void onAdLoaded() {
  adContainerView.setVisibility(View.VISIBLE);
  adContainerView.removeAllViews();
  adContainerView.addView(nativeAd.renderAdView());
}
```

### 创建自定义原生广告UI

为了创建自定义的原生广告UI，你需要经过以下步骤：

* 创建所有需要的视图（图标视图、主图像视图、文本视图、评级栏等等……）
* 将视图传给我们的SDK
你既可以在布局.xml中创建你的定制视图，也可以在代码中增加元件。

> 所有视图都应当置于一个子类中；该子类本身应当置于`NativeAdView`中。

定制布局`.xml`。例如：

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

在您创建所有视图之后，请继续将视图发送给我们的SDK。例如：

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

在`NativeAd`的创建中注册原生广告视图：

```java
.setAdView(adView);
```

在调用`loadAd()`方法之后，广告下载会立刻开始。如果您在广告被完全处理之前再次调用`loadAd()`，则新的请求处理就被忽略。在这种情况下，只有最后一个请求会被处理。

当原生广告下载完成后，你可以通过调用`renderAdView()`方法显示广告。

To learn about download completion, subscribe to ad events (see [Ad events handling](#ad-events-handling) section) or call method `isReady()`.
```java
nativeAd.isReady();
```

### 自定义原生 UI

随着原生模版，您可以自定以下列元素：

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

为了定制这些元素，您需要构建一个属性对象，并在“NativeAd”的创建中提供以下内容：
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

此外，您可以使用以下属性设置`NativeAdView`布局的样式：

|属性名称| 格式 | 描述 |
|---|---|---|
| `Title` |
| ampiriTitleTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | 标题文本的字体 |
| ampiriTitleTextSize | reference,dimension | 标题文本的大小 |
| ampiriTitleTextColor | reference,color | 标题文本的颜色|
| ampiriTitleTextMaxLengthEms | integer | 标题文本的最大Ems|
| `Icon` |
| ampiriIconWidth | reference,dimension | 图标视图宽度 |
| ampiriIconHeight | reference,dimension |图标视图高度|
| ampiriIconMarginLeft | reference,dimension | 图标查看左偏移|
| ampiriIconMarginRight | reference,dimension | 图标查看右偏移|
| `Star Rating` |
| ampiriStarRatingSize | SMALL, MEDIUM, LARGE | 评级栏星星的尺寸。 SMALL  -  10dp （高度和宽度），中 -  16dp，大 -  22dp|
| `Ad Attribution` |
| ampiriAdAttributionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | 属性标签文字的字体 |
| ampiriAdAttributionTextSize | reference,dimension | 广告属性文字的大小|
| ampiriAdAttributionTextColor | reference,color | 广告属性文字的颜色|
| ampiriAdAttributionDefaultText | reference,string | 默认广告属性文字|
| `Cover Image` |
| ampiriCoverImageBackgroundColor | reference,color | 封面图象背景颜色|
| ampiriCoverImageBackgroundResource | reference | 封面图片背景资源|
| ampiriCoverImageMarginLeft | reference,dimension | 封面图像左偏移|
| ampiriCoverImageMarginRight | reference,dimension | 封面图像右偏移|
| ampiriCoverImageMarginTop | reference,dimension | 封面图像顶偏移|
| ampiriCoverImageMarginBottom | reference,dimension | 封面图像底偏移|
| ampiriCoverImageAlignment | undefine, top, bottom, left, right, center, center_vertical, center_horizontal | 原生广告视图中的封面对齐|
| `Description` |
| ampiriDescriptionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | 描述文字的字体 |
| ampiriDescriptionTextSize | reference,dimension | 描述文字的字体的大小|
| ampiriDescriptionTextColor | reference,color | 描述文字的字体的颜色|
| ampiriTitleTextMaxLengthEms | integer | 描述文字的最大Ems|
| ampiriDescriptionMarginLeft | reference,dimension | 描述视图左偏移|
| ampiriDescriptionMarginRight | reference,dimension | 描述视图右偏移|
| `Call To Action` |
| ampiriCallToActionBackgroundColor |  reference,color | Call To Action操作按钮的颜色 |
| ampiriCallToActionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Call To Action文字的字体|
| ampiriCallToActionTextSize | reference,dimension | Call To Action文字的字体的大小|
| ampiriCallToActionTextColor | reference,color | Call To Action文字的字体的颜色|
| ampiriCallToActionMarginLeft | reference,dimension | Call To Action文字的字体的左偏移|
| ampiriCallToActionMarginRight | reference,dimension | Call To Action文字的字体的右偏移|
| ampiriCallToActionMarginTop | reference,dimension | Call To Action文字的字体的顶偏移|
| ampiriCallToActionMarginBottom | reference,dimension | Call To Action文字的字体的底偏移|
| ampiriCallToActionDefaultText | reference,string | 默认Call To Action文字|

You can set this attributes in your custom `NativeAdView` layout
``` xml
<com.ampiri.sdk.banner.NativeAdView android:id="@+id/native_ad"
    app:ampiriTitleTextFont="MONOSPACE"
    app:ampiriIconWidth="42dp"
    app:ampiriIconHeight="42dp"
    app:ampiriCallToActionTextColor="@android:color/lighter_gray"
 ...>
```

### In-Feed Viewer ###

> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

请在您的Activity里添加以下代码：

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

在调用`loadAd()`方法之后，广告下载会立刻开始。如果您在广告被完全处理之前再次调用`loadAd()`，则新的请求处理就被忽略。在这种情况下，只有最后一个请求会被处理。

当推送广告下载完成时，它将自动显示。

为了得知下载是否完成，订阅广告事件（请见 广告事件处理 章节）。


## 广告回调

为了从广告接收事件，你应当设置一个时间监听器接口AdEventCallback。

监听器示例：
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

## 活动生命周期

`onPause()`, `onResume()` 和 `onDestroy()` 方法应该根据活动生命周期事件来调用。

例如：
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

## Demo（示例）应用/测试

### 步骤

1. 从GitHub下载Ampiri Demo（示例）。
2. 在 **File > New > ImportProject** 下将Ampiri Demo（示例应用程序）导入Android Studio。
3. 在Ampiri用户界面中，将您要使用的广告单元 **的状态设置为** Test。
4. 在Ampiri用户界面中，点击“Click to Copy Ad Unit ID”按钮将广告位置ID复制到剪贴板。
5. 在Android Studio中，在**您要使用的广告单**元的广告类型的JavaActivity中，粘贴步骤4中的广告单元ID。
6. 在Android Studio 主面中，选择**Run**，然后单击**Run**编译和运行应用程序。
7. 在使用Ampiri运行应用程序的Android模拟器（或您的设备），从步骤4中选择广告类型，您将在Ampiri Publishers的自助服务UI下的** Reporting**看到事件数据。

[有关详细SDK主题，请单击此处](CONFIGURATION.md)
