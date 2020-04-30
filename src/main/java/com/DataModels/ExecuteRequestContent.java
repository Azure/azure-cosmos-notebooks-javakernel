package com.DataModels;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecuteRequestContent {

    @JsonProperty("code")
    private final String code;
    @JsonProperty("execution_count")
    private final Integer executionCount;
    @JsonProperty("silent")
    private final Boolean silent;
    @JsonProperty("store_history")
    private final Boolean storeHistory;
    @JsonProperty("user_expressions")
    private final Object userExpressions;
    @JsonProperty("allow_stdin")
    private final Boolean allowStdin;
    @JsonProperty("stop_on_error")
    private final Boolean stopOnError;

    @JsonCreator
    public ExecuteRequestContent(@JsonProperty("code") final String code, @JsonProperty("execution_count") final Integer executionCount, 
    		@JsonProperty("silent") final boolean silent, @JsonProperty("store_history") final boolean storeHistory,
    		@JsonProperty("user_expressions") final Object userExpressions, @JsonProperty("allow_stdin") final boolean allowStdin,
    		@JsonProperty("stop_on_error") final boolean stopOnError
    		) {
        Objects.requireNonNull(code);

        this.code = code;
        this.executionCount = executionCount == null ? 0 : executionCount;
        this.silent = silent;
        this.storeHistory = storeHistory;
        this.userExpressions = userExpressions;
        this.allowStdin = allowStdin;
        this.stopOnError = stopOnError;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getExecutionCount() {
        return this.executionCount;
    }
    public boolean getSilent() {
        return this.silent;
    }

    public boolean getStoreHistory() {
        return this.storeHistory;
    }

    public Object getUserExpressions() {
        return this.userExpressions;
    }

    public boolean getAllowStdin() {
        return this.allowStdin;
    }

    public boolean getStopOnError() {
        return this.stopOnError;
    }
}
