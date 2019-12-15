package com.example.tikihometest.modules;

import android.app.Application;

import com.example.tikihometest.api.TikiApi;
import com.example.tikihometest.dataservices.TikiService;
import com.example.tikihometest.dataservices.TikiServiceImpl;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetModule {
    private static final long DEFAULT_TIMEOUT = 60;
    private static final String BASE_URL = "https://raw.githubusercontent.com/tikivn/android-home-test/v2/";

    public NetModule() {

    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        return httpClient.build();
    }


    @Provides
    @Singleton
    public TikiApi provideTikiApi(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(TikiApi.class);
    }

    @Provides
    @Singleton
    TikiService provideTikiService(TikiApi api) {
        return new TikiServiceImpl(api);
    }

}
