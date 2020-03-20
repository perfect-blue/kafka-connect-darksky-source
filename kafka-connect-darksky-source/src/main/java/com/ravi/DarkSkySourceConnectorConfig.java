package com.ravi;

import com.ravi.validators.BatchSizeValidator;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;

import java.util.Map;


public class DarkSkySourceConnectorConfig extends AbstractConfig {

  //topic
  public  static final String TOPIC_CONFIG="topic";
  private static final String TOPIC_DOC="topic to write to";

  //location
  public static final String CITIES_CONFIG="cities";
  private static final String CITIES_DOC="get weather from certain cities";

  //batchsize
  public static final String BATCH_SIZE_CONFIG="batch.size";
  private static final String BATCH_SIZE_DOC="Number of data points to retrieve at a time. Defaults to 100 (max value)";

  //secretkey
  public static final String SECRET_KEY_CONFIG="secret.key";
  private static final String SECRET_KEY_DOCS="your personal secret key for authentication";

  public DarkSkySourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }

  public DarkSkySourceConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(TOPIC_CONFIG,Type.STRING,"",Importance.HIGH,TOPIC_DOC)
        .define(CITIES_CONFIG,Type.STRING,"",Importance.HIGH,CITIES_DOC)
        .define(BATCH_SIZE_CONFIG,Type.INT,100,new BatchSizeValidator(),Importance.LOW,BATCH_SIZE_DOC)
        .define(SECRET_KEY_CONFIG,Type.STRING,"",Importance.HIGH,SECRET_KEY_DOCS) ;

  }

  public String getTopicConfig(){
    return this.getString(TOPIC_CONFIG);
  }

  public String getCitiesConfig() {
    return this.getString(CITIES_CONFIG);
  }

  public String getBatchSizeConfig() {
    return this.getString(BATCH_SIZE_CONFIG);
  }

  public String getSecretKeyConfig() {
    return this.getString(SECRET_KEY_CONFIG);
  }
}
