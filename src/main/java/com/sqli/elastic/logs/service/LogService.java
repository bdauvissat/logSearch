package com.sqli.elastic.logs.service;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.sqli.elastic.logs.structure.Connection;
import com.sqli.elastic.logs.structure.LogResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Benjamin on 18/04/2017.
 */
@Service
public class LogService {

    private List<LogResponse> listLogs = new ArrayList<LogResponse>();
    private Connection connection = new Connection();

    public int countLines() {
        return listLogs.size();
    }

    public List<LogResponse> giveLogList() {
        listLogs.add(new LogResponse("1", "Coucou", new Date(), "", "", "", "", "", ""));
        return listLogs;
    }

    public void checkConnection(String server, Integer port) throws UnknownHostException {
        connection.setServer(server);
        connection.setPort(port);

        TransportClient tc = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(connection.getServer()), connection.getPort()));
    }

    public List<String> listIndices() {
        try {
            TransportClient tc = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(connection.getServer()), connection.getPort()));

            GetSettingsResponse response = tc.admin().indices().prepareGetSettings().get();
            List<String> lstIndexes = new ArrayList<String>();
            for (ObjectObjectCursor<String, Settings> cursor : response.getIndexToSettings()) {
                lstIndexes.add(cursor.key);
            }
            return lstIndexes;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
