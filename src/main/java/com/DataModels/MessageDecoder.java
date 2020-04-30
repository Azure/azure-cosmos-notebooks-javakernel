package com.DataModels;

import java.io.IOException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.JavaShell.Constants;

public final class MessageDecoder implements Decoder.Text<ExecuteRequestMessage> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(final EndpointConfig arg0) {
    }

    @Override
    public ExecuteRequestMessage decode(final String arg0) throws DecodeException {
        try {
            return Constants.MAPPER.readValue(arg0, ExecuteRequestMessage.class);
        } catch (IOException e) {
            throw new DecodeException(arg0, "Unable to decode text to Message", e);
        }
    }

    @Override
    public boolean willDecode(final String arg0) {
    	return true;
    }
}
