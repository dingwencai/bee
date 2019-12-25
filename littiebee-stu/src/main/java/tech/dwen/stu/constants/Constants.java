package tech.dwen.stu.constants;

import com.alibaba.fastjson.JSONObject;

import java.util.Comparator;
import java.util.function.Function;

public class Constants {

    //jdk8 lambda expression, usage: rec_score compare
    public static final Function<String, Comparator<Object>> sort =
            (Function<String, Comparator<Object>>) e ->  (e1, e2) -> {
                if (((JSONObject) e1).getDoubleValue(e) >= ((JSONObject) e2).getDoubleValue(e)) {
                    return -1;
                } else {
                    return 1;
                }
            };

}
