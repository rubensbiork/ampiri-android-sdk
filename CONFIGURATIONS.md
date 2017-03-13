# Ampiri SDK Advanced Features

Here you can find advanced SDK topics.

## Contents

* [Ampiri Support](#ampiri-support)
* [Eclipse Integration](#eclipse-integration)
* [Ad Networks Setting](#ad-networks-settings)
* [Avoiding the 65K Limit](#avoiding-the-65k-limit)
* [Log](#log)
* [Debug Mode](#debug-mode)

## Ampiri Support

Additional documentation for integrating the Ampiri SDK with your Android app can be found by clicking the links. 

- [Ampiri.com Tutorials](http://www.ampiri.com/tutorials/) - Tutorials and Sign Up to Ampiri
- [Publisher's Self-Serve UI User Guide](https://ampiri.zendesk.com/hc/en-us/articles/213857245-Publisher-s-Self-Serve-UI-User-Guide) - Publisher's Interface
- [Ampiri SDK Android Quickstart](https://ampiri.zendesk.com/hc/en-us/articles/213431769-Ampiri-SDK-Android-Quickstart) - Basic Integration
- [Ampiri SDK Android Integration Manual](https://ampiri.zendesk.com/hc/en-us/articles/115000510445-Ampiri-SDK-Android-Integration-Manual) - Ampiri SDK Reference Manual
- [Zendesk Support](https://ampiri.zendesk.com/hc/en-us) - Support and FAQ

## User Data

To pass user data to the Ampiri SDK, use the following static methods:
```java
Ampiri.setUserBirthday(data);
Ampiri.setUserGender(UserData.Gender.FEMALE);
Ampiri.setUserInterests(Arrays.asList("football", "auto", "cats")); // Just for example. Please set real interests.
```

## Ad Networks Settings

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

### InLocoMedia

```java
Ampiri.addMediationAdapter(new InLocoMediaMediation.Builder()
    .addTestDevice("HASHED_ID")
    .build());
```

## Log

The default log level is INFO. From the adb shell, you can change the log level to DEBUG, VERBOSE etc. using this command:

```
setprop log.tag.Ampiri_SDK DEBUG
```

```
setprop log.tag.MRAID DEBUG
```

```
setprop log.tag.VAST DEBUG
```

## Debug Mode

If you want to log debug information, please install ``Ampiri.setDebugMode(true)`` (false by default), then you will see the logs under `Ampiri_SDK` tag.
It is recommended that this option should be used for integration test purposes.

## Eclipse Integration ##

Since Google [deprecated] (https://developer.android.com/studio/tools/sdk/eclipse-adt.html) the Eclipse support, we recommend to use Android Studio for the SDK integration.

### Avoiding the 65K Limit ###

If you receive a dex error while adding third party network SDKs and adapters, you may need to enable multidexing in your build.gradle file.

defaultConfig {
  ...
  multiDexEnabled true
  ...
}


Then in your manifest add the `MultiDexApplication` class from the multidex support library to the application element.:

```
<application
  ...
  android:name="android.support.multidex.MultiDexApplication">
  ...
</application>
```

Please see the Android documentation [here](https://developer.android.com/tools/building/multidex.html).

## More Information:

[To go to the basic SDK Integration, click here.](README.md)



