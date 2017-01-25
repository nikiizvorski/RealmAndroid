package com.realm.nikiizvorski.realmandroid.di.weatherComponent;

import com.realm.nikiizvorski.realmandroid.api.WeatherService;
import com.realm.nikiizvorski.realmandroid.di.scope.CustomScope;
import com.realm.nikiizvorski.realmandroid.mvp.MainPresenter;
import com.realm.nikiizvorski.realmandroid.mvp.MainPresenterImpl;
import com.realm.nikiizvorski.realmandroid.mvp.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WeatherModule {
    private MainView mainView;

    public WeatherModule(MainView mainView) {
        this.mainView = mainView;
    }

    @CustomScope
    @Provides
    MainView getMainView() {
        return mainView;
    }

    @CustomScope
    @Provides
    WeatherService weatherService(Retrofit retrofit){
        return retrofit.create(WeatherService.class);
    }

    @CustomScope
    @Provides
    MainPresenter mainPresenter(MainView mainView, WeatherService weatherService){
        return new MainPresenterImpl(mainView, weatherService);
    }
}
