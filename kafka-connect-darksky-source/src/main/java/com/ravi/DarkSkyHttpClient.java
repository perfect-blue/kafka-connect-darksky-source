package com.ravi;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import org.apache.kafka.connect.errors.ConnectException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class DarkSkyHttpClient {
    private static final Logger log = LoggerFactory.getLogger(DarkSkyHttpClient.class);

    private Integer XMaxCalls;
    private Integer XCalls;
    private Integer XRateRemaining;

    DarkSkySourceConnectorConfig config;

    public DarkSkyHttpClient(DarkSkySourceConnectorConfig config){
        this.config=config;
        this.XMaxCalls=this.config.getMaxRequestConfig();
    }

    protected JSONObject getWeather() throws InterruptedException{
        HttpResponse<JsonNode> jsonResponse;
        try{
            sleep(12);
            jsonResponse=getWeatherAPI();
            switch (jsonResponse.getStatus()){
                case 200:
                    return jsonResponse.getBody().getObject();
                case 401:
                    throw new ConnectException("Bad Github credentials provided, please edit your config");
                default:
                    log.error("Unknown error: Sleeping 5 seconds " +
                            "before re-trying");
                    Thread.sleep(5000L);
                    return getWeather();
            }
        }catch (UnirestException e){
            e.printStackTrace();
            sleep(10);
            return new JSONObject();
        }
    }

    protected JSONObject getWeatherHistory(Instant date) throws InterruptedException {
        HttpResponse<JsonNode> jsonResponse;
        try{
            sleep(10);
           jsonResponse =getWeatherHistoryAPI(date);
            Headers headers = jsonResponse.getHeaders();//set the headers
            XCalls= Integer.valueOf(headers.getFirst("X-Forecast-API-Calls"));
            XRateRemaining = XMaxCalls-XCalls;
            switch (jsonResponse.getStatus()){
                case 200:
                   JSONObject jsonObject=jsonResponse.getBody().getObject();
                   return jsonObject;
                case 401:
                    throw new ConnectException("Bad GitHub credentials provided, please edit your config");
                case 403:
                    log.info(jsonResponse.getBody().getObject().getString("message"));
                    log.info(String.format("Your rate limit is %s", XMaxCalls));
                    log.info(String.format("Your remaining calls is %s", XRateRemaining));
                    Thread.sleep(1000 * 30);
                    return getWeatherHistory(date);
                default:
                    log.error(constructURLHistory(date));
                    log.error(String.valueOf(jsonResponse.getStatus()));
                    log.error(jsonResponse.getBody().toString());
                    log.error(jsonResponse.getHeaders().toString());
                    log.error("Unknown error: Sleeping 5 seconds " +
                            "before re-trying");
                    Thread.sleep(5000L);
                    return getWeatherHistory(date);
            }
        }catch (UnirestException e){
            e.printStackTrace();
            Thread.sleep(5000L);
            return new JSONObject();
        }
    }
    protected HttpResponse<JsonNode> getWeatherHistoryAPI(Instant date) throws UnirestException {
        GetRequest unirest=Unirest.get(constructURLHistory(date));
        log.debug(String.format("GET %s",unirest.getUrl()));
        return unirest.asJson();
    }

    protected HttpResponse<JsonNode> getWeatherAPI() throws UnirestException{
        GetRequest unirest=Unirest.get(constructURL());
        log.debug(String.format("GET %s",unirest.getUrl()));
        return unirest.asJson();
    }

    protected String constructURLHistory(Instant date){
        return String.format(
             "https://api.darksky.net/forecast/%s/%s,%s,%s?units=si",
             config.getSecretKeyConfig(),
             config.getLatitude(),
             config.getLongitude(),
                date.toString()
        );
    }

    protected String constructURL(){
        return String.format(
           "https://api.darksky.net/forecast/%s/%s,%s?units=%s",
                config.getSecretKeyConfig(),
                config.getLatitude(),
                config.getLongitude(),
                "si"
        );
    }

    public void sleep(int second) throws InterruptedException{
        Thread.sleep(1000*second);
    }

}
