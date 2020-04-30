package com.javacodegeeks.websocket_example;

import java.util.Objects;

import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/{username}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public final class ChatEndPoint {

    @OnOpen
    public void onOpen(@PathParam(Constants.USER_NAME_KEY) final String userName, final Session session) {
        if (Objects.isNull(userName) || userName.isEmpty()) {
            throw new RegistrationFailedException("User name is required");
        } else {
            session.getUserProperties().put(Constants.USER_NAME_KEY, userName);
            if (ChatSessionManager.register(session)) {
                System.out.printf("Session opened for %s\n", userName);

                ChatSessionManager.publish(new Message((String) session.getUserProperties().get(Constants.USER_NAME_KEY), "***joined the chat***"), session);
            } else {
                throw new RegistrationFailedException("Unable to register, username already exists, try another");
            }
        }
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
        if (throwable instanceof RegistrationFailedException) {
            ChatSessionManager.close(session, CloseCodes.VIOLATED_POLICY, throwable.getMessage());
        }
    }

    @OnMessage
    public void onMessage(final Message message, final Session session) {
        ChatSessionManager.publish(message, session);
    }

    @OnClose
    public void onClose(final Session session) {
        if (ChatSessionManager.remove(session)) {
            System.out.printf("Session closed for %s\n", session.getUserProperties().get(Constants.USER_NAME_KEY));

            ChatSessionManager.publish(new Message((String) session.getUserProperties().get(Constants.USER_NAME_KEY), "***left the chat***"), session);
        }
    }

    private static final class RegistrationFailedException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public RegistrationFailedException(final String message) {
            super(message);
        }
    }
}
