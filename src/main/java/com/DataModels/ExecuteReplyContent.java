package com.DataModels;

import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ExecuteReplyContent {

    @JsonProperty("status")
    private final String status;
    @JsonProperty("execution_count")
    private final int executionCount;
    @JsonProperty("user_expressions")
    private final HashMap<String, String> userExpressions;

    
    @JsonCreator
    public ExecuteReplyContent(@JsonProperty("status") final String status,
    		@JsonProperty("execution_count") final int executionCount,
    		@JsonProperty("user_expressions") final HashMap<String, String> userExpressions) {    	
    	
    	Objects.requireNonNull(status);
    	Objects.requireNonNull(executionCount);
    	
    	this.status = status;
    	this.executionCount = executionCount;
    	this.userExpressions = userExpressions;
    }

    public ExecuteReplyContent(final String status, final int executionCount) {
    	this(status, executionCount, null);
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public int getExecutionCount() {
        return this.executionCount;
    }

    public HashMap<String, String> getUserExpressions() {
        return this.userExpressions;
    }
}
