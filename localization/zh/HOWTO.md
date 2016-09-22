# Ampiri Android SDK 3.x 集成指南

* [更新你的Android Manifest](#updating-your-android-manifest)
* [标准横幅广告](#standard-banners)
* [插播广告](#interstitial-ads)
* [视频](#video-ads)
* [原生广告](#native-ads)
* [推送广告](#in-feed-ads)
* [广告事件处理](#ad-events-handling)
* [活动生命周期事件处理](#activity-lifecycle-events-handling)
* [用户数据](#user-data)
* [广告网络设置](#ad-networks-settings)
* [日志](#log)
* [调试模式](#debug-mode)

## Updating your Android Manifest

在主 `<manifest>` 元素下，增加以下许可。

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```

* ACCESS_COARSE_LOCATION（推荐） - 允许SDK基于发射塔访问近似位置。
* ACCESS_FINE_LOCATION（推荐） -  允许SDK基于GPS访问更精确的位置。

尽管技术上没有要求，LOCATION许可使SDK能够向广告商发送基于位置的数据。发送更好的位置数据通常会带来更好的经济效益。

* WRITE_EXTERNAL_STORAGE (optional) -  允许SDK将所有的有用广告数据（创新、用户框架、等等）缓存在外部存储器。这就能通过确保立即投放广告使效果最大化，并通过使缓存的广告数据可用（即使在用户关闭app之后），尽量减少SDK使用的网络流量。

* READ_PHONE_STATE（推荐） -  允许SDK处理视频期间的电话中断视频回放。

> 当将SDK作为库工程使用时，你无需担心合并AndroidManifest.xml变动或Proguard设置。如果你遇到问题，确保在`project.properties`中将`manifestmerger.enabled`设置为`true`。

## Standard banners

> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。 

对布局文件增加一个横幅广告，例如：

```xml
<FrameLayout
    android:id="@+id/ad_view"
    android:layout_width="320dp"
    android:layout_height="50dp"
    android:background="@android:color/white"/>
```

建议在布局中使横幅广告的尺寸与要求的相同（见下文）。否则横幅广告可能会不正确显示。

对你的活动增加以下代码：

```java
FrameLayout adView = (FrameLayout) view.findViewById(R.id.ad_view);
StandardAd standardAd = new StandardAd(this, adView, "YOUR_STANDARD_AD_PLACE_ID", BannerSize.BANNER_SIZE_320x50, adListener);
standardAd.loadAd();
```

默认使用`320*50`横幅广告。可用尺寸：

* 320x50
* 728x90

### Standard banner auto-update

你可以打开或关闭横幅广告的自动更新功能；为此，调用`setAutorefreshEnabled()`方法，例如：

```java
standardAd.setAutorefreshEnabled(false);
```

在默认情况下，自动更新打开。通过管理员面板设置自动更新时段。

## Interstitial Ads

> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

### Interstitial ad initialization

对你的活动增加以下代码：

```java
InterstitialAd interstitialAd = new InterstitialAd(this, "YOUR_INTERSTITIAL_AD_PLACE_ID", adListener);
interstitialAd.loadAd();
```

在调用`loadAd()`方法之后，插播广告开始。如果你在完全处理横幅广告之前再次调用`loadAd()`，之前请求的过程就被取消。在这种情况下，只有最后一个请求会被处理。

当横幅广告下载完成时，你可以通过调用`showAd()`方法显示横幅广告。

```java
interstitialAd.showAd();
```

为了得知下载是否完成，订阅横幅广告事件（请见[Ad 事件处理](#ad-events-handling)）或调用方法`isReady()`。

```java
interstitialAd.isReady();
```

如果你的申请工作流程允许在任何时间和任何位置显示全屏横幅广告，那么还有两种方法可以在加载完成后或在调用方法设置延迟后正确显示。

如果需要加载全屏横幅广告，并在加载后立即显示，则使用：

```java
interstitialAd.loadAndShow()
```

如果需要加载全屏横幅广告，并在调用方法设定延迟后显示，则使用：

```java
interstitialAd.loadAndShowWithDelay()
```

通过Admin UI接口指定延迟间隔。

如果你想要完全控制全屏横幅广告显示的时间和位置，使用以下步骤：

1. 提前调用 `interstitialAd.loadAd()` 。
2. 设置 `AdEventCallback` 处理横幅广告事件。
3. 当你希望显示横幅广告时，检查是否准备就绪，并显示：`if (interstitialAd.isReady()) interstitialAd.showAd()`
4.在`AdEventCallback`的事件处理器 `onAdClosed()`中开始加载下一个横幅广告。

## Video Ads

> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

### Video ad initialization

对你的活动增加以下代码：

```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_PLACE_ID", adListener);
videoAd.loadAd();
```
视频广告关闭按钮，支持某些网络。如果要启用这个按钮，你应该在视频广告中添加构造布尔参数:
```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled);
or
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled, adListener);
```
在调用`loadAd()`方法之后，视频下载开始。如果你在视频开始显示之前再次调用`loadAd()`，则新的请求处理被取消。只有一个请求会被处理。

当视频下载完成时，你可以通过调用`showAd()`方法显示视频。

```java
videoAd.showAd();
```

为了得知下载是否完成，订阅视频横幅广告事件(请见 [Ad 事件处理](#ad-events-handling) 章节) 或调用方法 `isReady()`.

```java
videoAd.isReady();
```

## Native Ads

>注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

通过`NativeAd`类加载原生广告，该类在创建期间具有自己的`Builder`类进行定制：

```java
NativeAd nativeAd = new NativeAd.Builder()
  .setAdUnitId(YOUR_NATIVE_AD_PLACE_ID)
  .setCallback(adListener)
  .setAdAttributionText(getString(R.string.ad_attribution_text))
  .build(this);
```

为了使用原生广告，你可以使用两种方法：

*  通过编程方法从模板创建一个广告视图，并将其加入到屏幕。
*  在布局中增加 `NativeAdView` 视图，并将加载的数据捆绑到此视图。

### Templates

Ampiri SDK 对原生广告提供了三类模板

* FeedCardNativeAdView -  图标、标题、说明、星级评定、和CTA按钮
* StoryCardNativeAdView -  图像、图标、标题、说明、星级评定、和CTA按钮
* VideoCardNativeAdView - 图像、图标/视频/
图片轮播、标题、说明、星级评定、和CTA按钮

>  每一个模板具有一个标签，清晰的标明了这是一个广告。例如"Ad" 或"Sponsored"。

如果你希望使用模板之一，你可以在`NativeAd`创建时增加所选的模板：

```java
.setAdViewBuilder(FeedCardNativeAdView.BUILDER);
```

通过原生模板，你可以定制以下元素：

* Height
* Width
* Background Color
* Title Color
* Title Font
* Description Color
* Description Font
* Button Color
* Button Title Color
* Button Title Font
* Button Border Color

为了定制这些要素，你需要建立一个属性对象，并在`NativeAd`创建时提供以下内容：

```java
.setAdView(FeedCardNativeAdView.BUILDER, new NativeAdView.Attributes()
    .setBackgroundColor(Color.RED)
    .setTitleTextColor(Color.GREEN)
    .setButtonColor(Color.GREEN));
```

对布局增加一个横幅广告位置，例如：

``` xml
  <FrameLayout
    android:id="@+id/ad_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:visibility="gone"/>
```

在调用`loadAd()`方法之后，广告下载开始。如果你在广告被完全处理之前再次调用`loadAd()`，则新的请求处理就被忽略。在这种情况下，只有最后一个请求会被处理。

当横幅广告下载完成时，你可以通过调用`renderAdView()`方法显示横幅广告。

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

为了开始使用原生广告，你需要经过以下步骤：

* 创建所有需要的视图（图标视图、主图像视图、文本视图、评级栏等等……）
* 将视图传给我们的SDK

你既可以在布局`.xml`中创建你的定制视图，也可以在代码中增加元件。

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

在你创建所有视图之后，请继续将视图发送给我们的SDK。例如：

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

在调用`loadAd()`方法之后，广告下载开始。如果你在广告被完全处理之前再次调用`loadAd()`，则新的请求处理就被忽略。在这种情况下，只有最后一个请求会被处理。

当横幅广告下载完成后，你可以通过调用`showAd()` 显示横幅广告。

为了得知下载是否完成，订阅横幅广告事件（请见[广告事件处理](#ad-events-handling)）或调用方法`isReady()`。

```java
nativeAd.isReady();
```

## In Feed Ads

> 注意：所有SDK方法调用都应当从主线程进行（主线程，UI线程）。

对你的活动增加以下代码：

```java
StreamAdAdapter adAdapter = new StreamAdAdapter.Builder()
  .setAdapter(new MainAdapter(this))
  .setAdUnitId("YOUR_NATIVE_AD_UNIT_ID")
  .setViewBuilder(FeedCardNativeAdView.BUILDER)
  .setAdViewAttributes(new NativeAdView.Attributes()
    .setAdAttributionText(R.string.ad_attribution_text)
    .setDefaultCallToActionText(R.string.call_to_action_text))
  .setEventCallback(this)
  .build(this);
```

在调用`loadAd()`方法之后，推送广告下载开始。如果你在完全处理原生广告之前再次调用`loadAd()`，新的请求处理被取消。只有一个请求会被处理。

当推送广告下载完成时，它将自动显示。

为了得知下载是否完成，订阅广告事件（请见 [广告事件处理](#ad-events-handling) 章节）。


## Ad events handling

为了从广告接收事件，你应当设置一个时间监听器接口`AdEventCallback`。

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

## Activity lifecycle events handling

根据活动生命周期事件，应当调用`onPause()`、`onResume()` 和 `onDestroy()` 方法。

示例：

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

## User Data

如果要将用户数据发送给 Ampiri SDK，使用以下静态方法：

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

## Debug mode

如果你希望记录日志调试信息，请安装 `AmpiriLogger.setDebugMode(true)` (默认为false)，然后你将在 `Ampiri_SDK` 标签下看到日志。
建议将此选项用于整合测试。
