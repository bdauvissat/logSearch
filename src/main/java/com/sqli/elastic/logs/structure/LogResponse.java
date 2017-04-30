package com.sqli.elastic.logs.structure;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Benjamin on 22/01/2017.
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class LogResponse {

    private LocalDateTime timestamp;
    private String id;
    private String index;
    private String host;
    private String logdatetime;
    private String logeventdatetime;
    private String logmessage;
    private String logoutputname;
    private String path;
    private String type;


    public LogResponse() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        setTimestamp(LocalDateTime.parse(timestamp, formatter));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLogdatetime() {
        return logdatetime;
    }

    public void setLogdatetime(String logdatetime) {
        this.logdatetime = logdatetime;
    }

    public String getLogeventdatetime() {
        return logeventdatetime;
    }

    public void setLogeventdatetime(String logeventdatetime) {
        this.logeventdatetime = logeventdatetime;
    }

    public String getLogmessage() {
        return logmessage;
    }

    public void setLogmessage(String logmessage) {
        this.logmessage = logmessage;
    }

    public String getLogoutputname() {
        return logoutputname;
    }

    public void setLogoutputname(String logoutputname) {
        this.logoutputname = logoutputname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
