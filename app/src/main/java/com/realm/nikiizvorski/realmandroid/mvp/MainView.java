package com.realm.nikiizvorski.realmandroid.mvp;

import com.realm.nikiizvorski.realmandroid.realm.WeatherRealm;

import io.realm.Realm;

/**
 * Created by nikiizvorski on 25/01/2017.
 */
public interface MainView {
    void loadData();

    void initData();

    void initViews();

    void weatherShow(WeatherRealm realm);

    void checkTemp(WeatherRealm realm);

    void checkName(WeatherRealm realm);
}
