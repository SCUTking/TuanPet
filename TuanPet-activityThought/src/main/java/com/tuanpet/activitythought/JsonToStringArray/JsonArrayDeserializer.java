package com.tuanpet.activitythought.JsonToStringArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

public class JsonArrayDeserializer implements ObjectDeserializer {

    @Override
    public String[] deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        String jsonString = parser.parseObject(String.class);
        if (jsonString == null || jsonString.isEmpty()) {
            return new String[0];
        }
        String[] array = JSON.parseObject(jsonString, String[].class);
        return array == null ? new String[0] : array;
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LBRACKET;
    }
}
