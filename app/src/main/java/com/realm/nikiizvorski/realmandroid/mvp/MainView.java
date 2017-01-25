package com.realm.nikiizvorski.realmandroid.mvp;

import com.realm.nikiizvorski.realmandroid.realm.WeatherRealm;

import io.realm.Realm;

/**
 * Created by nikiizvorski on 25/01/2017.
 */
public interface MainView {
    /**
     * Load data.
     */
    void loadData();

    /**
     * Init data.
     */
    void initData();

    /**
     * Init views.
     */
    void initViews();

    /**
     * Weather show.
     *
     * @param realm the realm
     */
    void weatherShow(WeatherRealm realm);

    /**
     * Check temp.
     *
     * @param realm the realm
     */
    void checkTemp(WeatherRealm realm);

    void setBackgroundWeather(WeatherRealm realm);

    /**
     * Check name.
     *
     * @param realm the realm
     */
    void checkName(WeatherRealm realm);
}
