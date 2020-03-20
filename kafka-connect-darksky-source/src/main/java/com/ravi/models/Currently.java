package com.ravi.models;

import org.json.JSONObject;

import java.sql.Time;
import java.time.Instant;

import static com.ravi.DarkSkySchema.*;

public class Currently {

    private String time;
    private String summary;
    private String icon;
    private int nearestStormDistance;
    private double precipIntensity;
    private double precipIntensityError;
    private double precipProbability;
    private String precipType;
    private double temperature;
    private double apparentTemperature;
    private double dewPoint;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private double windGust;
    private double windBearing;
    private double cloudCover;
    private int uvIndex;
    private double visibility;
    private double ozone;

    public Currently(){

    }

    public Currently(String time, String summary, String icon, int nearestStormDistance, double precipIntensity,
                     double precipIntensityError, double precipProbability, String precipType, double temperature,
                     double apparentTemperature, double dewPoint, double humidity, double pressure, double windSpeed,
                     double windGust, double windBearing, double cloudCover, int uvIndex, double visibility,
                     double ozone) {

        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.nearestStormDistance = nearestStormDistance;
        this.precipIntensity = precipIntensity;
        this.precipIntensityError = precipIntensityError;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
        this.uvIndex = uvIndex;
        this.visibility = visibility;
        this.ozone = ozone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getNearestStormDistance() {
        return nearestStormDistance;
    }

    public void setNearestStormDistance(int nearestStormDistance) {
        this.nearestStormDistance = nearestStormDistance;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public double getPrecipIntensityError() {
        return precipIntensityError;
    }

    public void setPrecipIntensityError(double precipIntensityError) {
        this.precipIntensityError = precipIntensityError;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(double windBearing) {
        this.windBearing = windBearing;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public static  Currently fromJson(JSONObject jsonObject){
        Currently currently = new Currently();
        Instant instant =Instant.ofEpochMilli(jsonObject.getLong(TIME_FIELD)*1000);
        currently.setTime(instant.toString());
        currently.setSummary(jsonObject.getString(SUMMARY_FIELD));
        currently.setIcon(jsonObject.getString(ICON_FIELD));
        currently.setNearestStormDistance(jsonObject.getInt(NEARESTSTORMDISTANCE_FIELD));
        currently.setPrecipIntensity(jsonObject.getDouble(PRECIPINTENCITY_FIELD));
        currently.setPrecipIntensityError(jsonObject.getDouble(PRECIPINTENCITYERROR_FIELD));
        currently.setPrecipProbability(jsonObject.getDouble(PRECIPPROBABILITY_FIELD));
        currently.setPrecipType(jsonObject.getString(PRECIPTYPE_FIELD));
        currently.setTemperature(jsonObject.getDouble(TEMPERATURE_FIELD));
        currently.setApparentTemperature(jsonObject.getDouble(APPARENT_TEMPERATURE_FIELD));
        currently.setDewPoint(jsonObject.getDouble(DEW_POINT_FIELD));
        currently.setHumidity(jsonObject.getDouble(HUMIDITY_FIELD));
        currently.setPressure(jsonObject.getDouble(PRESSURE_FIELD));
        currently.setWindSpeed(jsonObject.getDouble(WIND_SPEED_FIELD));
        currently.setWindGust(jsonObject.getDouble(WIND_GUST_FIELD));
        currently.setWindBearing(jsonObject.getDouble(WIND_BEARING_FIELD));
        currently.setCloudCover(jsonObject.getDouble(CLOUD_COVER_FIELD));
        currently.setUvIndex(jsonObject.getInt(UV_INDEX_FIELD));
        currently.setVisibility(jsonObject.getDouble(VISIBILITY_FIELD));
        currently.setOzone(jsonObject.getDouble(OZONE_FIELD));

        return currently;
    }
}
