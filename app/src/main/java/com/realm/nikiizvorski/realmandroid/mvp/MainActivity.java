package com.realm.nikiizvorski.realmandroid.mvp;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.realm.nikiizvorski.realmandroid.R;
import com.realm.nikiizvorski.realmandroid.di.app.WeatherApp;
import com.realm.nikiizvorski.realmandroid.di.appComponet.AppComponent;
import com.realm.nikiizvorski.realmandroid.di.weatherComponent.DaggerWeatherComponent;
import com.realm.nikiizvorski.realmandroid.di.weatherComponent.WeatherModule;
import com.realm.nikiizvorski.realmandroid.realm.WeatherRealm;
import com.realm.nikiizvorski.realmandroid.util.NetworkUtils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener{
    @Inject MainPresenter mainPresenter;
    @Bind(R.id.timeLabel) TextView time;
    @Bind(R.id.temperatureLabel) TextView degree;
    @Bind(R.id.iconImageView) ImageView background;
    @Bind(R.id.summaryLabel) TextView weatherDesc;
    @Bind(R.id.humidityValue) TextView hummididty;
    @Bind(R.id.precipValue) TextView percentWind;
    @Bind(R.id.locationLabel) EditText editText;
    @Bind(R.id.refreshImageView) ImageView req;

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
        mainPresenter.checkTemp(realm);
    }

    @Override
    public void checkTemp(WeatherRealm realm) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
        String localTime = date.format(currentLocalTime);

        time.setText("At " + localTime + " it will be");
        if (realm != null && realm.getTemp() > 0) {
            setBackground(realm);
            degree.setText(String.valueOf(realm.getTemp()));
            weatherDesc.setText(String.valueOf(realm.getMain()));
            hummididty.setText(String.valueOf(realm.getHummidity()));
            percentWind.setText(String.valueOf(realm.getWindSpeed()));

        }
    }

    @Override
    public void setBackground(WeatherRealm realm) {
        if (realm.getMain().toLowerCase().contains("sun")){
            background.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.sunny));
        } else if (realm.getMain().toLowerCase().contains("snow")){
            background.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.snow));
        } else if (realm.getMain().toLowerCase().contains("cloud")){
            background.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.cloudy));
        } else if (realm.getMain().toLowerCase().contains("rain")){
            background.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.rain));
        } else if (realm.getMain().toLowerCase().contains("fog")){
            background.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.fog));
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
