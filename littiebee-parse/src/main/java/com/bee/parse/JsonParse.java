package com.bee.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class JsonParse {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\1.txt");
        try {
            int count = 0;
            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ( ( line = bfr.readLine()) != null){
                String[] array = line.split("\\t");
                JSONObject obj = JSON.parseObject(array[1]);
                int q = Integer.parseInt(array[0]);
                int a = Integer.parseInt(obj.get("a").toString());
                int c = Integer.parseInt(obj.get("c").toString());
                count += q * (a + c);
            }

            System.out.println(count);

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
