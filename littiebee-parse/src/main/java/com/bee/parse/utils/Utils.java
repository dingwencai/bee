package com.bee.parse.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    private static Properties properties = new Properties();

    static {
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("./config/config.properties"));
            properties.load(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertiesValue(String key){
        return properties.getProperty(key);
    }
}
