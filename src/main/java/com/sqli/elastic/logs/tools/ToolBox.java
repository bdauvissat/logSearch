package com.sqli.elastic.logs.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Benjamin on 05/05/2017.
 */
public abstract class ToolBox {

    public static final Map<String, String> cleanKeyNames(Map<String, Object> theMap) {
        Set<String> keyList = theMap.keySet();
        Map<String, String> retour = new HashMap<>();

        for (String key:keyList) {
            retour.put(key.replace("@", "").replace("_", ""), theMap.get(key).toString());
        }
        return retour;
    }

}
