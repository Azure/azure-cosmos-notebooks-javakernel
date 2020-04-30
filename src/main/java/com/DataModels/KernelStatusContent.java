package com.DataModels;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class KernelStatusContent {

    @JsonProperty("execution_state")
    private final String executionState;
    
    @JsonCreator
    public KernelStatusContent(@JsonProperty("execution_state") final String executionState) {    	
    	Objects.requireNonNull(executionState);
    	
        this.executionState = executionState;
    }

    public String getExecutionState() {
        return this.executionState;
    }
}
