package com.sqli.elastic.logs.service;

import com.sqli.elastic.logs.structure.LogResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Benjamin on 18/04/2017.
 */
@Service
public class LogService {

    private List<LogResponse> listLogs = new ArrayList<LogResponse>();

    public int countLines() {
        return listLogs.size();
    }

    public List<LogResponse> giveLogList() {
        listLogs.add(new LogResponse(1l, "Coucou", new Date()));
        return listLogs;
    }
}
