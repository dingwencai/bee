package tech.dwen.stu.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class C {

    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();
        m.put(null,"");
        m.put("", null);

        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("", null);
        map.put(null,"");

        Hashtable<String, String> t = new Hashtable<>();
        t.put("", null);
        t.put(null, "");
    }

}
