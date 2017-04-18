package com.sqli.elastic.logs.structure;

import java.util.Date;

/**
 * Created by Benjamin on 22/01/2017.
 */
public class LogResponse {
    private long id;
    private String message;
    private Date date;

    public LogResponse() {
    }

    public LogResponse(long id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
