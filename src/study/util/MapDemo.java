package study.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("name", "157");
        map.put("name", "231");
        map.put("value", "423");

        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Iterator<Map.Entry<String, String>> it = entrySet.iterator(); it.hasNext(); ){
            Map.Entry<String,String> me = it.next();
            System.out.println(me.getKey() + me.getValue());
        }
    }
}
