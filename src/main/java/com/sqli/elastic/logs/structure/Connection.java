package com.sqli.elastic.logs.structure;

import org.elasticsearch.client.transport.TransportClient;

import java.util.List;

/**
 * Created by Benjamin on 21/04/2017.
 */
public class Connection {

    private String server;
    private Integer port;
    private TransportClient tc;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public TransportClient getTc() {
        return tc;
    }

    public void setTc(TransportClient tc) {
        this.tc = tc;
    }
}
