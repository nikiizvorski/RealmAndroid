package com.realm.nikiizvorski.realmandroid.mvp;

import com.realm.nikiizvorski.realmandroid.realm.WeatherRealm;

import io.realm.Realm;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by nikiizvorski on 25/01/2017.
 */
public interface MainPresenter {

    /**
     * Gets weather.
     *
     * @param city the city
     */
    void getWeather(String city);

    /**
     * Gets weather from realm.
     *
     * @param city the city
     */
    void getWeatherFromRealm(String city);

    /**
     * Gets realm.
     *
     * @return the realm
     */
    Realm getRealm();

    /**
     * Find in realm weather realm.
     *
     * @param realm the realm
     * @param name  the name
     * @return the weather realm
     */
    WeatherRealm findInRealm(Realm realm, String name);

    /**
     * On destroy.
     */
    void onDestroy();

    /**
     * Subscribe subscription.
     *
     * @param <T>        the type parameter
     * @param observable the observable
     * @param observer   the observer
     * @return the subscription
     */
    <T> Subscription subscribe(Observable<T> observable, Observer<T> observer);

    /**
     * Check temp.
     *
     * @param realm the realm
     */
    void checkTemp(WeatherRealm realm);
}
