package com.javacodegeeks.websocket_example;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;

public final class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(final EndpointConfig arg0) {
    }

    @Override
    public String encode(final Message message) throws EncodeException {
        try {
            return Constants.MAPPER.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new EncodeException(message, "Unable to encode message", e);
        }
    }
}
