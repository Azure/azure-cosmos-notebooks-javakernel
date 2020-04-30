package com.DataModels;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class StreamContent {

    @JsonProperty("name")
    private final String name;
    @JsonProperty("text")
    private final String text;
    
    @JsonCreator
    public StreamContent(@JsonProperty("name") final String name,
    		@JsonProperty("text") final String text) { 	
    	Objects.requireNonNull(name);
    	Objects.requireNonNull(text);
    	
    	this.name = name;
    	this.text = text;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }
}
