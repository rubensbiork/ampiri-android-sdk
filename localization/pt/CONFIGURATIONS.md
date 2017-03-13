# Funções Avançadas do Ampiri SDK

Aqui você pode encontrar tópicos avançados sobre o Ampiri SDK.

## Conteúdo

* [Suporte Ampiri](#Suporte-Ampiri)
* [Suporte Para Redes e Tipos de Anúncios](#Suporte-Para-Redes-e-Tipos-de-Anúncios)
* [Integração com Eclipse](#Integração-com-Eclipse)
* [Configurações de rede de anúncios](#Configurações-de-rede-de-anúncios)
* [Evitando o limite de 65K](#Evitando-o-limite-de-65K)
* [Log](#Log)
* [Modo Debug](#Modo-Debug)

## Suporte Ampiri ##

Documentos adicionais sobre a integração do Ampiri SDK com o seu Android app pode ser encontrado clicando nos links.

- [Tutoriais Ampiri.com](http://www.ampiri.com/tutorials/) - Tutoriais Ampiri
- [Guia do desenvolvedor](https://ampiri.zendesk.com/hc/en-us/articles/213857245-Publisher-s-Self-Serve-UI-User-Guide) - Guia do desenvolvedor
- [Começando com Ampiri SDK Android](https://ampiri.zendesk.com/hc/en-us/articles/213431769-Ampiri-SDK-Android-Quickstart) - Integração Básica
- [Manual de Integração Ampiri SDK Android](https://ampiri.zendesk.com/hc/en-us/articles/115000510445-Ampiri-SDK-Android-Integration-Manual) - Manual de Integração Ampiri SDK
- [Zendesk Support](https://ampiri.zendesk.com/hc/en-us) - Suporte e FAQ

## Suporte Para Redes e Tipos de Anúncios ##

Ampiri supports the following ad networks on the client-side:

Para mostrar anúncio de *banner padrão*:

| Especificação | Rede | Versão |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/audience-network)|v4.12.0, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-facebook`| [Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-inlocomedia`| [InLocoMedia](http://docs.inlocomedia.com)| v2.5.0, API 14: Android 4.0 (ICE_CREAM_SANDWICH)|

Para mostrar anúncio *intersticial*:

| Especificação | Rede | Versão |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/audience-network)|v4.12.0, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-chartboost`|[Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android)|v6.6.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-facebook`|[Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-nativex`|[NativeX](https://github.com/nativex/NativeX-Android-SDK)|v5.5.9, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-applovin`|[AppLovin](https://github.com/AppLovin/Android-Demo-App)|v6.4.2, API 9: Android 2.3 (Gingerbread)|

Para mostrar anúncio de *vídeo*:

| Especificação | Rede | Versão |
|----------|----------|----------|
|`ampiri-sdk-mediation-adcolony`|[AdColony](https://github.com/AdColony/AdColony-Android-SDK)|v2.3.6, API 14: Android 4.0 (Ice Cream Sandwich)|
|`ampiri-sdk-mediation-unityads`|[Unity Ads](https://github.com/Applifier/unity-ads-sdk)|v2.0.8, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-chartboost`|[Chartboost](https://answers.chartboost.com/hc/en-us/articles/201219545-Download-Integrate-the-Chartboost-SDK-for-Android)|v6.6.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-nativex`|[NativeX](https://github.com/nativex/NativeX-Android-SDK)|v5.5.9, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-vungle`|[Vungle](https://v.vungle.com/sdk)|v4.0.3, API 11: Android 3.0 (Honeycomb)|

Para mostrar anúncio *nativo*:

| Especificação | Rede | Versão |
|----------|----------|----------|
|`ampiri-sdk-mediation-admob`|[Google Mobile Ads](https://developers.google.com/admob/android/quick-start)|v10.0.1, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-mopub`|[MoPub](https://github.com/mopub/audience-network)|v4.12.0, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-facebook`|[Facebook Audience](https://developers.facebook.com/docs/audience-network)|v4.20.0, API 11: Android 3.0 (Honeycomb)|
|`ampiri-sdk-mediation-applovin`|[AppLovin](https://github.com/AppLovin/Android-Demo-App)|v6.4.2, API 9: Android 2.3 (Gingerbread)|
|`ampiri-sdk-mediation-baidu`|[Baidu](http://mssp.baidu.com/app/static/main.html#/sdk)|v5.6, API 8: Android 2.2 (Froyo)|

> Não podemos garantir um funcionamento estável do SDK se você usar outra versão destas bibliotecas.

Você precisa configurar cada rede de terceiro no site da Ampiri, caso contrário elas não irão ser usadas para mostrar anúncios.

## Dados do Usuário

Para transmitir as informações de uso ao Ampiri SDK, deve-se usar os seguintes métodos estáticos:
```java
Ampiri.setUserBirthday(data);
Ampiri.setUserGender(UserData.Gender.FEMALE);
Ampiri.setUserInterests(Arrays.asList("football", "auto", "cats")); // Apenas exemplos. Por favor define interesses reais.
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

## Log ##

O nível de log padrão é INFO. A partir da adb shell, você pode alterar o nível de log para DEBUG, VERBOSE e etc. Usando estes comandos:
```
setprop log.tag.Ampiri_SDK DEBUG
```

```
setprop log.tag.MRAID DEBUG
```

```
setprop log.tag.VAST DEBUG
```

## Modo Debug ##

Caso pretenda registar as informações de debug, por favor setar `Ampiri.setDebugMode(true)` (false por padrão), então poderá ver os logs com a tag `Ampiri_SDK`.
Recomenda-se a utilização desta opção para efeitos de teste de integração.

## Integração com Eclipse ##

Desde que a Google tornou obsoleto o suporte ao Eclipse, recomendamos o uso do Android Studio para a integração do SDK.

### Evitando o limite de 65K ###

Caso haja erro de dex quando adicionar SDKs e adaptadores de rede de terceiros, pode ser necessário ativar o multidexing no seu arquivo build.gradle.

defaultConfig {
  ...
  multiDexEnabled true
  ...
}

Então adicione ao seu manifest a classe `MultiDexApplication` da sua biblioteca de suporte multidex para o elemento da aplicação:

```
<application
  ...
  android:name="android.support.multidex.MultiDexApplication">
  ...
</application>
```

Por favor consultar a documentação Android [aqui](https://developer.android.com/tools/building/multidex.html).

## Mais Informações:

[Para acessar o manual básico de integração com o SDK, clique aqui.](README.md)
