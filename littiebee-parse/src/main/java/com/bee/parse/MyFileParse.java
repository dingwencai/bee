package com.bee.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class MyFileParse {

    public static void main(String[] args) {
        File file = new File("F:\\notepad文档\\124.txt");
        try {
            int count = 0;
            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ( ( line = bfr.readLine()) != null){
                String[] array = line.split(" ");
                //when (t1.page = 'pages/index/index') then '微信小程序-首页'
                System.out.println("when (t1.page = '" + array[1] +"') then '百度小程序-" + array[0] +"'");
            }

            System.out.println(count);

        } catch (IOException e){
            e.printStackTrace();
        }

    }


}
