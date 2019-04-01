package com.bee.parse.file;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * 将CSV格式的数据转换为jsonArray
 */
public class Csv2Json {

    public static void main(String[] args) {

//        String s = "10653016.0";

        traverseFolder("C:\\Users\\Administrator\\Desktop\\download\\o");
    }

    public static void traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            // 获取目录下的 file 或 目录 列表
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    // 遍历此目录下的所有 file 或 目录
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        // TODO 以下为对单个 file 的操作
                        csvFile2Json(file2);
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void csvFile2Json(File file) {
        try {

            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            JSONArray array = new JSONArray();
            String line = bfr.readLine();

            while ( ( line = bfr.readLine()) != null){

//                String value = Long.valueOf(line.split(",")[0]).toString();
                String value = line.split(",")[0].split("\\.")[0].toString();
                String label = line.split(",")[1];

                JSONObject obj = new JSONObject();
                obj.put("value", value.trim());
                obj.put("label", label.trim());
                array.add(obj);
            }

            System.out.println(array.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
