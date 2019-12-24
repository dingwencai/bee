package tech.dwen.stu.lambda;

import tech.dwen.stu.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class MyCollection {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.sort(Constants.sort.apply("rec_score"));
    }

}
