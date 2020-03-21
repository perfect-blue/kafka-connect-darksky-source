package com.ravi.validators;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
public class RequestValidator implements ConfigDef.Validator{

    @Override
    public void ensureValid(String name, Object value) {
        Integer requestSize = (Integer) value;
        if (!(500 <= requestSize && requestSize <=20000)){
            throw new ConfigException(name, value, "Max Request must be a positive integer that's less or equal 500 to 20000");
        }
    }
}
