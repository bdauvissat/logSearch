package com.sqli.elastic.logs.controller;

/**
 * Created by Benjamin on 22/01/2017.
 */

import com.sqli.elastic.logs.structure.LogResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LogController {

    @RequestMapping("/searchlog")
    public LogResponse coucou(@RequestParam(value="message") String message) {
        return new LogResponse(1l,"coucou", new Date());

    }
}
