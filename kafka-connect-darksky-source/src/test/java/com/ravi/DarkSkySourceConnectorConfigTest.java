package com.ravi;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigValue;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.ravi.DarkSkySourceConnectorConfig.*;

public class DarkSkySourceConnectorConfigTest {
  private ConfigDef configDef = DarkSkySourceConnectorConfig.conf();

  private Map<String,String> initialConfig(){
    Map<String,String> baseProps=new HashMap<>();
    baseProps.put(TOPIC_CONFIG,"weather.darksky");
    baseProps.put(BATCH_SIZE_CONFIG, "100");
    baseProps.put(SECRET_KEY_CONFIG,"1234567");
    return baseProps;
  }

  @Test
  public void doc() {
    System.out.println(DarkSkySourceConnectorConfig.conf().toRst());
  }

  @Test
  public void initialConfigIsValid() {
    assert (configDef.validate(initialConfig())
            .stream()
            .allMatch(configValue -> configValue.errorMessages().size() == 0));
  }

  @Test
  public void canReadConfigCorrectly() {
    DarkSkySourceConnectorConfig config = new DarkSkySourceConnectorConfig(initialConfig());
    config.getSecretKeyConfig();
    System.out.println("this is topic name: "+config.getTopicConfig());

  }


  @Test
  public void validateBatchSize() {
    Map<String, String> config = initialConfig();
    config.put(BATCH_SIZE_CONFIG, "-1");
    ConfigValue configValue = configDef.validateAll(config).get(BATCH_SIZE_CONFIG);
    assert (configValue.errorMessages().size() > 0);

    config = initialConfig();
    config.put(BATCH_SIZE_CONFIG, "101");
    configValue = configDef.validateAll(config).get(BATCH_SIZE_CONFIG);
    assert (configValue.errorMessages().size() > 0);

  }

  @Test
  public void validateCities() {
    Map<String, String> config = initialConfig();
    config.put(CITIES_CONFIG, "username");
    ConfigValue configValue = configDef.validateAll(config).get(CITIES_CONFIG);
    assert (configValue.errorMessages().size() == 0);
  }

  @Test
  public void validatePassword() {
    Map<String, String> config = initialConfig();
    config.put(SECRET_KEY_CONFIG, "password");
    ConfigValue configValue = configDef.validateAll(config).get(SECRET_KEY_CONFIG);
    assert (configValue.errorMessages().size() == 0);
  }
}