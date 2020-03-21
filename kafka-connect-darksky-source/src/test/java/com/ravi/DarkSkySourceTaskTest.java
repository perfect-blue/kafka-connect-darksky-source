package com.ravi;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.kafka.connect.source.SourceRecord;
import com.ravi.models.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ravi.DarkSkySchema.DATA_FIELD;
import static com.ravi.DarkSkySourceConnectorConfig.*;

public class DarkSkySourceTaskTest {

  DarkSkySourceTask darkSkySourceTask = new DarkSkySourceTask();
  private Integer batchSize=10;

  private Map<String,String> initialConfig(){
    Map<String,String> baseProps = new HashMap<>();
    baseProps.put(TOPIC_CONFIG, "darksky.weather");
    baseProps.put(CITY_CONFIG,"bandung");
    baseProps.put(BATCH_SIZE_CONFIG,batchSize.toString());
    baseProps.put(DATE_CONFIG,"2020-01-01T00:00:00Z");
    baseProps.put(SECRET_KEY_CONFIG,"fd5db841561d25a99b2c4cb0e5e80c4a");

    return baseProps;

  }

  @Test
  public void test() throws UnirestException, InterruptedException {
    darkSkySourceTask.config=new DarkSkySourceConnectorConfig(initialConfig());
    darkSkySourceTask.client=new DarkSkyHttpClient(darkSkySourceTask.config);

    HttpResponse<JsonNode> httpResponse =darkSkySourceTask.client.getWeatherHistoryAPI(darkSkySourceTask.config.getDateConfig());
    if(httpResponse.getStatus()!=403){
      assert(httpResponse.getStatus()==200);
      JSONObject jsonObject=(JSONObject) httpResponse.getBody().getObject();
      JSONArray jsonArray = jsonObject.getJSONObject("hourly").getJSONArray(DATA_FIELD);
      Weather weather = Weather.fromJson(jsonArray.getJSONObject(0));
      assert (weather!=null);
      System.out.println(weather.getTimezone());
    }
  }
}