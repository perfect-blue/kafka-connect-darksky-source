package com.ravi;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

public class DarkSkySchema {

    //latitude(double)
    public static String LATITTUDE_FIELD="latitude";
    //longitude(double)
    public static String LONGITUDE_FIELD="longitude";
    //timezone(double)
    public static String TIMEZONE_FIELD="timezone";
    //place
    public static String PLACE_FIELD="place";

    //currently (object)
    public static String CURRENTLY_FIELD="currently";

    //flags(object)
    public static String FLAGS_FIELD="flags";

    //time
    public static String TIME_FIELD="time";
    //summary
    public static String SUMMARY_FIELD="summary";
    //icon
    public static String ICON_FIELD="icon";
    //nearestStormDistance
    public static String NEARESTSTORMDISTANCE_FIELD="nearestStormDistance";
    //precipIntencity
    public static String PRECIPINTENCITY_FIELD="precipIntensity";
    //precipIntencityError
    public static String PRECIPINTENCITYERROR_FIELD="precipIntensityError";
    //precipProbability
    public static String PRECIPPROBABILITY_FIELD="precipProbability";
    //precipType
    public static String PRECIPTYPE_FIELD="precipType";
    //temperature
    public static String TEMPERATURE_FIELD="temperature";
    //apparentTemperature
    public static String APPARENT_TEMPERATURE_FIELD="apparentTemperature";
    //dewPoint
    public static String DEW_POINT_FIELD="dewPoint";
    //humidity
    public static String HUMIDITY_FIELD="humidity";
    //pressure
    public static String PRESSURE_FIELD="pressure";
    //windSpeed
    public static String WIND_SPEED_FIELD="windSpeed";
    //windGust
    public static String WIND_GUST_FIELD="windGust";
    //windBearing
    public static String WIND_BEARING_FIELD="windBearing";
    //cloudCover
    public static String CLOUD_COVER_FIELD="cloudCover";
    //uvIndex
    public static String UV_INDEX_FIELD="uvIndex";
    //visibility
    public static String VISIBILITY_FIELD="visibility";
    //ozone
    public static String OZONE_FIELD="ozone";
    //units
    public static String UNITS_FIELD="units";
    //nearest station
    public static String NEAREST_STATION_FIELD="nearest-station-field";


    //SCHEMA NAMES
    public static String SCHEMA_CURRENTLY="currently";
    public static String SCHEMA_FLAGS="flags";
    public static String SCHEMA_HOURLY="hour";
    public static String SCHEMA_KEY="key";
    public static String SCHEMA_VALUE="value";

    //KEY Schema
    public static Schema KEY_SCHEMA=SchemaBuilder.struct().name(SCHEMA_KEY)
            .field(LATITTUDE_FIELD,Schema.FLOAT32_SCHEMA)
            .field(LONGITUDE_FIELD,Schema.FLOAT32_SCHEMA)
            .build();

    //HOUR SCHEMA
    public  static Schema CURRENTLY_SCHEMA=SchemaBuilder.struct().name(SCHEMA_CURRENTLY)
            .version(1)
            .field(TIME_FIELD,Schema.FLOAT32_SCHEMA)
            .field(SUMMARY_FIELD,Schema.STRING_SCHEMA)
            .field(NEARESTSTORMDISTANCE_FIELD,Schema.INT32_SCHEMA)
            .field(PRECIPINTENCITY_FIELD,Schema.FLOAT32_SCHEMA)
            .field(PRECIPINTENCITYERROR_FIELD,Schema.FLOAT32_SCHEMA)
            .field(PRECIPPROBABILITY_FIELD,Schema.FLOAT32_SCHEMA)
            .field(PRECIPTYPE_FIELD,Schema.STRING_SCHEMA)
            .field(TEMPERATURE_FIELD,Schema.FLOAT32_SCHEMA)
            .field(APPARENT_TEMPERATURE_FIELD,Schema.FLOAT32_SCHEMA)
            .field(DEW_POINT_FIELD,Schema.FLOAT32_SCHEMA)
            .field(HUMIDITY_FIELD,Schema.FLOAT32_SCHEMA)
            .field(PRESSURE_FIELD,Schema.FLOAT32_SCHEMA)
            .field(WIND_SPEED_FIELD,Schema.FLOAT32_SCHEMA)
            .field(WIND_GUST_FIELD,Schema.FLOAT32_SCHEMA)
            .field(WIND_BEARING_FIELD,Schema.FLOAT32_SCHEMA)
            .field(CLOUD_COVER_FIELD,Schema.FLOAT32_SCHEMA)
            .field(UV_INDEX_FIELD,Schema.INT32_SCHEMA)
            .field(VISIBILITY_FIELD,Schema.FLOAT32_SCHEMA)
            .field(OZONE_FIELD,Schema.FLOAT32_SCHEMA)
            .build();

    public static Schema FLAG_SCHEMA=SchemaBuilder.struct().name(SCHEMA_FLAGS)
            .version(1)
            .field(UNITS_FIELD,Schema.STRING_SCHEMA)
            .field(NEAREST_STATION_FIELD,Schema.STRING_SCHEMA)
            .build();

    public static Schema VALUE_SCHEMA=SchemaBuilder.struct().name(SCHEMA_VALUE)
            .version(1)
            .field(LATITTUDE_FIELD,Schema.FLOAT32_SCHEMA)
            .field(LONGITUDE_FIELD,Schema.FLOAT32_SCHEMA)
            .field(TIMEZONE_FIELD,Schema.STRING_SCHEMA)
            .field(CURRENTLY_FIELD,CURRENTLY_SCHEMA)
            .field(FLAGS_FIELD,FLAG_SCHEMA)
            .build();


}
