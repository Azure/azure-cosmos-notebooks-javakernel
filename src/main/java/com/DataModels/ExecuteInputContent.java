package com.DataModels;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecuteInputContent {

    @JsonProperty("code")
    private final String code;
    @JsonProperty("execution_count")
    private final Integer executionCount;

    @JsonCreator
    public ExecuteInputContent(@JsonProperty("code") final String code, @JsonProperty("execution_count") final Integer executionCount) {
        Objects.requireNonNull(code);

        this.code = code;
        this.executionCount = executionCount == null ? 0 : executionCount;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getExecutionCount() {
        return this.executionCount;
    }
}
