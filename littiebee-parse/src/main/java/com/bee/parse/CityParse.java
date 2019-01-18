package com.bee.parse;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 选择地区工具，包含全国各地省级市级
 *
 */
public class CityParse {
    //各地区xml文件路径
    private static final String LOCAL_LIST_PATH = "./config/LocList.xml";
    //所有国家名称List
    private static final List<String> COUNTRY_REGION = new ArrayList<String>();
    private static CityParse localutil;
    private SAXReader reader;
    private Document document;
    private Element rootElement;		//根元素

    //初始化
    private CityParse(){
        //1.读取
        reader = new SAXReader();
        try {
            document = reader.read(LOCAL_LIST_PATH);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //2.获得根元素
        rootElement =  document.getRootElement();
        //3.初始化所有国家名称列表
        Iterator it =  rootElement.elementIterator();
        Element ele = null;
        while(it.hasNext()){
            ele = (Element)it.next();
            COUNTRY_REGION.add(ele.attributeValue("Name"));
        }
    }

    /**
     *
     * @TODO		功能：	获取所有国家名称
     * @return		List<String>
     */
    public List<String> getCountry(){
        return COUNTRY_REGION;
    }

    /**
     *
     * @TODO		功能：	根据国家名获取该国所有省份
     * @param countryName	国家名，从getCountry()从取出
     * @return		List<Element>
     */
    private List<Element> provinces(String countryName){
        Iterator it =  rootElement.elementIterator();
        List<Element> provinces = new ArrayList<Element>();
        Element ele = null;
        while(it.hasNext()){
            ele = (Element)it.next();
            COUNTRY_REGION.add(ele.attributeValue("Name"));
            if(ele.attributeValue("Name").equals(countryName)){
                provinces = ele.elements();
                break;
            }
        }
        return provinces;
    }

    /**
     *
     * @TODO		功能：	根据国家名获取该国所有省份
     * @param countryName	国家名，从getCountry()从取出
     * @return		List<String>
     */
    public List<String> getProvinces(String countryName){
        List<Element> tmp = this.provinces(countryName);
        List<String> list = new ArrayList<String>();
        for(int i=0; i<tmp.size(); i++){
            list.add(tmp.get(i).attributeValue("Name"));
        }
        return list;
    }

    /**
     *
     * @TODO		功能：根据国家名和省份名，获取该省城市名列表
     * @param
     * @param provinceName
     * @return
     */
    private List<Element> cities(String countryName, String provinceName){
        List<Element> provinces =  this.provinces(countryName);
        List<Element> cities = new ArrayList<Element>();
        if(provinces==null || provinces.size()==0){		//没有这个城市
            return cities;
        }

        for(int i=0; i<provinces.size(); i++){
            if(provinces.get(i).attributeValue("Name").equals(provinceName)){
                cities = provinces.get(i).elements();
                break;
            }
        }
        return cities;
    }

    /**
     *
     * @TODO		功能：根据国家名和省份名获取城市列表
     * @param countryName
     * @param provinceName
     * @return		List<String>
     */
    public List<String> getCities(String countryName, String provinceName){
        List<Element> tmp =  this.cities(countryName, provinceName);
        List<String> cities = new ArrayList<String>();
        for(int i=0; i<tmp.size(); i++){
            cities.add(tmp.get(i).attributeValue("Name"));
        }
        return cities;
    }

    public static CityParse getInstance(){
        if(localutil==null){
            localutil = new CityParse();
        }
        return localutil;
    }

    public static void main(String[] args) {

       // List<Map<String, String>> resultList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> province = getInstance().getProvinces("中国");
        System.out.println(province.size());
        for (String pro:
            province ) {
            list = getInstance().getCities("中国", pro);
            for (String city:list
                 ) {
                Map map = new HashMap();
                map.put(city, pro);
                resultList.add(city);
            }
            //list.addAll(getInstance().getCities("中国", pro));
        }
        System.out.println(resultList.size());

        File file = new File("./city.txt");
        try {
            if (!file.isFile()) {
                file.createNewFile();
            }
            BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
            for (String s:resultList
                 ) {
                bfw.write(s+"\n");
            }
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String city2province: resultList) {
            System.out.println(city2province);
        }
    }
}

