package com.realm.nikiizvorski.realmandroid.di.appComponet;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type App module.
 */
@Module
public class AppModule {

    private String mBaseUrl;
    private Context mContext;

    /**
     * Instantiates a new App module.
     *
     * @param context the context
     * @param baseUrl the base url
     */
    public AppModule(Context context, String baseUrl) {
        mContext = context;
        mBaseUrl = baseUrl;
    }

    /**
     * Provide gson converter factory gson converter factory.
     *
     * @return the gson converter factory
     */
    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    /**
     * Provide ok http client 1 ok http client.
     *
     * @return the ok http client
     */
    @Singleton
    @Provides
    @Named("ok-1")
    OkHttpClient provideOkHttpClient1() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Provide ok http client 2 ok http client.
     *
     * @return the ok http client
     */
    @Singleton
    @Provides
    @Named("ok-2")
    OkHttpClient provideOkHttpClient2() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Provide rx java call adapter factory rx java call adapter factory.
     *
     * @return the rx java call adapter factory
     */
    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    /**
     * Provide retrofit retrofit.
     *
     * @param client           the client
     * @param converterFactory the converter factory
     * @param adapterFactory   the adapter factory
     * @return the retrofit
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("ok-1") OkHttpClient client, GsonConverterFactory converterFactory, RxJavaCallAdapterFactory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    /**
     * Provide context context.
     *
     * @return the context
     */
    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

}
