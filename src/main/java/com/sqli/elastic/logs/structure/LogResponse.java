package com.sqli.elastic.logs.structure;

import java.util.Date;

/**
 * Created by Benjamin on 22/01/2017.
 */
public class LogResponse {
    private final long id;
    private final String message;
    private final Date date;

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
}
