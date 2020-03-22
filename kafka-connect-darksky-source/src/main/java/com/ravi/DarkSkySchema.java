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


    //currently (object)
    public static String CURRENTLY_FIELD="currently";
    public static String HOURLY_FIELD="hourly";
    //data(array)
    public static String DATA_FIELD="data";

    //time
    public static String TIME_FIELD="time";
    //summary
    public static String SUMMARY_FIELD="summary";
    //icon
    public static String ICON_FIELD="icon";

    //precipIntencity
    public static String PRECIPINTENCITY_FIELD="precipIntensity";

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
    //Date
    public static String DATE_FIELD="date";



    //SCHEMA NAMES
    public static String SCHEMA_CURRENTLY="currently";
    public static String SCHEMA_HOURLY="hour";
    public static String SCHEMA_KEY="key";
    public static String SCHEMA_VALUE="value";

    //KEY Schema
    public static Schema KEY_SCHEMA=SchemaBuilder.struct().name(SCHEMA_KEY)
            .field(LATITTUDE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(LONGITUDE_FIELD,Schema.FLOAT64_SCHEMA)
            .build();

    //CURRENTLY SCHEMA
    public  static Schema CURRENTLY_SCHEMA=SchemaBuilder.struct().name(SCHEMA_CURRENTLY)
            .version(1)
            .field(TIME_FIELD,Schema.STRING_SCHEMA)
            .field(SUMMARY_FIELD,Schema.STRING_SCHEMA)
            .field(PRECIPINTENCITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(PRECIPPROBABILITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(PRECIPTYPE_FIELD,Schema.STRING_SCHEMA)
            .field(TEMPERATURE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(APPARENT_TEMPERATURE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(DEW_POINT_FIELD,Schema.FLOAT64_SCHEMA)
            .field(HUMIDITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(PRESSURE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(WIND_SPEED_FIELD,Schema.FLOAT64_SCHEMA)
            .field(WIND_GUST_FIELD,Schema.FLOAT64_SCHEMA)
            .field(WIND_BEARING_FIELD,Schema.FLOAT64_SCHEMA)
            .field(CLOUD_COVER_FIELD,Schema.FLOAT64_SCHEMA)
            .field(UV_INDEX_FIELD,Schema.INT32_SCHEMA)
            .field(VISIBILITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(OZONE_FIELD,Schema.FLOAT64_SCHEMA)
            .optional()
            .build();

    //HOURLY SCHEMA
    public  static Schema HOURLY_SCHEMA=SchemaBuilder.struct().name(SCHEMA_HOURLY)
            .version(1)
            .field(TIME_FIELD,Schema.STRING_SCHEMA)
            .field(SUMMARY_FIELD,Schema.STRING_SCHEMA)
            .field(PRECIPINTENCITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(PRECIPPROBABILITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(PRECIPTYPE_FIELD,Schema.STRING_SCHEMA)
            .field(TEMPERATURE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(APPARENT_TEMPERATURE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(DEW_POINT_FIELD,Schema.FLOAT64_SCHEMA)
            .field(HUMIDITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(PRESSURE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(WIND_SPEED_FIELD,Schema.FLOAT64_SCHEMA)
            .field(WIND_GUST_FIELD,Schema.FLOAT64_SCHEMA)
            .field(WIND_BEARING_FIELD,Schema.FLOAT64_SCHEMA)
            .field(CLOUD_COVER_FIELD,Schema.FLOAT64_SCHEMA)
            .field(UV_INDEX_FIELD,Schema.INT32_SCHEMA)
            .field(VISIBILITY_FIELD,Schema.FLOAT64_SCHEMA)
            .field(OZONE_FIELD,Schema.FLOAT64_SCHEMA)
            .optional()
            .build();

    public static Schema VALUE_SCHEMA=SchemaBuilder.struct().name(SCHEMA_VALUE)
            .version(1)
            .field(LATITTUDE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(LONGITUDE_FIELD,Schema.FLOAT64_SCHEMA)
            .field(TIMEZONE_FIELD,Schema.STRING_SCHEMA)
            .field(CURRENTLY_FIELD,CURRENTLY_SCHEMA)
            .field(DATE_FIELD,Schema.STRING_SCHEMA)
            .field(DATA_FIELD,SchemaBuilder.array(HOURLY_SCHEMA))
            .build();


}
