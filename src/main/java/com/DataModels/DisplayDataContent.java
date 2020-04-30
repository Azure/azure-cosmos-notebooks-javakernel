package com.DataModels;

import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public final class DisplayDataContent {

    @JsonProperty("data")
    private final HashMap<String, String> data;
    @JsonProperty("metadata")
    private final HashMap<String, String> metadata;

    @JsonCreator
    public DisplayDataContent(@JsonProperty("data") final HashMap<String, String> data,
    		@JsonProperty("metadata") final HashMap<String, String> metadata) {
    	
    	Objects.requireNonNull(data);
    	
        this.data = data;
        this.metadata = metadata;
    }

    public HashMap<String, String> getData() {
        return this.data;
    }

    public HashMap<String, String> getmetadata() {
        return this.metadata;
    }

}
