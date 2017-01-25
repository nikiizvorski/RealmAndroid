package com.realm.nikiizvorski.realmandroid.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherRealm extends RealmObject {
    @PrimaryKey
    private String name;
    private Double temp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
