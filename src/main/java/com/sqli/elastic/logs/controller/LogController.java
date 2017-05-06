package com.sqli.elastic.logs.controller;

/**
 * Created by Benjamin on 22/01/2017.
 */

import com.sqli.elastic.logs.service.LogService;
import com.sqli.elastic.logs.structure.LogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/checkConnection/{server}/{port}")
    public String checkConnection(@PathVariable String server, @PathVariable Integer port) {
        String retour = "OK";
        try {
            logService.checkConnection(server, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            retour = "KO";
        }
        return retour;
    }

    @RequestMapping("/listIndexes")
    public List<String> listIndexes() {
        return logService.listIndices();
    }

    public List<LogResponse> listLogs(Date dateDebut, Date dateFin, String index, String logLevel) {
        return null;
    }

    @RequestMapping("/logDetail/{index}/{id}")
    public LogResponse logDetail(@PathVariable String index, @PathVariable String id) throws UnknownHostException {
        return logService.logDetail(index,id);
    }

    @RequestMapping("searchlog/{index}/{dateFrom}/{dateTo}")
    public List<LogResponse> searchLog(@PathVariable String index, @PathVariable String dateFrom, @PathVariable String dateTo) throws IOException {
        return logService.searchLogs(index, dateFrom, dateTo);
    }
}
