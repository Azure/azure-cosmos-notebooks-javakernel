package com.Helpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Constants {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    public static ObjectMapper getMapper() {
		Constants.MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		Constants.MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    	return Constants.MAPPER;
    }
}
