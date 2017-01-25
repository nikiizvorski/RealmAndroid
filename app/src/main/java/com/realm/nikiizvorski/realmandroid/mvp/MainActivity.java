package com.realm.nikiizvorski.realmandroid.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.realm.nikiizvorski.realmandroid.R;
import com.realm.nikiizvorski.realmandroid.di.appComponet.AppComponent;
import com.realm.nikiizvorski.realmandroid.di.app.WeatherApp;
import com.realm.nikiizvorski.realmandroid.di.weatherComponent.DaggerWeatherComponent;
import com.realm.nikiizvorski.realmandroid.di.weatherComponent.WeatherModule;
import com.realm.nikiizvorski.realmandroid.realm.WeatherRealm;
import com.realm.nikiizvorski.realmandroid.util.NetworkUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{
    @Inject MainPresenter mainPresenter;
    @Bind(R.id.name) TextView city;
    @Bind(R.id.degree) TextView degree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        loadData();
    }

    @Override
    public void loadData() {
        if (NetworkUtils.isNetAvailable(this)) {
            if (mainPresenter.getRealm() != null && !mainPresenter.getRealm().isEmpty()) mainPresenter.getWeatherFromRealm("London");
            else mainPresenter.getWeather("London");
        } else mainPresenter.getWeatherFromRealm("London");
    }

    @Override
    public void initData() {
        DaggerWeatherComponent.builder().appComponent(getAppComponent()).weatherModule(new WeatherModule(this)).build().inject(this);
    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    public void weatherShow(WeatherRealm realm){
        mainPresenter.checkName(realm);
        mainPresenter.checkTemp(realm);
    }

    @Override
    public void checkTemp(WeatherRealm realm) {
        if (realm != null && realm.getTemp() > 0) degree.setText(String.valueOf(realm.getTemp()));
        else degree.setText(R.string.nodatatemp);
    }

    @Override
    public void checkName(WeatherRealm realm) {
        if(realm != null && !realm.getName().isEmpty()) city.setText(realm.getName());
        else city.setText(R.string.nodata);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    protected AppComponent getAppComponent() {
        return ((WeatherApp) getApplication()).getAppComponent();
    }
}
