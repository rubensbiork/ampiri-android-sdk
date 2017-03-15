# Funções Avançadas do Ampiri SDK

Aqui você pode encontrar tópicos avançados sobre o Ampiri SDK.

## Conteúdo

* [Suporte Ampiri](#Suporte-Ampiri)
* [Dados do Usuário](#Dados-do-Usuário)
* [Configurações de rede de anúncios](#Configurações-de-rede-de-anúncios)
* [Log](#Log)
* [Modo Debug](#Modo-Debug)
* [Integração com Eclipse](#Integração-com-Eclipse)
* [Evitando o limite de 65K](#Evitando-o-limite-de-65K)

## Suporte Ampiri ##

Documentos adicionais sobre a integração do Ampiri SDK com o seu Android app pode ser encontrado clicando nos links.

- [Tutoriais Ampiri.com](http://www.ampiri.com/tutorials/) - Tutoriais Ampiri
- [Guia do desenvolvedor](https://ampiri.zendesk.com/hc/en-us/articles/213857245-Publisher-s-Self-Serve-UI-User-Guide) - Guia do desenvolvedor
- [Começando com Ampiri SDK Android](https://ampiri.zendesk.com/hc/en-us/articles/213431769-Ampiri-SDK-Android-Quickstart) - Integração Básica
- [Manual de Integração Ampiri SDK Android](https://ampiri.zendesk.com/hc/en-us/articles/115000510445-Ampiri-SDK-Android-Integration-Manual) - Manual de Integração Ampiri SDK
- [Zendesk Support](https://ampiri.zendesk.com/hc/en-us) - Suporte e FAQ

## Dados do Usuário

Para transmitir as informações de uso ao Ampiri SDK, deve-se usar os seguintes métodos estáticos:
```java
Ampiri.setUserBirthday(data);
Ampiri.setUserGender(UserData.Gender.FEMALE);
Ampiri.setUserInterests(Arrays.asList("football", "auto", "cats")); // Apenas exemplos. Por favor define interesses reais.
```

## Configurações de rede de anúncios

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
