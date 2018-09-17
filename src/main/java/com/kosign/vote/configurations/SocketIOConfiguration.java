package com.kosign.vote.configurations;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class SocketIOConfiguration {

    private final String SOCKET_HOST = "0.0.0.0";
    private final int SOCKET_PORT = 9090;
    private SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config=new com.corundumstudio.socketio.Configuration();
        config.setHostname(SOCKET_HOST);
        config.setPort(SOCKET_PORT);
        server = new SocketIOServer(config);
        server.start();

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Client connected : " + client.getSessionId());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("Client disconnected : " + client.getSessionId());
            }
        });

        return server;
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }

}
