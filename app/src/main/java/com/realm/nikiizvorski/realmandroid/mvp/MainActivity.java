package com.realm.nikiizvorski.realmandroid.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.realm.nikiizvorski.realmandroid.R;
import com.realm.nikiizvorski.realmandroid.di.appComponet.AppComponent;
import com.realm.nikiizvorski.realmandroid.di.app.WeatherApp;
import com.realm.nikiizvorski.realmandroid.di.weatherComponent.DaggerWeatherComponent;
import com.realm.nikiizvorski.realmandroid.di.weatherComponent.WeatherModule;
import com.realm.nikiizvorski.realmandroid.realm.WeatherRealm;
import com.realm.nikiizvorski.realmandroid.util.ImageHandler;
import com.realm.nikiizvorski.realmandroid.util.NetworkUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener{
    @Inject MainPresenter mainPresenter;
    @Bind(R.id.name) TextView city;
    @Bind(R.id.degree) TextView degree;
    @Bind(R.id.imageView) ImageView background;
    @Bind(R.id.editText) EditText editText;
    @Bind(R.id.request) Button req;

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
        req.setOnClickListener(this);
    }

    @Override
    public void weatherShow(WeatherRealm realm){
        mainPresenter.checkName(realm);
        mainPresenter.checkTemp(realm);
    }

    @Override
    public void checkTemp(WeatherRealm realm) {
        if (realm != null && realm.getTemp() > 0) {
            degree.setText(String.valueOf(realm.getTemp()));

            setBackgroundWeather(realm);

        } else {
            degree.setText(R.string.nodatatemp);
        }
    }

    @Override
    public void setBackgroundWeather(WeatherRealm realm) {
        if(realm.getTemp() > 2){
            Glide.with(this).load(R.drawable.sun).asGif().into(background);
        } else {
            Glide.with(this).load(R.drawable.rain).asGif().into(background);
        }
    }

    @Override
    public void checkName(WeatherRealm realm) {
        if(realm != null && !realm.getName().isEmpty()) {
            city.setText(realm.getName());
        } else {
            city.setText(R.string.nodata);
        }
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

    @Override
    public void onClick(View v) {
        String city = String.valueOf(editText.getText());

        if(city != null && !city.isEmpty()){
            if (NetworkUtils.isNetAvailable(this)) {
                if (mainPresenter.getRealm() != null && !mainPresenter.getRealm().isEmpty()) {
                    mainPresenter.getWeather(city);
                } else {
                    mainPresenter.getWeatherFromRealm(city);
                }
            } else mainPresenter.getWeatherFromRealm(city);
        }
    }
}
