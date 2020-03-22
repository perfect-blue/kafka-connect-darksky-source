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
  public void validatePassword() {
    Map<String, String> config = initialConfig();
    config.put(SECRET_KEY_CONFIG, "password");
    ConfigValue configValue = configDef.validateAll(config).get(SECRET_KEY_CONFIG);
    assert (configValue.errorMessages().size() == 0);
  }

  @Test
  public void validateDate(){
    Map<String,String> config= initialConfig();
    config.put(DATE_CONFIG,"not-a-date");
    ConfigValue configValue=configDef.validateAll(config).get(DATE_CONFIG);
    assert (configValue.errorMessages().size()>0);
  }

  @Test
  public void validateMaxRequest(){

    Map<String,String> config = initialConfig();
    config.put(MAX_REQUEST_CONFIG,"-1");
    ConfigValue configValue =configDef.validateAll(config).get(MAX_REQUEST_CONFIG);
    assert (configValue.errorMessages().size()>0);

    config = initialConfig();
    config.put(MAX_REQUEST_CONFIG, "20001");
    configValue = configDef.validateAll(config).get(MAX_REQUEST_CONFIG);
    assert (configValue.errorMessages().size() > 0);
  }
}