package com.ravi.validators;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;

public class LatitudeValidator implements ConfigDef.Validator{
    @Override
    public void ensureValid(String name, Object value) {
        Double lat = (Double) value;
        if (!(-90.0 <= lat && lat <=90.0)){
            throw new ConfigException(name, value, "latitude must be a double between -90.0 and 90.0");
        }
    }
}
