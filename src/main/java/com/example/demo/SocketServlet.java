package com.example.demo;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/game")
public class SocketServlet {

    // Create a set of sessions to hold the different user sessions
    private static final Set<Session> sessions = new HashSet<>();
    Session user;
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("Socket session added"); // Console debugger
        onMessage("\nSession added", session);
    }

    @OnMessage
    public void onMessage(String message, Session session){
        for (Session s : sessions) {
            if (s.isOpen()) {
                try {
                    s.getBasicRemote().sendText("User: " + session + "\n\n"+ message);
                    //s.getBasicRemote().sendText(message);
                    System.out.println("Message broadcasted");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void leaveGame (Session session){
        for (Session s : sessions) {
            if (s.isOpen()){
                try {
                    s.getBasicRemote().sendText(user + "\n just left the game");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @OnClose
    public void onClose(Session session) {
        user = session;
        sessions.remove(session);
        leaveGame(user);
    }
}
