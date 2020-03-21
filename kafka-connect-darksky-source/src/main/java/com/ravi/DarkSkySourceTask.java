package com.ravi;

import com.ravi.models.Currently;
import com.ravi.models.Weather;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.temporal.ChronoUnit;

import static com.ravi.DarkSkySchema.*;
public class DarkSkySourceTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(DarkSkySourceTask.class);

  protected Instant nextDate;
  public DarkSkySourceConnectorConfig config;

  DarkSkyHttpClient client;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    config=new DarkSkySourceConnectorConfig(map);
    this.nextDate=config.getDateConfig();
    client=new DarkSkyHttpClient(config);
    log.debug(this.nextDate.plus(1,ChronoUnit.DAYS).toString());
  }


  @Override
  public List<SourceRecord> poll() throws InterruptedException {
      final ArrayList<SourceRecord> records = new ArrayList<>();
      JSONArray weatherArray = client.getWeatherHistory(this.nextDate);
      int i=0;
      int data=0;
      for(Object obj: weatherArray){
        Weather weather = Weather.fromJson((JSONObject) obj);
        SourceRecord sourceRecord = generateSourceRecord(weather,this.nextDate);
        records.add(sourceRecord);
        i++;
        data++;
      }

      if (i > 0) log.info(String.format("Fetched %s record(s)", i));
      this.nextDate=this.nextDate.plus(data/24,ChronoUnit.DAYS);

      return records;
  }


  private SourceRecord generateSourceRecord(Weather weather, Instant date){
    return new SourceRecord(
            sourcePartition(),
            sourceOffset(date),
            config.getTopicConfig(),
            null,
            KEY_SCHEMA,
            buildRecordKey(weather),
            VALUE_SCHEMA,
            buildRecordValue(weather)
            );
  }
  @Override
  public void stop() {

  }

  private Map<String,String> sourcePartition(){
    Map<String,String> map = new HashMap<>();
    map.put(LOCATION_FIELD,config.getCitiesConfig());
    return map;
  }

  private Map<String,String> sourceOffset(Instant date){
    Map<String,String> map = new HashMap<>();
    map.put(DATE_FIELD,date.toString());
    return map;
  }

  private Struct buildRecordKey(Weather weather){
    Struct key=new Struct(KEY_SCHEMA)
            .put(LATITTUDE_FIELD,weather.getLatitude())
            .put(LONGITUDE_FIELD,weather.getLongitude());

    return key;
  }

  private Struct buildRecordValue(Weather weather){
    Currently current=weather.getCurrently();
    Struct CurrentlyStruct=new Struct(CURRENTLY_SCHEMA)
            .put(TIME_FIELD,current.getTime())
            .put(SUMMARY_FIELD, current.getSummary())
            .put(PRECIPINTENCITY_FIELD,current.getPrecipIntensity())
            .put(PRECIPPROBABILITY_FIELD,current.getPrecipProbability())
            .put(PRECIPTYPE_FIELD,current.getPrecipType())
            .put(TEMPERATURE_FIELD,current.getTemperature())
            .put(APPARENT_TEMPERATURE_FIELD,current.getApparentTemperature())
            .put(DEW_POINT_FIELD,current.getDewPoint())
            .put(HUMIDITY_FIELD,current.getHumidity())
            .put(PRESSURE_FIELD,current.getPressure())
            .put(WIND_SPEED_FIELD,current.getWindSpeed())
            .put(WIND_GUST_FIELD,current.getWindGust())
            .put(WIND_BEARING_FIELD,current.getWindBearing())
            .put(CLOUD_COVER_FIELD,current.getCloudCover())
            .put(UV_INDEX_FIELD,current.getUvIndex())
            .put(VISIBILITY_FIELD,current.getVisibility())
            .put(OZONE_FIELD,current.getOzone());

    List<Currently> data = weather.getData();
    List<Struct> dataStruct=new ArrayList<>();

    for (int i=0; i<data.size();i++){
        Currently currently = data.get(i);

      Struct hourlyStruct=new Struct(HOURLY_SCHEMA)
              .put(TIME_FIELD,currently.getTime())
              .put(SUMMARY_FIELD, currently.getSummary())
              .put(PRECIPINTENCITY_FIELD,currently.getPrecipIntensity())
              .put(PRECIPPROBABILITY_FIELD,currently.getPrecipProbability())
              .put(PRECIPTYPE_FIELD,currently.getPrecipType())
              .put(TEMPERATURE_FIELD,currently.getTemperature())
              .put(APPARENT_TEMPERATURE_FIELD,currently.getApparentTemperature())
              .put(DEW_POINT_FIELD,currently.getDewPoint())
              .put(HUMIDITY_FIELD,currently.getHumidity())
              .put(PRESSURE_FIELD,currently.getPressure())
              .put(WIND_SPEED_FIELD,currently.getWindSpeed())
              .put(WIND_GUST_FIELD,currently.getWindGust())
              .put(WIND_BEARING_FIELD,currently.getWindBearing())
              .put(CLOUD_COVER_FIELD,currently.getCloudCover())
              .put(UV_INDEX_FIELD,currently.getUvIndex())
              .put(VISIBILITY_FIELD,currently.getVisibility())
              .put(OZONE_FIELD,currently.getOzone());

      dataStruct.add(hourlyStruct);
    }

    Struct valueStruct = new Struct(VALUE_SCHEMA)
            .put(LATITTUDE_FIELD,weather.getLatitude())
            .put(LONGITUDE_FIELD,weather.getLongitude())
            .put(TIMEZONE_FIELD,weather.getTimezone())
            .put(CURRENTLY_FIELD,CurrentlyStruct)
            .put(LOCATION_FIELD,client.getGeoLocation().getName())
            .put(DATE_FIELD,nextDate)
            .put(DATA_FIELD,dataStruct);


    return valueStruct;

  }
}