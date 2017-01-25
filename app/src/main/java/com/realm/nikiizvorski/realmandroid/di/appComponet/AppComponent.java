/**
 * Created by nikiizvorski on 13/01/2017.
 */

package com.realm.nikiizvorski.realmandroid.di.appComponet;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
