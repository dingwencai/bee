package com.bee.parse;

import com.bee.parse.pojo.DPojo;
import com.bee.parse.utils.JDBCUtil;
import com.bee.parse.utils.Utils;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 将一个文件夹递归遍历，解析所有 xml 文件
 */
public class MyParse {

    public static int num = 0;

    public static List<String> failPathList = new ArrayList<String>();

    public static Set<String> fieldSet = new HashSet<String>();

    public static String FILE_PATH = "";
    public static String FILE_NAME = "";

    public static List<DPojo> resultList = new ArrayList<>();

    public static int length = 0;
    public static String filedNmae = "";


    public static void main(String[] args) {
        traverseFolder(Utils.getPropertiesValue("filePath"));

        System.out.println("出错文件个数："+num);
        for (String path:failPathList
             ) {
            System.out.println(path);
        }
        System.out.println("field size:"+fieldSet.size());
        for (String field:fieldSet
             ) {
            System.out.println(field);
        }
        System.out.println("结果个数："+resultList.size());

        try {
            JDBCUtil.insertByPreparedStatement(resultList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        if(file2.getAbsolutePath().contains(".xml")) parseXml(file2.getAbsolutePath());
                        //System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void parseXml(final String filePath) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = builderFactory.newDocumentBuilder();
            /*
             * builder.parse()方法将给定文件的内容解析为一个 XML 文档， 并且返回一个新的 DOM Document对象。
             */
            File file = new File(filePath);
            FILE_NAME = file.getName();
            document = builder.parse(file);
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            //TODO 解析XML文件时，无效的XML 字符 (Unicode: 0x1)异常处理
            e.printStackTrace();
            num++;
            failPathList.add(filePath);
            return;
        }
        //获取文档的根元素，赋值给rootElement变量
        if (document == null) return;
        Element rootElement = document.getDocumentElement();
        NodeList childNodess = rootElement.getChildNodes();//2 级节点list
        for (int i = 0; i < childNodess.getLength(); i++) {
            //获取childNodes的第i个节点
            Node childNode = childNodess.item(i); //2 级节点中具体的一个 节点
            getNodeAttributeNameAndValue(childNode);
        }
    }

    //遍历
    public static void getNodeAttributeNameAndValue(Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            NamedNodeMap nodeAttributes = node.getAttributes();

            for (int k = 0; k < nodeAttributes.getLength(); k++) {
                DPojo obj = new DPojo();
                Node attr = nodeAttributes.item(k);
                obj.setFileName(FILE_NAME);
                if (length < attr.getNodeValue().length()){
                    length = attr.getNodeValue().length();
                    filedNmae = attr.getNodeName();
                }
                obj.setKey(attr.getNodeName());
                obj.setValue(attr.getNodeValue());
                resultList.add(obj);
            }
        }
        //递归遍历当前节点所有的子节点
        NodeList childNodes = node.getChildNodes();//所有一级子节点的list
        for (int i = 0; i < childNodes.getLength(); i++) {//遍历所有一级子节点
            getNodeAttributeNameAndValue(childNodes.item(i));//递归
        }
    }
}
