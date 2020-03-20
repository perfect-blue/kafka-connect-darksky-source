package com.ravi.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.Assert.*;


public class ModelTest {
    String weatherStr=
            "{\n"+
                    "\"latitude\": 42.3601,\n"+
                    "\"longitude\": -71.0589,\n"+
                    "\"timezone\": \"America/New_York\",\n"+
            "\"currently\": {\n"+
            "\"time\": 1509993277,\n"+
            "\"summary\": \"Drizzle\",\n"+
            "\"icon\": \"rain\",\n"+
            "\"nearestStormDistance\": 0,\n" +
            "\"precipIntensity\": 0.0089,\n" +
            "\"precipIntensityError\": 0.0046,\n" +
            "\"precipProbability\": 0.9,\n" +
            "\"precipType\": \"rain\",\n" +
            "\"temperature\": 66.1,\n"+
            "\"apparentTemperature\": 66.31,\n" +
            "\"dewPoint\": 60.77,\n" +
            "\"humidity\": 0.83,\n" +
            "\"pressure\": 1010.34,\n" +
            "\"windSpeed\": 5.59,\n" +
            "\"windGust\": 12.03,\n" +
            "\"windBearing\": 246,\n" +
            "\"cloudCover\": 0.7,\n" +
            "\"uvIndex\": 1,\n" +
            "\"visibility\": 9.84,\n" +
            "\"ozone\": 267.44\n" +
            "},\n" +
            "\"minutely\": { \n" +
            "\"summary\": \"Light rain stopping in 13 min., starting again 30 min. later.\",\n" +
            "\"icon\": \"rain\",\n" +
            "\"data\": [{\n" +
            "\"time\": 1509993240,\n" +
            "\"precipIntensity\": 0.007,\n" +
            "\"precipIntensityError\": 0.004,\n" +
            "\"precipProbability\": 0.84,\n" +
            "\"precipType\": \"rain\" \n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "\"hourly\": {\n" +
            "\"summary\": \"Rain starting later this afternoon, continuing until this evening.\",\n" +
            "\"icon\": \"rain\",\n" +
            "\"data\": [{\n" +
            "\"time\": 1509991200,\n" +
            "\"summary\": \"Mostly Cloudy\",\n" +
            "\"icon\": \"partly-cloudy-day\",\n" +
            "\"precipIntensity\": 0.0007,\n" +
            "\"precipProbability\": 0.1,\n" +
            "\"precipType\": \"rain\",\n" +
            "\"temperature\": 65.76,\n" +
            "\"apparentTemperature\": 66.01,\n" +
            "\"dewPoint\": 60.99,\n" +
            "\"humidity\": 0.85,\n" +
            "\"pressure\": 1010.57,\n" +
            "\"windSpeed\": 4.23,\n" +
            "\"windGust\": 9.52,\n" +
            "\"windBearing\": 230,\n" +
            "\"cloudCover\": 0.62,\n" +
            "\"uvIndex\": 1,\n" +
            "\"visibility\": 9.32,\n" +
            "\"ozone\": 268.95\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "\"alerts\": [\n" +
            "{\n" +
            "\"title\": \"Flood Watch for Mason, WA\",\n" +
            "\"time\": 1509993360,\n" +
            "\"expires\": 1510036680,\n" +
            "},\n" +
            "]\n" +
             "},\n"+
            "{\n" +
            "\"flags\": {\n" +
            "\"units\": \"us\",\n" +
            "}\n" +
            "}";

    private JSONObject weahterJSON = new JSONObject(weatherStr);

    @Test
    public void canParseJSON(){
        Weather weather = Weather.fromJson(weahterJSON);

        //weather
        assertEquals(weather.getLatitude(),42.3601,0.001);
        assertEquals(weather.getLongitude(),-71.0589,0.001);
        assertEquals(weather.getTimezone(),"America/New_York");

        //currently
        Currently currently = weather.getCurrently();
        assertEquals(currently.getTime(),"2017-11-06T18:34:37Z");
        assertEquals(currently.getSummary(),"Drizzle");
        assertEquals(currently.getIcon(),"rain");
        assertEquals(currently.getNearestStormDistance(),0);
        assertEquals(currently.getPrecipIntensity(),0.0089,0.0001);
        assertEquals(currently.getPrecipIntensityError(),0.0046,0.0001);
        assertEquals(currently.getPrecipProbability(),0.9,0.01);
        assertEquals(currently.getPrecipType(),"rain");
        assertEquals(currently.getTemperature(),66.1,0.01);
        assertEquals(currently.getApparentTemperature(),66.31,0.01);
        assertEquals(currently.getDewPoint(),60.77,0.01);
        assertEquals(currently.getHumidity(),0.83,0.01);
        assertEquals(currently.getPressure(), 1010.34, 0.01);
        assertEquals(currently.getWindSpeed(),5.59,0.01);
        assertEquals(currently.getWindGust(),12.03,0.1);
        assertEquals(currently.getWindBearing(),246,0.01);
        assertEquals(currently.getCloudCover(),0.7,0.01);
        assertEquals(currently.getUvIndex(),1,0.01);
        assertEquals(currently.getVisibility(),9.84,0.01);
        assertEquals(currently.getOzone(),267.44,0.01);

    }


}
