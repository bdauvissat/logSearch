package com.sqli.elastic.logs.component;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Benjamin on 21/04/2017.
 */
public class Connection {

    private String server;
    private Integer port;
    private TransportClient tc;

    public void openConnection() throws UnknownHostException {
        setTc(new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(getServer()), getPort())));
    }

    public void closeConnection() {
        getTc().close();
    }

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
