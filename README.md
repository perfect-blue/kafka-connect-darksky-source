# Kafka Connect Source Dark Sky

This connector allows you to get stream of weather condition from DarkSky, [using DarkSky API](https://darksky.net/dev/docs)

# Configuration
```
name=DarkSkySourceConnector
tasks.mask=1
connector.class=com.ravi.DarkSkySourceConnector
topic=weather.darksky
location=Bandung,Jakarta,Bali
secret.key=your_secret_key

```
# Deploying

Note: Java 8 is required for this connector
TO DO
