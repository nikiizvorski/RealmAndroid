package com.realm.nikiizvorski.realmandroid.mvp;

import android.util.Log;

import com.realm.nikiizvorski.realmandroid.api.WeatherService;
import com.realm.nikiizvorski.realmandroid.model.WeatherResponse;
import com.realm.nikiizvorski.realmandroid.realm.WeatherRealm;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter, Observer<WeatherResponse> {
    public static final String TAG = MainPresenterImpl.class.getSimpleName();
    @Inject MainView mainView;
    @Inject WeatherService weatherService;
    private Subscription subscription;

    @Inject
    public MainPresenterImpl(MainView mainView, WeatherService weatherService) {
        this.mainView = mainView;
        this.weatherService = weatherService;
    }

    @Override
    public void getWeather(String city){
        Log.d(TAG, "FromObserver");
        Observable<WeatherResponse> responsePetsObservable = weatherService.getWeather(city, "775c0d25a34bf7c1e01d506881eabfd3");
        subscription = subscribe(responsePetsObservable, this);
    }

    @Override
    public void getWeatherFromRealm(String city) {
        Log.d(TAG, "FromDB");
        Realm realm = Realm.getDefaultInstance();
        WeatherRealm weatherRealm = findInRealm(realm, city);
        mainView.weatherShow(weatherRealm);
        realm.close();
    }

    @Override
    public void checkTemp(WeatherRealm realm) {
        mainView.checkTemp(realm);
    }

    @Override
    public Realm getRealm(){
        return Realm.getDefaultInstance();
    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "Completed");
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.toString());
    }

    @Override
    public void onNext(final WeatherResponse weatherResponse) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                WeatherRealm weatherRealm = findInRealm(realm, weatherResponse.getName());
                if (weatherRealm == null)
                    weatherRealm = realm.createObject(WeatherRealm.class, weatherResponse.getName());
                weatherRealm.setMain(weatherResponse.getWeather().get(0).getMain());
                weatherRealm.setHummidity(weatherResponse.getMain().getHumidity());
                weatherRealm.setWindSpeed(weatherResponse.getWind().getSpeed());
                weatherRealm.setTemp(weatherResponse.getMain().getTemp());
                mainView.weatherShow(weatherRealm);
            }
        });
        realm.close();
    }

    @Override
    public WeatherRealm findInRealm(Realm realm, String name) {
        return realm.where(WeatherRealm.class).equalTo("name", name).findFirst();
    }

    @Override
    public <T> Subscription subscribe(Observable<T> observable, Observer<T> observer) {
        return observable.subscribeOn(Schedulers.newThread()).toSingle().observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    @Override
    public void onDestroy(){
        mainView = null;
        subscription.unsubscribe();
    }
}