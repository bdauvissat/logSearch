package com.sqli.elastic.logs.structure;

import java.util.Date;

/**
 * Created by Benjamin on 22/01/2017.
 */
public class LogResponse {
    private String id;
    private String message;
    private Date dateHeure;
    private String logLevel;
    private String host;
    private String path;
    private String server;
    private String exit;
    private String type;

    public LogResponse() {
    }

    public LogResponse(String id, String message, Date dateHeure, String logLevel, String host, String path, String server, String exit, String type) {
        this.id = id;
        this.message = message;
        this.dateHeure = dateHeure;
        this.logLevel = logLevel;
        this.host = host;
        this.path = path;
        this.server = server;
        this.exit = exit;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
