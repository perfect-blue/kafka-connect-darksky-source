package com.ravi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeoLocation {

    private String name;
    private double latitude;
    private double longitude;

    public GeoLocation(String name){
        this.name=name;
        if(name.equalsIgnoreCase("bandung")){
            this.latitude=-6.917464;
            this.longitude=107.619125;
        }else if(name.equalsIgnoreCase("Jakarta")){
            this.latitude=-6.175110;
            this.longitude=106.865036;
        }else{
            this.latitude=-7.257472;
            this.longitude=112.752090;
        }
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
