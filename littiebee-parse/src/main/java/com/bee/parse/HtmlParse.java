package com.bee.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrCai on 2018/11/02.
 */
public class HtmlParse {

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("xx")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
                    .get();
            Elements tags = doc.getElementsByTag("tr");

            List<String> list = new ArrayList<String>();
            for(int i = 0 ;i < tags.size() ;i++) {
                Element e = tags.get(i);
                Elements elements = e.getAllElements();
                for (int j = 0 ; j< elements.size() ; j++) {
                    Element ee = elements.get(j);
                    if(ee.is("tr")){
                        String text = ee.text();
                        list.add(text);
                    }
                }
            }
            File file = new File("./66.txt");
            if (!file.isFile()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("./66.txt"));
            for (String l:list){
                writer.write(l + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
