package com.JavaShell;

import java.util.Objects;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.DataModels.ExecuteRequestMessage;
import com.DataModels.MessageDecoder;
import com.fasterxml.jackson.databind.DeserializationFeature;

@ServerEndpoint(value = "/api/kernels/{kernelId}/channels"/*, encoders = MessageEncoder.class, decoders = MessageDecoder.class*/)
public final class JShellWebSocket {

    @OnOpen
    public void onOpen(@PathParam(Constants.KERNEL_ID) final String kernelId, final Session session) {
        if (Objects.isNull(kernelId) || kernelId.isEmpty()) {
        	throw new IllegalArgumentException("kernel id should not be empty.");
        } else {
        	JavaKernel.createJShell(kernelId);
        	System.out.println("created kernel:" + kernelId);
        }
        Constants.MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
    	throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(@PathParam(Constants.KERNEL_ID) final String kernelId, final String message,/*final ExecuteRequestMessage executeRequestMessage,*/ final Session session) {
	   try {
		   System.out.println(message);
		   ExecuteRequestMessage executeRequestMessage = (new MessageDecoder()).decode(message);
		   Utils.executeRequest(session, executeRequestMessage, kernelId);		   
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
    }

    @OnClose
    public void onClose(@PathParam(Constants.KERNEL_ID) final String kernelId, final Session session) {
    	System.out.println("closed kernel:" + kernelId);
    	JavaKernel.removeJShell(kernelId);
    	System.out.println("remaining kernels:" + JavaKernel.getAllKernels());
    }
}
