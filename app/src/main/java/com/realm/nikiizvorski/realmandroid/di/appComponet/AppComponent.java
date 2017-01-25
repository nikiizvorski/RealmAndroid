/**
 * Created by nikiizvorski on 13/01/2017.
 */

package com.realm.nikiizvorski.realmandroid.di.appComponet;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


/**
 * The interface App component.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * Expose retrofit retrofit.
     *
     * @return the retrofit
     */
    Retrofit exposeRetrofit();

    /**
     * Expose context context.
     *
     * @return the context
     */
    Context exposeContext();
}
