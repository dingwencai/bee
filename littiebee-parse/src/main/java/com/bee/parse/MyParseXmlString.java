package com.bee.parse;

import com.bee.parse.pojo.DPojo;
import com.bee.parse.utils.JDBCUtil;
import com.bee.parse.utils.Utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyParseXmlString {

    public static List<DPojo> list = new ArrayList<>();

    public static void main(String[] args) {
        String sql = Utils.getPropertiesValue("sql");
        System.out.println("Begin TimeMillis:"+System.currentTimeMillis());
        try {
            Connection con = JDBCUtil.getConnection();

            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            int num = 0;
            while(rs.next()) {
                num++;
                String xml =rs.getString("xml");
                //解析 xml
                Object obj = xml2Object(xml);

                if(num % 10000 == 0){
                    System.out.println("已经处理完 xml 数："+num);
                    System.out.println("当前时间：" + System.currentTimeMillis());

                    JDBCUtil.insertByPreparedStatement(list);
                    System.out.println("已处理结果个数："+list.size());
                    list.clear();
                }
            }
            JDBCUtil.insertByPreparedStatement(list);
            System.out.println("已处理结果个数："+list.size());
            //list.clear();
        }catch(SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End TimeMillis:"+System.currentTimeMillis());
    }

    /**
     * 读取XML文件并返回相应的对象
     *
     * @param objSource 输出文件的路径
     * @return 由XML转换出来的对象
     */
    public static Object xml2Object(String objSource) {
        //初始化各种对象
        if(objSource == null) return  null;
        InputStream is = new ByteArrayInputStream(objSource.getBytes());
        XMLDecoder decoder = new XMLDecoder(is);
        Object obj = null;
        try {
            //真正的核心部分，用于读取Object
            obj = decoder.readObject();
            //关闭流
            is.close();
            decoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将传入的obj对象转换为XML文件
     * @param obj 需要转换的对象
     * @param fileName 需要写出的文件路径
     */
    public static void objectXmlEncoderList(Object obj,String fileName) throws IOException {
        // 创建输出文件
        File fo = new File(fileName);
        // 文件不存在,就创建该文件
        if (!fo.exists()) {
            // 先创建文件的目录
            String path = fileName.substring(0, fileName.lastIndexOf('.'));
            File pFile = new File(path);
            pFile.mkdirs();
        }
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream(fo);
        // 创建XML文件对象输出类实例
        XMLEncoder encoder = new XMLEncoder(fos);
        // 对象序列化输出到XML文件
        encoder.writeObject(obj);
        encoder.flush();
        // 关闭序列化工具和输出流
        encoder.close();
        fos.close();
    }
}
