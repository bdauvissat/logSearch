package com.sqli.elastic.logs.service;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.elastic.logs.component.Connection;
import com.sqli.elastic.logs.structure.LogResponse;
import com.sqli.elastic.logs.tools.ToolBox;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by Benjamin on 18/04/2017.
 */
@Service
public class LogService {

    private Connection connection = new Connection();

    public void checkConnection(String server, Integer port) throws UnknownHostException {
        connection.setServer(server);
        connection.setPort(port);

        connection.openConnection();
        connection.closeConnection();
    }

    public List<String> listIndices() {
        try {
            connection.openConnection();

            GetSettingsResponse response = connection.getTc().admin().indices().prepareGetSettings().get();
            List<String> lstIndexes = new ArrayList<String>();
            for (ObjectObjectCursor<String, Settings> cursor : response.getIndexToSettings()) {
                lstIndexes.add(cursor.key);
            }
            return lstIndexes;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return null;
    }

    public LogResponse logDetail(String index, String id) throws UnknownHostException {
        connection.openConnection();

        GetResponse reponse = connection.getTc().prepareGet(index, "log", id).get();

        Map<String, String> trad = ToolBox.cleanKeyNames(reponse.getSourceAsMap());

        ObjectMapper om = new ObjectMapper();
        LogResponse result = om.convertValue(trad, LogResponse.class);

        result.setId(reponse.getId());
        result.setIndex(reponse.getIndex());

        connection.closeConnection();

        return result;
    }

    public List<LogResponse> searchLogs(String indexName, String fromDate, String toDate) throws IOException {
        List<LogResponse> retour = new ArrayList<>();

        connection.openConnection();

        SearchResponse reponse = connection.getTc().prepareSearch(indexName)
                .setSize(50)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.rangeQuery("@timestamp").from(fromDate).to(toDate))
                .get();
        SearchHit[] results = reponse.getHits().getHits();
        connection.closeConnection();

        for(SearchHit hit : results){

            String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {
                ObjectMapper mapper = new ObjectMapper();
                LogResponse rep = mapper.readValue(sourceAsString, LogResponse.class);
                rep.setId(hit.getId());
                rep.setIndex(hit.getIndex());
                retour.add(rep);
            }
        }

        return retour;
    }

}
