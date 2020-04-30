package com.JavaShell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import com.DataModels.ChannelNames;
import com.DataModels.ClearDisplayContent;
import com.DataModels.ClearDisplayMessage;
import com.DataModels.DisplayDataContent;
import com.DataModels.DisplayDataMessage;
import com.DataModels.ExecuteInputContent;
import com.DataModels.ExecuteInputMessage;
import com.DataModels.ExecuteReplyContent;
import com.DataModels.ExecuteReplyMessage;
import com.DataModels.ExecuteRequestMessage;
import com.DataModels.ExecuteResultContent;
import com.DataModels.ExecuteResultMessage;
import com.DataModels.ExecuteStatus;
import com.DataModels.ExecutionState;
import com.DataModels.KernelStatusContent;
import com.DataModels.KernelStatusMessage;
import com.DataModels.Message;
import com.DataModels.MessageDecoder;
import com.DataModels.MessageEncoder;
import com.DataModels.MessageHeader;
import com.DataModels.MessageTypes;
import com.DataModels.StreamContent;
import com.DataModels.StreamMessage;
import com.DataModels.StreamName;
import com.Helpers.Display;

public class Utils {
	private static MessageEncoder encoder = new MessageEncoder();
	private static MessageDecoder decoder = new MessageDecoder();
	

    public static void sendDisplayDataMessage(Session session, Message parent, String mimeType, String displayString) throws IOException, EncodeException {
        DisplayDataMessage displayDataMessage = createDisplayDataMessage(parent, mimeType, displayString);
    	session.getBasicRemote().sendText(encoder.encode(displayDataMessage));
    }
    
    public static DisplayDataMessage createDisplayDataMessage(Message parent, String mimeType, String displayString) {
    	HashMap<String, String> content = new HashMap<String, String>();
    	content.put(mimeType, displayString);
    	
    	DisplayDataContent displayDataContent = new DisplayDataContent(content, new HashMap<String, String>());
    	
    	String messageId = UUID.randomUUID().toString();
    	MessageHeader messageHeader = new MessageHeader(messageId, MessageTypes.DisplayData.toString(), parent.getHeader().getSession());
    	
        return new DisplayDataMessage(displayDataContent,
        		parent.getBuffers(),
        		ChannelNames.IoPub.toString(),
        		MessageTypes.DisplayData.toString(),
        		messageId,
        		messageHeader,
        		parent.getHeader(),
        		new HashMap<String, String>());
    }

    public static void sendKernelStatusMessage(Session session, Message parent, ExecutionState status) throws IOException, EncodeException {
        KernelStatusMessage statusMessage = createKernelStatusMessage(parent, status);
    	session.getBasicRemote().sendText(encoder.encode(statusMessage));
    }

    public static KernelStatusMessage createKernelStatusMessage(Message parent, ExecutionState status) {
    	KernelStatusContent kernelStatusContent = new KernelStatusContent(status.toString());
    	
    	String messageId = UUID.randomUUID().toString();
    	MessageHeader messageHeader = new MessageHeader(messageId, MessageTypes.Status.toString(), parent.getHeader().getSession());
    	
        return new KernelStatusMessage(kernelStatusContent,
        		parent.getBuffers(),
        		ChannelNames.IoPub.toString(),
        		MessageTypes.Status.toString(),
        		messageId,
        		messageHeader,
        		parent.getHeader(),
        		new HashMap<String, String>());
    }
    
    public static void sendExecuteResultMessage(Session session, Message parent, int executionCount, String displayString) throws IOException, EncodeException {
    	ExecuteResultMessage executeResultMessage = createExecuteResultMessage(parent, executionCount, displayString);
    	session.getBasicRemote().sendText(encoder.encode(executeResultMessage));
    }


    public static ExecuteResultMessage createExecuteResultMessage(Message parent, int executionCount, String displayString) {
    	HashMap<String, String> content = new HashMap<String, String>();
    	content.put("text/plain", displayString);
    	
    	ExecuteResultContent executeResultContent = new ExecuteResultContent(executionCount, content, new HashMap<String, String>());
    	
    	String messageId = UUID.randomUUID().toString();
    	MessageHeader messageHeader = new MessageHeader(messageId, MessageTypes.ExecuteResult.toString(), parent.getHeader().getSession());
    	
        return new ExecuteResultMessage(executeResultContent,
        		parent.getBuffers(),
        		ChannelNames.IoPub.toString(),
        		MessageTypes.ExecuteResult.toString(),
        		messageId,
        		messageHeader,
        		parent.getHeader(),
        		new HashMap<String, String>());
    }
    
    public static void sendExecuteInputMessage(Session session, Message parent, Integer executionCount, String code) throws IOException, EncodeException {
    	ExecuteInputMessage executeInputMessage = createExecuteInputMessage(parent, executionCount, code);
    	session.getBasicRemote().sendText(encoder.encode(executeInputMessage));
    }


    public static ExecuteInputMessage createExecuteInputMessage(Message parent, int executionCount, String code) {
    	ExecuteInputContent executeInputContent = new ExecuteInputContent(code, executionCount);
    	
    	String messageId = UUID.randomUUID().toString();
    	MessageHeader messageHeader = new MessageHeader(messageId, MessageTypes.ExecuteInput.toString(), parent.getHeader().getSession());
    	
        return new ExecuteInputMessage(executeInputContent,
        		parent.getBuffers(),
        		ChannelNames.IoPub.toString(),
        		MessageTypes.ExecuteInput.toString(),
        		messageId,
        		messageHeader,
        		parent.getHeader(),
        		new HashMap<String, String>());
    }
    
    public static void sendExecuteReplyMessage(Session session, Message parent, Integer executionCount, ExecuteStatus executeStatus) throws IOException, EncodeException {
    	ExecuteReplyMessage executeReplyMessage = createExecuteReplyMessage(parent, executionCount, executeStatus);
    	session.getBasicRemote().sendText(encoder.encode(executeReplyMessage));
    }
    
    public static ExecuteReplyMessage createExecuteReplyMessage(Message parent, int executionCount, ExecuteStatus executeStatus) {
    	ExecuteReplyContent executeReplyContent = new ExecuteReplyContent(executeStatus.toString(), executionCount);
    	
    	String messageId = UUID.randomUUID().toString();
    	MessageHeader messageHeader = new MessageHeader(messageId, MessageTypes.ExecuteReply.toString(), parent.getHeader().getSession());
    	
        return new ExecuteReplyMessage(executeReplyContent,
        		parent.getBuffers(),
        		ChannelNames.Shell.toString(),
        		MessageTypes.ExecuteReply.toString(),
        		messageId,
        		messageHeader,
        		parent.getHeader(),
        		new HashMap<String, String>());
    }

    public static void sendStreamMessage(Session session, Message parent, StreamName streamName, String text) throws IOException, EncodeException {
    	StreamMessage streamMessage = createStreamMessage(parent, streamName, text);
    	session.getBasicRemote().sendText(encoder.encode(streamMessage));
    }


    public static StreamMessage createStreamMessage(Message parent, StreamName streamName, String text) {
    	StreamContent streamContent = new StreamContent(streamName.toString(), text);
    	
    	String messageId = UUID.randomUUID().toString();
    	MessageHeader messageHeader = new MessageHeader(messageId, MessageTypes.Stream.toString(), parent.getHeader().getSession());
    	
        return new StreamMessage(streamContent,
        		parent.getBuffers(),
        		ChannelNames.IoPub.toString(),
        		MessageTypes.Stream.toString(),
        		messageId,
        		messageHeader,
        		parent.getHeader(),
        		new HashMap<String, String>());
    }

    public static void sendClearDisplayMessage(Session session, Message parent, boolean shouldWait) throws IOException, EncodeException {
    	ClearDisplayMessage clearDisplayMessage = createClearDisplayMessage(parent, shouldWait);
    	session.getBasicRemote().sendText(encoder.encode(clearDisplayMessage));
    }


    public static ClearDisplayMessage createClearDisplayMessage(Message parent, boolean shouldWait) {
    	ClearDisplayContent clearDisplayContent = new ClearDisplayContent(shouldWait);
    	
    	String messageId = UUID.randomUUID().toString();
    	MessageHeader messageHeader = new MessageHeader(messageId, MessageTypes.ClearOutput.toString(), parent.getHeader().getSession());
    	
        return new ClearDisplayMessage(clearDisplayContent,
        		parent.getBuffers(),
        		ChannelNames.IoPub.toString(),
        		MessageTypes.ClearOutput.toString(),
        		messageId,
        		messageHeader,
        		parent.getHeader(),
        		new HashMap<String, String>());
    }
    
    public static void executeRequest(Session session, ExecuteRequestMessage parent, String kernelId) throws IOException, EncodeException {
    	Integer executionCount = parent.getContent().getExecutionCount();
    	if (executionCount == null) {
    		executionCount = 0;
    	}
    	
    	executionCount++;
    	
    	sendKernelStatusMessage(session, parent, ExecutionState.Busy);
    	sendExecuteInputMessage(session, parent, parent.getContent().getExecutionCount(), parent.getContent().getCode());

		JShellResult result = JavaKernel.getResult(kernelId, parent.getContent().getCode());
	          
     	System.out.println("received:" + parent.getContent().getCode() + " sent:" + result.toString());

    	if (result.getError() != null) {
        	sendStreamMessage(session, parent, StreamName.StandardError, result.getError());
    	} else if (result.getStreamOutput() != null) {
        	sendStreamMessage(session, parent, StreamName.StandardOut, result.getStreamOutput());
    	}
    	
    	if (result.getDisplayParameters() != null) {
    		ArrayList<String> mimeFormats = result.getDisplayParameters().getMimeFormats();
    		ArrayList<String> displayStrings = result.getDisplayParameters().getDisplayStrings();
    		
    		for (int i = 0; i < mimeFormats.size(); i++) {
    			sendDisplayDataMessage(session, parent, mimeFormats.get(i), displayStrings.get(i));
    		}
    	}
    	
		sendExecuteResultMessage(session, parent, executionCount, result.getResult());
	
    	sendKernelStatusMessage(session, parent, ExecutionState.Idle);
    	sendExecuteReplyMessage(session, parent, executionCount, ExecuteStatus.Ok);
    }
}
