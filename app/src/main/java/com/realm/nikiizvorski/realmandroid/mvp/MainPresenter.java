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

    void getWeather(String city);

    void getWeatherFromRealm(String city);

    Realm getRealm();

    WeatherRealm findInRealm(Realm realm, String name);

    void onDestroy();

    <T> Subscription subscribe(Observable<T> observable, Observer<T> observer);

    void checkName(WeatherRealm realm);

    void checkTemp(WeatherRealm realm);
}
