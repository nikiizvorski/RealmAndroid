package com.realm.nikiizvorski.realmandroid.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * The type Weather realm.
 */
public class WeatherRealm extends RealmObject {
    @PrimaryKey
    private String name;
    private Double temp;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets temp.
     *
     * @return the temp
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * Sets temp.
     *
     * @param temp the temp
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
