package com.javacodegeeks.websocket_example;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Message {

    @JsonProperty("username")
    private final String userName;
    @JsonProperty
    private final String message;

    @JsonCreator
    public Message(@JsonProperty("username") final String userName, @JsonProperty("message") final String message) {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(message);

        this.userName = userName;
        this.message = message;
    }

    String getUserName() {
        return this.userName;
    }

    String getMessage() {
        return this.message;
    }
}
