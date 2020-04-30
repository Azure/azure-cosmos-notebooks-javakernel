package com.DataModels;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ClearDisplayContent {

    @JsonProperty("wait")
    private final boolean wait;
    
    @JsonCreator
    public ClearDisplayContent(@JsonProperty("wait") final boolean wait) {
    	Objects.requireNonNull(wait);

    	this.wait = wait;
    }

    public boolean getWait() {
        return this.wait;
    }
}
