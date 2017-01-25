package com.realm.nikiizvorski.realmandroid.api;

import com.realm.nikiizvorski.realmandroid.model.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * The interface Weather service.
 */
public interface WeatherService {
    /**
     * Gets weather.
     *
     * @param city   the city
     * @param apiKey the api key
     * @return the weather
     */
    @GET("weather?units=metric")
    Observable<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String apiKey);
}
