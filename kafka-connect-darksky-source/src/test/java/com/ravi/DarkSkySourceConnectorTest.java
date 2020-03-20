package com.ravi;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static com.ravi.DarkSkySourceConnectorConfig.*;
import static org.junit.Assert.*;

public class DarkSkySourceConnectorTest {
  private Map<String, String> initialConfig() {
    Map<String, String> baseProps = new HashMap<>();
    baseProps.put(BATCH_SIZE_CONFIG, "100");
    baseProps.put(TOPIC_CONFIG, "weather.data");
    return (baseProps);
  }

  @Test
  public void taskConfigsShouldReturnOneTaskConfig() {
    DarkSkySourceConnector foursquareSourceConnector = new DarkSkySourceConnector();
    foursquareSourceConnector.start(initialConfig());
    assertEquals(foursquareSourceConnector.taskConfigs(1).size(),1);
    assertEquals(foursquareSourceConnector.taskConfigs(10).size(),1);
  }

}
