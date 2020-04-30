package com.DataModels;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageHeader {
    @JsonProperty("msg_id")
    private final String msgId;
    @JsonProperty("username")
    private final String username;
    @JsonProperty("session")
    private final String session;
    @JsonProperty("msg_type")
    private final String msgType;
    @JsonProperty("version")
    private final String version;
    @JsonProperty("date")
    private final String date;

    @JsonCreator
    public MessageHeader(@JsonProperty("msg_id") final String msgId,
    		@JsonProperty("username") final String username,
    		@JsonProperty("session") final String session,
    		@JsonProperty("msg_type") final String msgType,
    		@JsonProperty("version") final String version,
    		@JsonProperty("date") final String date) {
        
    	Objects.requireNonNull(msgId);
        
        this.msgId = msgId;
        this.username = username;
        this.session = session;
        this.msgType = msgType;
        this.version = version;
        this.date = date;
    }
    
    public MessageHeader(final String msgId,
    		final String msgType,
    		final String session) {

    	this(msgId, "username", session, msgType, "5.2.0", (new Date()).toString());
    }

    public String getMsgId() {
        return this.msgId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSession() {
        return this.session;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDate() {
        return this.date;
    }
}
