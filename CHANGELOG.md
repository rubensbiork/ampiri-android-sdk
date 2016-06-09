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
