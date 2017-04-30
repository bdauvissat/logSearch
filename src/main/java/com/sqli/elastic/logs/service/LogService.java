package com.sqli.elastic.logs.service;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.elastic.logs.structure.Connection;
import com.sqli.elastic.logs.structure.LogResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

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

    public List<LogResponse> giveLogList() throws IOException {

        connect();

        SearchResponse response = connection.getTc().prepareSearch().execute().actionGet();

        SearchHit[] results = response.getHits().getHits();
        for(SearchHit hit : results){

            String sourceAsString = hit.getSourceAsString();
            Long id = hit.field("id").<Long>getValue();
        }

        closeConnection();

        return listLogs;
    }

    public void checkConnection(String server, Integer port) throws UnknownHostException {
        connection.setServer(server);
        connection.setPort(port);

        connect();
        closeConnection();
    }

    public List<String> listIndices() {
        try {
            connect();

            GetSettingsResponse response = connection.getTc().admin().indices().prepareGetSettings().get();
            List<String> lstIndexes = new ArrayList<String>();
            for (ObjectObjectCursor<String, Settings> cursor : response.getIndexToSettings()) {
                lstIndexes.add(cursor.key);
            }
            return lstIndexes;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public LogResponse logDetail(String index, String id) throws UnknownHostException {
        connect();

        GetResponse reponse = connection.getTc().prepareGet(index, "log", id).get();

        Map<String, String> trad = cleanKeyNames(reponse.getSourceAsMap());

        ObjectMapper om = new ObjectMapper();
        LogResponse result = om.convertValue(trad, LogResponse.class);

        result.setId(reponse.getId());
        result.setIndex(reponse.getIndex());

        closeConnection();

        return result;
    }

    private Map<String, String> cleanKeyNames(Map<String, Object> theMap) {
        Set<String> keyList = theMap.keySet();
        Map<String, String> retour = new HashMap<>();

        for (String key:keyList) {
            String newKey = key;
            if(key.contains("@")) {
                newKey = key.replace("@", "");
            }

            if(key.contains("_")) {
                newKey = key.replace("_", "");
            }
            retour.put(newKey, theMap.get(key).toString());
        }
        return retour;
    }

    private void connect() throws UnknownHostException {
        connection.setTc(new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(connection.getServer()), connection.getPort())));
    }

    private void closeConnection() {
        connection.getTc().close();
    }
}
