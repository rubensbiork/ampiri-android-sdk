## Version 3.3.2 (November 25, 2016)
- Improvements:
    - Cached data source is independent from activity's lifecycle
    - Ads are shown in in-feed right after the first ad is loaded
- Updated the following dependencies:
    - support-annotations JAR to 25.0.1
    - support-v4 AAR to 25.0.1
    - Facebook Audience Network SDK to 4.17.0
- Fixed:
    - Bug when banner was considered as shown, but was invisible on the screen
    - Miscellaneous bug fixes

## Version 3.3.1 (October 27, 2016)
- Updated the following dependencies:
    - Google Play Services to 9.8.0
- Fixed:
    - Miscellaneous bug fixes
    
## Version 3.3 (October 24, 2016)
- Baidu Ads Network SDK v5.6 integration
- VAST 3.0 support for video ads.
- New templates for native ads. More customization options for templates.
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
- Updated the following dependencies:
    - support-annotations JAR to 25.0.0
    - support-v4 AAR to 25.0.0
    - Google Play Services to 9.6.1
  	- Unity Ads SDK to 2.0.4
  	- Facebook Audience Network SDK to 4.16.1
  	- Applovin SDK to 6.3.2
  	- AdColony SDK to 2.3.6
  	- NativeX SDK to 5.5.8
  	- Vungle SDK to 4.0.2
  	- Chartboost SDK to 6.5.1
- Fixed:
    - Miscellaneous bug fixes

## Version 3.2.4 (September 13, 2016)
- Support Multiple Ad Units
- Added `void setStore(String store)` to `AdColonyMediation.Builder` class
- Updated the following dependencies:
	- AppLovin SDK to 6.3.0
- Fixed:
    - Miscellaneous bug fixes

## Version 3.2.3 (September 9, 2016)
- Changed
  - `void setTitleTextSize(final int titleTextSize)` to `Attributes setTitleTextSize(final int titleTextSize)` in `NativeAdView` class
  - `void setTextTextSize(final int textTextSize)` to `Attributes setTextTextSize(final int textTextSize)` in `NativeAdView` class
- Updated the following dependencies:
	- support-annotations JAR to 24.2.0
	- support-v4 AAR to 24.2.0
	- Google Play Services to 9.4.0
	- MoPub SDK to 4.9.0
- Fixed:
    - Empty template's text and title if sizes are not set
    - Miscellaneous bug fixes

## Version 3.2.2 (August 4, 2016)
- `PlaceId` renamed to `UnitId`
- Added
  - `setAdViewAttributes(NativeAdView.Attributes attributes)` to `NativeAd.Builder` class
  - `setAdViewAttributes(NativeAdView.Attributes attributes)` to `StreamAdAdapter.Builder` class
  - `setAdAttributionText(String adAttributionText)` to `NativeAdView.Attributes` class
  - `setDefaultCallToActionText(String defaultCallToActionText)` to `NativeAdView.Attributes` class
- Renamed
    - `StreamAdAdapter.Builder.setViewBuilder()` to `StreamAdAdapter.Builder.setAdViewBuilder()`
- Removed
    - `setAdAttributionText()` from `NativeAd.Builder` class
    - `setAdAttributionText()` from `StreamAdAdapter.Builder` class
- Updated the following dependencies:
	- support-annotations JAR to 24.1.1
	- Google Play Services to 9.2.1
- Miscellaneous bug fixes

## Version 3.2.1 (July 25, 2016)
- Removed
    - `showAd()` from `NativeAd` class
- Miscellaneous bug fixes

## Version 3.2.0 (July 8, 2016)
- Added Facebook Native Ad Video
- Added `VideoCardNativeAdView` template for support native video ads
- Added close button control for Video Ad
- NativeAd class now can utilize Context instance instead of Activity
- Updated the following dependencies:
	- support-annotations JAR to 24.0.0
	- Google Play Services to 9.2.0
	- Facebook Audience Network SDK to 4.13.0
	- MoPub SDK to 4.7.1
- Miscellaneous bug fixes

## Version 3.1.0 (June 9, 2016)
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

## Version 3.0.3 (May 19, 2016)
- Added support for AppLovin SDK 6.1.5
- Updated the following dependencies:
	- support-annotations JAR to 23.4.0
	- support-v4 AAR to 23.4.0
- Fixed Unity Ads SDK(applifier) maven integration

## Version 3.0.2 (May 5, 2016)
- Vungle Ads Network support updated to SDK 3.3.5
- Fixed:
    - Facebook Audience Network integration
    - Vungle Ads Network integration
    - AdColony Android SDK integration

## Version 3.0.1 (April 14, 2016)
- Facebook Audience Network support updated to SDK 4.11.0
- Removed ResponseStatus.UNKNOWN
- Fixed:
    - Banner update after click
    - Proguard settings
    - Documentation for native ads
