package com.DataModels;

import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class Message {

    @JsonProperty("buffers")
    protected final JsonNode buffers;
    @JsonProperty("channel")
    protected final String channel;
    @JsonProperty("msg_type")
    protected final String msgType;
    @JsonProperty("msg_id")
    protected final String msgId;
    @JsonProperty("header")
    protected final MessageHeader header;
    @JsonProperty("parent_header")
    protected final MessageHeader parentHeader;
    @JsonProperty("metadata")
    protected final HashMap<String, String> metadata;

    @JsonCreator
    public Message(@JsonProperty("buffers") final JsonNode buffers,
    		@JsonProperty("channel") final String channel,
    		@JsonProperty("msg_type") final String msgType,
    		@JsonProperty("msg_id") final String msgId,
    		@JsonProperty("header") final MessageHeader header,
    		@JsonProperty("parent_header") final MessageHeader parentHeader,
    		@JsonProperty("metadata") final HashMap<String, String> metadata) {
        
    	Objects.requireNonNull(buffers);
    	Objects.requireNonNull(channel);
    	Objects.requireNonNull(header);
    	Objects.requireNonNull(parentHeader);
    	Objects.requireNonNull(metadata);

    	this.buffers = buffers;
    	this.channel = channel;
    	this.msgType = msgType;
    	this.msgId = msgId;
    	this.header = header;
    	this.parentHeader = parentHeader;
    	this.metadata = metadata;
    }
    
    public JsonNode getBuffers() {
        return this.buffers;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public MessageHeader getHeader() {
        return this.header;
    }

    public MessageHeader getParentHeader() {
        return this.parentHeader;
    }

    public HashMap<String, String> getMetadata() {
        return this.metadata;
    }
}
