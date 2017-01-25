package com.realm.nikiizvorski.realmandroid.di.weatherComponent;

import com.realm.nikiizvorski.realmandroid.di.appComponet.AppComponent;
import com.realm.nikiizvorski.realmandroid.di.scope.CustomScope;
import com.realm.nikiizvorski.realmandroid.mvp.MainActivity;

import dagger.Component;

/**
 * The interface Weather component.
 */
@CustomScope
@Component(modules = WeatherModule.class, dependencies = AppComponent.class)
public interface WeatherComponent {
    /**
     * Inject.
     *
     * @param mainActivity the main activity
     */
    void inject(MainActivity mainActivity);
}
