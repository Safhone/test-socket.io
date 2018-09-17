package com.kosign.vote.controllers;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.kosign.vote.models.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SocketIOController {

    private SocketIONamespace namespace;

    public SocketIONamespace getNamespace() {
        return namespace;
    }

    @Autowired
    public SocketIOController(SocketIOServer server) {
        this.namespace = server.addNamespace("/vote");
    }

    public void broadcastEvent(String event, Object data){
        this.getNamespace().getBroadcastOperations().sendEvent(event,data);
    }

}
