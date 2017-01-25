package com.realm.nikiizvorski.realmandroid.di.app;

import android.app.Application;

import com.realm.nikiizvorski.realmandroid.di.appComponet.AppComponent;
import com.realm.nikiizvorski.realmandroid.di.appComponet.AppModule;
import com.realm.nikiizvorski.realmandroid.di.appComponet.DaggerAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class WeatherApp extends Application{

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initDep();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initDep() {
       appComponent = DaggerAppComponent.builder().appModule(new AppModule(this, "http://api.openweathermap.org/data/2.5/")).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
