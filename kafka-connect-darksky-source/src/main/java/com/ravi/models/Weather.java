package com.ravi.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static  com.ravi.DarkSkySchema.*;

public class Weather {

   private double latitude;
   private double longitude;
   private String Timezone;
   private Currently currently;
   private List<Currently> data=new ArrayList<>();

   public Weather(){}

    public Weather(double latitude, double longitude, String timezone, Currently currently) {
        this.latitude = latitude;
        this.longitude = longitude;
        Timezone = timezone;
        this.currently = currently;
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

    public void  addData(Currently currently){
       this.data.add(currently);
    }

    public List<Currently> getData(){
       return this.data;
    }

    public static Weather fromJson(JSONObject jsonObject){
       Weather weather = new Weather();
       weather.setLatitude(jsonObject.getDouble(LATITTUDE_FIELD));
       weather.setLongitude(jsonObject.getDouble(LONGITUDE_FIELD));
       weather.setTimezone(jsonObject.getString(TIMEZONE_FIELD));

       Currently currently=Currently.fromJson(jsonObject.getJSONObject(CURRENTLY_FIELD));
       weather.setCurrently(currently);

        JSONArray jsonArray=jsonObject.getJSONObject(HOURLY_FIELD).getJSONArray(DATA_FIELD);
        for (int i =0 ;i <jsonArray.length();i++){
            JSONObject data=jsonArray.getJSONObject(i);
            Currently currently1=Currently.fromJson(data);
            weather.addData(currently);
        }
       return weather;
    }
}
