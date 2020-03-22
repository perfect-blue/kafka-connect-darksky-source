package com.ravi.validators;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;

public class LongitudeValidator implements ConfigDef.Validator{
    @Override
    public void ensureValid(String name, Object value) {
       Double requestSize = (Double) value;
        if (!(-180.0 <= requestSize && requestSize <=180.0)){
            throw new ConfigException(name, value, "Longitude must be a double between -180.0 and 180.0");
        }
    }
}
