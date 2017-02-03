package denis.easyweather.app.inject;

import android.content.Context;
import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import denis.easyweather.app.inject.modules.ContextModule;
import denis.easyweather.app.inject.modules.ContextModule_ContextFactory;
import denis.easyweather.app.inject.modules.NetworkModule;
import denis.easyweather.app.inject.modules.NetworkModule_LoggingInterceptorFactory;
import denis.easyweather.app.inject.modules.NetworkModule_OkHttpClientFactory;
import denis.easyweather.app.inject.modules.PicassoModule;
import denis.easyweather.app.inject.modules.PicassoModule_OkHttp3DownloaderFactory;
import denis.easyweather.app.inject.modules.PicassoModule_PicassoFactory;
import denis.easyweather.app.inject.modules.RestApiModule;
import denis.easyweather.app.inject.modules.RestApiModule_GetRestApiFactory;
import denis.easyweather.app.inject.modules.RestApiModule_GsonFactory;
import denis.easyweather.app.inject.modules.RestApiModule_RetrofitFactory;
import denis.easyweather.app.net.WeatherService;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private Provider<OkHttpClient> okHttpClientProvider;

  private Provider<Gson> gsonProvider;

  private Provider<Retrofit> retrofitProvider;

  private Provider<WeatherService> getRestApiProvider;

  private Provider<Context> contextProvider;

  private Provider<OkHttp3Downloader> okHttp3DownloaderProvider;

  private Provider<Picasso> picassoProvider;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.loggingInterceptorProvider =
        NetworkModule_LoggingInterceptorFactory.create(builder.networkModule);

    this.okHttpClientProvider =
        NetworkModule_OkHttpClientFactory.create(builder.networkModule, loggingInterceptorProvider);

    this.gsonProvider =
        DoubleCheck.provider(RestApiModule_GsonFactory.create(builder.restApiModule));

    this.retrofitProvider =
        DoubleCheck.provider(
            RestApiModule_RetrofitFactory.create(
                builder.restApiModule, okHttpClientProvider, gsonProvider));

    this.getRestApiProvider =
        DoubleCheck.provider(
            RestApiModule_GetRestApiFactory.create(builder.restApiModule, retrofitProvider));

    this.contextProvider =
        DoubleCheck.provider(ContextModule_ContextFactory.create(builder.contextModule));

    this.okHttp3DownloaderProvider =
        DoubleCheck.provider(
            PicassoModule_OkHttp3DownloaderFactory.create(
                builder.picassoModule, okHttpClientProvider));

    this.picassoProvider =
        DoubleCheck.provider(
            PicassoModule_PicassoFactory.create(
                builder.picassoModule, contextProvider, okHttp3DownloaderProvider));
  }

  @Override
  public WeatherService getWeatherService() {
    return getRestApiProvider.get();
  }

  @Override
  public Picasso getPicasso() {
    return picassoProvider.get();
  }

  public static final class Builder {
    private NetworkModule networkModule;

    private RestApiModule restApiModule;

    private ContextModule contextModule;

    private PicassoModule picassoModule;

    private Builder() {}

    public AppComponent build() {
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      if (restApiModule == null) {
        this.restApiModule = new RestApiModule();
      }
      if (contextModule == null) {
        throw new IllegalStateException(ContextModule.class.getCanonicalName() + " must be set");
      }
      if (picassoModule == null) {
        this.picassoModule = new PicassoModule();
      }
      return new DaggerAppComponent(this);
    }

    public Builder restApiModule(RestApiModule restApiModule) {
      this.restApiModule = Preconditions.checkNotNull(restApiModule);
      return this;
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    public Builder contextModule(ContextModule contextModule) {
      this.contextModule = Preconditions.checkNotNull(contextModule);
      return this;
    }

    public Builder picassoModule(PicassoModule picassoModule) {
      this.picassoModule = Preconditions.checkNotNull(picassoModule);
      return this;
    }
  }
}
