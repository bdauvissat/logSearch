package com.sqli.elastic.logs.structure;

import java.util.List;

/**
 * Created by Benjamin on 21/04/2017.
 */
public class Connection {

    private String server;
    private Integer port;
    private List<String> indexes;

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

    public List<String> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<String> indexes) {
        this.indexes = indexes;
    }
}
