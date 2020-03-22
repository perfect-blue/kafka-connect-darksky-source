package com.ravi;

import com.ravi.validators.*;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;

import java.time.Instant;
import java.util.Map;
import java.time.ZonedDateTime;

public class DarkSkySourceConnectorConfig extends AbstractConfig {

  //topic
  public  static final String TOPIC_CONFIG="topic";
  private static final String TOPIC_DOC="topic to write to";


  //Latitude
  public static final String LATITUDE_CONFIG="latitude";
  private static final String LATITUDE_DOC="latitude of a place";

  //Longitude
  public static final String LONGITUDE_CONFIG="longitude";
  private static final String LONGITUDE_DOC="longitude of a place";

  //batchsize
  public static final String BATCH_SIZE_CONFIG="batch.size";
  private static final String BATCH_SIZE_DOC="Number of data points to retrieve at a time. Defaults to 100 (max value)";

  //secretkey
  public static final String SECRET_KEY_CONFIG="secret.key";
  private static final String SECRET_KEY_DOCS="your personal secret key for authentication";

  //date
  public static final String DATE_CONFIG="date";
  private static final String DATE_DOC= "Only weather information updated at or after this time are returned.\n"
          + "This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ.\n"
          + "Defaults to a year before current day.";

  //max request
  public static final String MAX_REQUEST_CONFIG="max.request";
  private static final String MAX_REQUEST_DOC="maximum API request";

  public DarkSkySourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }

  public DarkSkySourceConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(TOPIC_CONFIG,Type.STRING,"",Importance.HIGH,TOPIC_DOC)
        .define(BATCH_SIZE_CONFIG,Type.INT,100,new BatchSizeValidator(),Importance.LOW,BATCH_SIZE_DOC)
        .define(SECRET_KEY_CONFIG,Type.STRING,"",Importance.HIGH,SECRET_KEY_DOCS)
        .define(DATE_CONFIG, Type.STRING, ZonedDateTime.now().minusYears(1).toInstant().toString(), new TimestampValidator(),
                Importance.HIGH, DATE_DOC)
         .define(MAX_REQUEST_CONFIG,Type.INT,1000,new RequestValidator(),Importance.HIGH, MAX_REQUEST_DOC)
         .define(LATITUDE_CONFIG,Type.DOUBLE,42.3601, new LatitudeValidator(), Importance.HIGH, LATITUDE_DOC)
         .define(LONGITUDE_CONFIG,Type.DOUBLE, -71.0589, new LongitudeValidator(), Importance.HIGH, LONGITUDE_DOC);


  }

  public Instant getDateConfig(){return java.time.Instant.parse(this.getString(DATE_CONFIG));}

  public Integer getMaxRequestConfig(){return this.getInt(MAX_REQUEST_CONFIG);}

  public String getTopicConfig(){
    return this.getString(TOPIC_CONFIG);
  }


  public Integer getBatchSizeConfig() {
    return this.getInt(BATCH_SIZE_CONFIG);
  }

  public String getSecretKeyConfig() {
    return this.getString(SECRET_KEY_CONFIG);
  }

  public Double getLatitude(){return this.getDouble(LATITUDE_CONFIG);}

  public Double getLongitude(){return this.getDouble(LONGITUDE_CONFIG);}
}
