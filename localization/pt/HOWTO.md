# Ampiri Android SDK 3.x Guia de Integração

* [Atualização do seu Android Manifest](#atualização-do-seu-android-manifest)
* [Banners padrão](#banners-padrão)
* [Intersticiais](#anúncios-intersticiais)
* [Vídeo](#anúncios-em-vídeo)
* [Anúncios nativos](#anúncios-nativos)
* [Anúncios em processo](#anúncios-em-processamento)
* [Manuseamento de eventos publicitários](#manuseamento-de-eventos-de-anúncio)
* [Manuseamento de eventos de ciclo de vida útil da atividade](#manuseamento-de-eventos-de-ciclo-de-vida-útil-da-atividade)
* [Dados do utilizador](#informações-do-utilizador)
* [Configuração de redes de anúncios](#configuracao-de-redes-de-anuncios)
* [Log](#log)
* [Modo de depuração](#modo-de-depuração)

## Suporte Ampiri

* Nota: Os tutorias a seguir estão em Inglês. A versão em Português estará disponível em breve.

Documentos adicionais sobre a integração da Ampiri SDK com o seu Android app pode ser encontrado clicando nos links.
- [Tutoriais Ampiri.com](http://www.ampiri.com/tutorials/) - Tutoriais da Ampiri
- [Guia do desenvolvedor](https://ampiri.zendesk.com/hc/en-us/articles/213857245-Publisher-s-Self-Serve-UI-User-Guide) - Guia do desenvolvedor
- [Começando com Ampiri SDK Android](https://ampiri.zendesk.com/hc/en-us/articles/213431769-Ampiri-SDK-Android-Quickstart)  - Integração Básica
- Em breve o manual completo da Ampiri SDK!


## Atualização do seu Android Manifest

No elemento principal `<manifest>`, adicionar as seguintes permissões.

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```

* ACCESS\_COARSE\_LOCATION (recomendado) – Garante a permissão SDK para aceder à localização aproximada com base na torre celular.
* ACCESS\_FINE\_LOCATION (recomendado) – Garante a permissão SDK para aceder a uma localização mais exata com base no GPS.

Embora não seja tecnicamente necessário, as permissões de LOCALIZAÇÃO permitem ao SDK enviar informações com base na localização aos anunciantes. O envio de melhores
informações de localização originam geralmente uma melhor monetização.

* WRITE\_EXTERNAL\_STORAGE (opcional) – Permite ao SDK armazenar em cache todos os anúncios (criativos, estruturas personalizadas, etc.) na memória externa. Isto pode melhorar
o desempenho garantindo um fornecimento imediato de anúncios e reduzindo o tráfego na rede utilizada pela rede utilizada pelo SDK mantendo armazenados em cache
os anúncios disponíveis mesmo após o utilizador fechar a app.

* READ\_PHONE\_STATE (recomendado) – Permite ao SDK gerir as chamadas que interrompem a reprodução de vídeos.

> Quando se utiliza o SDK como um projeto de biblioteca, não é necessário se preocupar com as alterações emergentes do AndroidManifest.xml ou com as configurações Proguard. Se encontrar problemas, certifique-se que `manifestmerger.enabled` está configurado para `true` em `project.properties`

## Banners padrão

> Nota: Todas as chamadas do método SDK devem ser efetuadas a partir da linha principal (Linha principal, Linha UI).

Adicionar um banner ao ficheiro de configuração, ex:

```xml
<FrameLayout
    android:id="@+id/ad_view"
    android:layout_width="320dp"
    android:layout_height="50dp"
    android:background="@android:color/white"/>
```

Recomenda-se que o tamanho do banner na disposição seja o mesmo do requerido (ver abaixo). Caso contrário, o banner pode-se ser visualizado incorretamente.

Adicionar o seguinte código à sua atividade:

```java
FrameLayout adView = (FrameLayout) view.findViewById(R.id.ad_view);
StandardAd standardAd = new StandardAd(this, adView, "YOUR_STANDARD_AD_PLACE_ID", BannerSize.BANNER_SIZE_320x50, adListener);
standardAd.loadAd();
```

Os banners de `320*50` são do tamanho padrão. Tamanhos disponíveis:

* 320x50
* 728x90

### Auto atualização do banner padrão

Pode-se ligar ou desligar a função de auto atualização do banner; para efetuar esta ação, chame o método `setAutorefreshEnabled()`, ex:

```java
standardAd.setAutorefreshEnabled(false);
```

Por padrão, a auto atualização está ligada. O período de auto atualização é configurado no painel de administração.

## Anúncios intersticiais

> Nota: Todas as chamadas do método SDK devem ser efetuadas a partir da linha principal (Linha principal, Linha UI).

### Inicialização do anúncio intersticial

Adicione o seguinte código à sua atividade:

```java
InterstitialAd interstitialAd = new InterstitialAd(this, "YOUR_INTERSTITIAL_AD_UNIT_ID", adListener);
interstitialAd.loadAd();
```

Após ativar o método `loadAd()`, inicia-se a transferência intersticial. Se ativar novamente `loadAd()` antes do banner ser totalmente enviado, o processamento do pedido anterior é cancelado. Neste caso, apenas será processado o último pedido.

Quando a transferência do banner estiver concluída, pode-se visualizar o banner ativando o método `showAd()`.

```java
interstitialAd.showAd();
```

Para mais informações sobre a conclusão da transferência, subscrever os eventos do banner (ver [Manuseamento de eventos de anúncios](#manuseamento-de-eventos-de-anúncio)) ou ative o método `isReady()`.

```java
interstitialAd.isReady();
```
Se o fluxo de trabalho da sua aplicação permitir mostrar os banners em tela cheia em qualquer momento e em qualquer lugar, existem 2 formas adicionais para os mostrar logo após a conclusão da transferência ou com um atraso personalizado após a ativação do método.

Para carregar e mostrar o banner em tela cheia logo após ter sido carregado, utilizar:

```java
interstitialAd.loadAndShow()
```
Para carregar e mostrar o banner em tela cheia com um atraso personalizado após a ativação do método utilizar:

```java
interstitialAd.loadAndShowWithDelay()
```
O intervalo de atraso é especificado na interface Admin UI.

Se pretender ter um controle total em relação a quando e onde mostrar os banners em tela cheia, deverá utilizar os seguintes passos:

1. Ative `interstitialAd.loadAd()` antecipadamente
2. Configure `AdEventCallback` para gerir os eventos do banner
3. Quando pretender mostar o banner, verificar se está pronto e mostrar: `if (interstitialAd.isReady()) interstitialAd.showAd()`
4. Inicie o carregamento do banner seguinte no gestor de eventos `onAdClosed()` do `AdEventCallback`

## Anúncios em vídeo

> Nota: Todos as chamada do método SDK devem ser efetuadas a partir da linha principal (Linha principal, Linha UI).

### Inicialização do anúncio em vídeo

Adicione o seguinte código à sua atividade:

```java
VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_PLACE_ID", adListener);
videoAd.loadAd();
```

O botão fechar é suportado apenas por algumas redes de anúncios. Para habilitar este botão você deve adicionar um parâmetro booleano no construtor de VideoAd:
```java
   VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled);
   or
   VideoAd videoAd = new VideoAd(this, "YOUR_VIDEO_AD_UNIT_ID", closeButtonEnabled, adListener);
```

Após chamar o método `loadAd()`, inicia-se a transferência do vídeo. Se chamar novamente `loadAd()` antes de iniciar a visualização do vídeo, a nova chamada será cancelada. Será apenas aceita a primeira chamada realizada.

Quando a transferência do vídeo estiver concluída, pode-se visualizar ativando o método `showAd()`.
```java
videoAd.showAd();
```

Para mais informações sobre a conclusão da transferência, subscrever os eventos do banner de vídeo (ver a secção [Manuseamento de eventos de anúncios](#manuseamento-de-eventos-de-anúncio)) ou ative o método `isReady()`.
```java
videoAd.isReady();
```

## Anúncios nativos

>Nota: Todos as chamada do método SDK devem ser efetuadas a partir da linha principal (Linha principal, Linha UI).

Os anúncios nativos são carregados através da classe `NativeAd`, a qual possui a sua própria classe `Builder` para personalizar durante a criação:

```java
NativeAd nativeAd = new NativeAd.Builder()
  .setAdUnitId(YOUR_NATIVE_AD_UNIT_ID)
  .setCallback(adListener)
  .build(this);
```

Para mostrar os anúncios nativos, pode-se utilizar dois métodos:

* Crie uma vista de anúncio de forma programática a partir do modelo e adicione-o no tela.
* Adicione a vista `NativeAdView` na disposição e vincule as informações carregadas.

### Modelos

O Ampiri SDK permite 2 tipos de modelos para os anúncios nativos

* FeedCardNativeAdView - Ícone, título, descrição, classificação por estrelas e tecla CTA
* StoryCardNativeAdView - Icon, imagem, ícone, título, descrição, classificação por estrelas e tecla CTA
* VideoCardNativeAdView - Icon, image/video/carousel, title, description, star rating, and CTA button

> Todos os modelos incluem uma etiqueta que indica claramente que é um anúncio. Por exemplo, "Ad" ou "Sponsored".

Se pretender utilizar um destes modelos, pode-se adicionar o modelo selecionado na criação do `NativeAd`:

```java
.setAdViewBuilder(FeedCardNativeAdView.BUILDER);
```

Adicione um local do banner na disposição, ex:

``` xml
  <FrameLayout
    android:id="@+id/ad_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:visibility="gone"/>
```


Após ativar o método `loadAd()`, inicia-se a transferência do anúncio. Se ativar novamente `loadAd()` antes do banner ser totalmente enviado, o processamento do novo pedido será
ignorado. Neste caso, apenas será processado o último pedido.

Quando a transferência do banner estiver concluída, pode-se visualizar o banner ativando o método `renderAdView()`.

```java
adContainerView = (FrameLayout) view.findViewById(R.id.ad_container);

@Override
public void onAdLoaded() {
  adContainerView.setVisibility(View.VISIBLE);
  adContainerView.removeAllViews();
  adContainerView.addView(nativeAd.renderAdView());
}
```

### Crie uma UI nativa

Para criar anúncios customizados nativos, é necessário efetuar os seguintes passos:

* Crie todas as visualizações necessárias (visualização do ícone, visualização da imagem principal, visualizações do texto, barra de classificação, etc...)
* Transfira as visualizações para o nosso SDK

Pode-se criar as suas visualizações personalizadas numa configuração `.xml`, ou pode-se adicionar elementos ao código.

> Todas as visualizações devem ser colocadas num nó sucessor; este nó sucessor deve ser colocado em `NativeAdView`.

Custom layout `.xml`. Por exemplo:

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

Após ter criado todas as visualizações, por favor continue passando as visualizações para o nosso SDK. Por exemplo:

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

Registrando a visualização do anúncio nativo na criação do `NativeAd`:

```java
.setAdView(adView);
```

Após ativar o método `loadAd()`, inicia-se a transferência do anúncio. Se ativar novamente `loadAd()` antes do banner ter sido totalmente enviado, o processamento do novo pedido
será ignorado. Neste caso, apenas será processado o último pedido.

Quando a transferência do banner estiver concluída, pode-se visualizar o banner ativando o método `showAd()`.

Para mais informações sobre a conclusão da transferência, subscrever os eventos de anúncio (ver a secção [Manuseamento de eventos de anúncio](#manuseamento-de-eventos-de-anúncio)) ou ative o método `isReady()`.
```java
nativeAd.isReady();
```
### Customizar anúncios nativos

Com um template nativo, você pode customizar os seguintes elementos:

* Título
    * Fonte do texto (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
    * Tamanho do texto
    * Cor do texto
    * Máximo ems (25 por padrão)
* Ícone
    * Dimensões(width, height)
    * Margens (left, right)
* Star rating (Estrelas para pontuação)
  * Tamanho (SMALL, MEDIUM, LARGE)
* Label
  * Texto padrão
  * Fonte do texto (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
  * Tamanho do texto
  * Cor do texto
* Imagem da capa
    * Cor de fundo
    * Conteúdo do fundo
    * Margem (left, right, bottom, top)
    * Alinhamento (undefine, top, bottom, left, right, center, center_vertical, center_horizontal
* Descrição
    * Fonte do texto (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
    * Tamanho do texto
    * Cor do texto
    * Máximo ems (25 por padrão)
* Chamada do botão de ação
    * Texto padrão
    * Fonte do texto (DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF)
    * Tamanho do texto
    * Cor do texto
    * Máximo ems (25 por padrão)
    * Cor de fundo
    * Conteúdo do fundo
    * Margem (left, right)


Para customizar estes elementos, você vai precisar criar um objeto com atributos e passar o seguinte código na criação do `NativeAd`:
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

Também, você pode definir o estilo do seu `NativeAdView` layout usando os seguintes atributos:
| nome do atributo | formato | descrição |
|---|---|---|
| `Title` |
| ampiriTitleTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Fonte do texto do título |
| ampiriTitleTextSize | reference,dimension | Tamanho do texto do título |
| ampiriTitleTextColor | reference,color | Cor do texto do título |
| ampiriTitleTextMaxLengthEms | integer | Máximo ems do texto do título|
| `Icon` |
| ampiriIconWidth | reference,dimension | Largura do ícone |
| ampiriIconHeight | reference,dimension | Altura do ícone|
| ampiriIconMarginLeft | reference,dimension | Margem esquerda do ícone |
| ampiriIconMarginRight | reference,dimension | Margem direita do ícone |
| `Star Rating` |
| ampiriStarRatingSize | SMALL, MEDIUM, LARGE | Tamanho da barra de estrelas. SMALL - 10dp para altura e largura, MEDIUM - 16dp, LARGE - 22dp |
| `Ad Attribution` |
| ampiriAdAttributionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Fonte do texto do label de atribuição |
| ampiriAdAttributionTextSize | reference,dimension | Tamanho do texto do label de atribuição |
| ampiriAdAttributionTextColor | reference,color | Cor do texto do label de atribuição|
| ampiriAdAttributionDefaultText | reference,string | Padrão do texto do label de atribuição |
| `Cover Image` |
| ampiriCoverImageBackgroundColor | reference,color | Cor do fundo da imagem da capa |
| ampiriCoverImageBackgroundResource | reference | Conteúdo do fundo da imagem da capa |
| ampiriCoverImageMarginLeft | reference,dimension | Margem esquerda da imagem da capa|
| ampiriCoverImageMarginRight | reference,dimension | Margem direita da imagem da capa|
| ampiriCoverImageMarginTop | reference,dimension | Margem de cima da imagem da capa |
| ampiriCoverImageMarginBottom | reference,dimension | Margem de baixo da imagem da capa |
| ampiriCoverImageAlignment | undefine, top, bottom, left, right, center, center_vertical, center_horizontal | Alinhamento da imagem da capa dentro da NativeAd view |
| `Description` |
| ampiriDescriptionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Fonte do texto da descrição |
| ampiriDescriptionTextSize | reference,dimension | Tamanho do texto da descrição|
| ampiriDescriptionTextColor | reference,color | Cor do texto da descrição |
| ampiriTitleTextMaxLengthEms | integer | Máximo ems do texto da descrição |
| ampiriDescriptionMarginLeft | reference,dimension | Margem esquerda da descrição |
| ampiriDescriptionMarginRight | reference,dimension | Margem direita da descrição |
| `Call To Action` |
| ampiriCallToActionBackgroundColor |  reference,color | Cor do botão de chamada de ação |
| ampiriCallToActionTextFont | DEFAULT, DEFAULT_BOLD, MONOSPACE, SANS_SERIF, SERIF | Fonte do texto da descrição |
| ampiriCallToActionTextSize | reference,dimension | Tamanhao  do botão de chamada de ação |
| ampiriCallToActionTextColor | reference,color | Cor do botão de chamada de ação |
| ampiriCallToActionMarginLeft | reference,dimension | Margem esquerda do botão de chamada de ação |
| ampiriCallToActionMarginRight | reference,dimension | Margem direita do botão de chamada de ação |
| ampiriCallToActionMarginTop | reference,dimension | Margem de cima do botão de chamada de ação |
| ampiriCallToActionMarginBottom | reference,dimension | Margem de baixo do botão de chamada de ação|
| ampiriCallToActionDefaultText | reference,string | Texto padrão do botão de chamada de ação |


## Anúncios em processamento

> Nota: Todos as chamada do método SDK devem ser efetuadas a partir da linha principal (Linha principal, Linha UI).

Adicionar o seguinte código à sua atividade:

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


Após ativar o método `loadAd()`, inicia-se a transferência do anúncio em processamento. Se ativar novamente `loadAd()` antes do anúncio nativo ter sido totalmente enviado, o processamento do novo pedido será cancelado. Apenas será processado um pedido.

Quando estiver concluída a transferência do anúncio em processamento, será automaticamente visualizado.

Para mais informações sobre a conclusão da transferência, subscrever os eventos de anúncio (ver secção [Manuseamento de eventos de aníncio](#manuseamento-de-eventos-de-anúncio)).


## Manuseamento de eventos de anúncio

Para receber eventos de anúncio, deve-se implementar uma interface de ouvinte de evento `AdEventCallback`.

Exemplo de ouvinte:

```java
AdEventCallback adListener = new AdEventCallback() {
    @Override
    public void onAdLoaded() {
      //Chamado quando um anúncio é recebido
    }

    @Override
    public void onAdFailed(@NonNull final ResponseStatus responseStatus) {
      //Chamado quando uma requisição de anúncio falha por causa de um erro ou por não ter sido preenchida
    }

    @Override
    public void onAdOpened() {
      //Chamado quando um anúncio é mostrado
    }

    @Override
    public void onAdClicked() {
      //Chamado quando um usuário clica em um anúncio
    }

    @Override
    public void onAdClosed() {
      //Chamado quando o usuário está quase fechando um banner ou retorna para a aplicação depois de clicar em um anúncio
    }

    @Override
    public void onAdCompleted() {
      //Chamado quando o video foi mostrado até o final
    }
};
```

## Manuseamento de eventos de ciclo de vida útil da atividade

Os métodos `onPause()`, `onResume()` e `onDestroy()` devem ser ativados dependendo dos eventos do ciclo de vida útil da atividade.

Exemplo:

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

## Informações do utilizador

Para transmitir as informações do utilizador ao Ampiri SDK, deve-se utilizar os seguintes métodos estáticos:

```java
Ampiri.setUserBirthday(data);;
Ampiri.setUserGender(UserData.Gender.FEMALE);
Ampiri.setUserInterests(Arrays.asList("football", "auto", "cats")); // Just for example. Please set real interests.
```

## Configuração de redes de anúncios

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

O nível de log por defeito é INFO. A partir da estrutura adb, pode-se alterar o nível de log para DEBUG, VERBOSE etc. utilizando estes comandos:

```
setprop log.tag.Ampiri_SDK DEBUG
```

```
setprop log.tag.MRAID DEBUG
```

```
setprop log.tag.VAST DEBUG
```

## Modo de depuração

Caso pretenda registar as informações de depuração, por favor setar `AmpiriLogger.setDebugMode(true)` (false por padrão), então poderá ver os logs com a tag `Ampiri_SDK`.
Recomenda-se a utilização desta opção para efeitos de teste de integração.
