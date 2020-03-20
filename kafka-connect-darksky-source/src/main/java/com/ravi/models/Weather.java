package com.ravi.models;

import org.json.JSONObject;
import static  com.ravi.DarkSkySchema.*;

public class Weather {

   private double latitude;
   private double longitude;
   private String Timezone;
   private Currently currently;
   private Weather weather;

   public Weather(){}

    public Weather(double latitude, double longitude, String timezone, Currently currently, Weather weather) {
        this.latitude = latitude;
        this.longitude = longitude;
        Timezone = timezone;
        this.currently = currently;
        this.weather = weather;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return Timezone;
    }

    public void setTimezone(String timezone) {
        Timezone = timezone;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }


    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public static Weather fromJson(JSONObject jsonObject){
       Weather weather = new Weather();
       weather.setLatitude(jsonObject.getDouble(LATITTUDE_FIELD));
       weather.setLongitude(jsonObject.getDouble(LONGITUDE_FIELD));
       weather.setTimezone(jsonObject.getString(TIMEZONE_FIELD));

       Currently currently=Currently.fromJson(jsonObject.getJSONObject(CURRENTLY_FIELD));
       weather.setCurrently(currently);

       return weather;
    }
}
