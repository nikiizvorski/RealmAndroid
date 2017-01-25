package com.realm.nikiizvorski.realmandroid.api;

import com.realm.nikiizvorski.realmandroid.model.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Miquel Beltran on 9/24/16
 * More on http://beltran.work
 */
public interface WeatherService {
    @GET("weather?units=metric")
    Observable<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String apiKey);
}
