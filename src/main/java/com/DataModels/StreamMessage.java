package com.DataModels;

import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class StreamMessage extends Message {

    @JsonProperty("content")
    private final StreamContent content;    

    @JsonCreator
    public StreamMessage(@JsonProperty("content") final StreamContent content,
    		@JsonProperty("buffers") final JsonNode buffers,
    		@JsonProperty("channel") final String channel,
    		@JsonProperty("msg_type") final String msgType,
    		@JsonProperty("msg_id") final String msgId,
    		@JsonProperty("header") final MessageHeader header,
    		@JsonProperty("parent_header") final MessageHeader parentHeader,
    		@JsonProperty("metadata") final HashMap<String, String> metadata) {
        super(buffers, channel, msgType, msgId, header, parentHeader, metadata);

    	Objects.requireNonNull(content);
        
        this.content = content;
    }

    public StreamContent getContent() {
        return this.content;
    }

}
