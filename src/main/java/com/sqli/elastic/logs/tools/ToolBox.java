package com.sqli.elastic.logs.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Benjamin on 05/05/2017.
 */
public abstract class ToolBox {

    public final static Map<String, String> cleanKeyNames(Map<String, Object> theMap) {
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

}
