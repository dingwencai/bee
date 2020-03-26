package tech.dwen.stu.collection;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by MrCai on 2020/1/5.
 */
public class MyList {

    public static void main(String[] args) {
        new ArrayList<String>().add("");

        new CopyOnWriteArrayList<>().add("");

        new CopyOnWriteArraySet<>().add("");

        new ConcurrentHashMap<>().put("","");
    }

}
