package com.sqli.elastic.logs.component;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Benjamin on 21/04/2017.
 */
@Component
public class Connection {

    private static final String SERVER = "server";
    private static final String PORT = "port";
    private static final String TC = "connexion";
    private ConcurrentHashMap<String, Object> chConnexion = new ConcurrentHashMap<>();

    public void openConnection() throws UnknownHostException {
        setTc(new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(getServer()), Integer.valueOf(getPort()))));
    }

    public void closeConnection() {
        getTc().close();
    }

    public String getServer() {

        if (chConnexion.containsKey(SERVER)) {
            return chConnexion.get(SERVER).toString();
        }
        return null;
    }

    public void setServer(String server) {
        this.chConnexion.put(SERVER, server);
    }

    public String getPort() {
        if (chConnexion.containsKey(PORT)) {
            return chConnexion.get(PORT).toString();
        }
        return null;
    }

    public void setPort(String port) {

        this.chConnexion.put(PORT, port);
    }

    public TransportClient getTc() {

        if (chConnexion.containsKey(TC)) {
            return (TransportClient) chConnexion.get(TC);
        }
        return null;
    }

    public void setTc(TransportClient tc) {

        this.chConnexion.put(TC, tc);
    }
}
