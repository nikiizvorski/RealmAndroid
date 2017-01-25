package com.realm.nikiizvorski.realmandroid.di.weatherComponent;

import com.realm.nikiizvorski.realmandroid.di.appComponet.AppComponent;
import com.realm.nikiizvorski.realmandroid.di.scope.CustomScope;
import com.realm.nikiizvorski.realmandroid.mvp.MainActivity;

import dagger.Component;

@CustomScope
@Component(modules = WeatherModule.class, dependencies = AppComponent.class)
public interface WeatherComponent {
    void inject(MainActivity mainActivity);
}
