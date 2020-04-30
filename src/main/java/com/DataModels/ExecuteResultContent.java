package com.DataModels;

import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ExecuteResultContent {

    @JsonProperty("execution_count")
    private final int executionCount;
    @JsonProperty("data")
    private final HashMap<String, String> data;
    @JsonProperty("metadata")
    private final HashMap<String, String> metadata;

    @JsonCreator
    public ExecuteResultContent(@JsonProperty("execution_count") final int executionCount,
    		@JsonProperty("data") final HashMap<String, String> data,
    		@JsonProperty("metadata") final HashMap<String, String> metadata) {
    	
    	Objects.requireNonNull(data);
    	
    	this.executionCount = executionCount;
        this.data = data;
        this.metadata = metadata;
    }

    public int getExecutionCount() {
        return this.executionCount;
    }

    public HashMap<String, String> getData() {
        return this.data;
    }

    public HashMap<String, String> getmetadata() {
        return this.metadata;
    }

}
