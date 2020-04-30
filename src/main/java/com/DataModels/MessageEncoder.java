package com.DataModels;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.JavaShell.Constants;

public final class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(final EndpointConfig arg0) {
    }

    @Override
    public String encode(final Message displayDataMessage) throws EncodeException {
        try {
            return Constants.MAPPER.writeValueAsString(displayDataMessage);
        } catch (Exception e) {
            throw new EncodeException(displayDataMessage, "Unable to encode message", e);
        }
    }
}
