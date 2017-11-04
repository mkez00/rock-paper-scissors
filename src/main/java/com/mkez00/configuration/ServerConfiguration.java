package com.mkez00.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Created by michaelkezele on 2017-10-30.
 */
@Configuration
public class ServerConfiguration {
    private String serverId;

    public ServerConfiguration(){
        serverId = UUID.randomUUID().toString();
    }

    public String getServerId() {
        return serverId;
    }
}
