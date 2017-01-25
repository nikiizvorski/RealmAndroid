package com.realm.nikiizvorski.realmandroid.di.weatherComponent;

import com.realm.nikiizvorski.realmandroid.api.WeatherService;
import com.realm.nikiizvorski.realmandroid.di.scope.CustomScope;
import com.realm.nikiizvorski.realmandroid.mvp.MainPresenter;
import com.realm.nikiizvorski.realmandroid.mvp.MainPresenterImpl;
import com.realm.nikiizvorski.realmandroid.mvp.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * The type Weather module.
 */
@Module
public class WeatherModule {
    private MainView mainView;

    /**
     * Instantiates a new Weather module.
     *
     * @param mainView the main view
     */
    public WeatherModule(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Gets main view.
     *
     * @return the main view
     */
    @CustomScope
    @Provides
    MainView getMainView() {
        return mainView;
    }

    /**
     * Weather service weather service.
     *
     * @param retrofit the retrofit
     * @return the weather service
     */
    @CustomScope
    @Provides
    WeatherService weatherService(Retrofit retrofit){
        return retrofit.create(WeatherService.class);
    }

    /**
     * Main presenter main presenter.
     *
     * @param mainView       the main view
     * @param weatherService the weather service
     * @return the main presenter
     */
    @CustomScope
    @Provides
    MainPresenter mainPresenter(MainView mainView, WeatherService weatherService){
        return new MainPresenterImpl(mainView, weatherService);
    }
}
