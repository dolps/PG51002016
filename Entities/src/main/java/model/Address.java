package model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by dolplads on 05/09/16.
 */
@Embeddable
public class Address {
    private String streetName;
    private String cityCode;
    private String cityName;
    private String countryName;

    public Address(String streetName, String cityCode, String cityName, String countryName) {
        this.streetName = streetName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public Address() {
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
