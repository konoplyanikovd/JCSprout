package com.konoplyanikovd.interview.actual;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class ReadFile {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReadFile.class);

    private static List<String> content ;
    private static String path ="/Users/chenjie/Desktop/test.log" ;

    private static final String KEYWORD = "login" ;


    private static Map<String,Integer> countMap ;

    private static Set<SortString> contentSet ;
    public static void main(String[] args) {
        contentSet = new TreeSet<>() ;
        countMap = new HashMap<>(30) ;
        File file = new File(path) ;
        try {
            sortAndFindKeyWords(file);

            LOGGER.info(contentSet.toString());

        } catch (IOException e) {
            LOGGER.error("IOException",e);
        }
    }

    private static void sortAndFindKeyWords(File file) throws IOException {
        content = Files.readLines(file, Charset.defaultCharset());
        LOGGER.info(String.valueOf(content));

        for (String value : content) {

            boolean flag = value.contains(KEYWORD) ;
            if (!flag){
                continue;
            }

            if (countMap.containsKey(value)){
                countMap.put(value,countMap.get(value) + 1) ;
            } else {
                countMap.put(value,1) ;
            }
        }

        for (String key :countMap.keySet()){
            SortString sort = new SortString() ;
            sort.setKey(key);
            sort.setCount(countMap.get(key));

            contentSet.add(sort) ;
        }

    }

    private static class SortString implements Comparable<SortString>{

        private String key ;
        private Integer count ;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }


        @Override
        public int compareTo(SortString o) {
            if (this.getCount() >o.getCount()){
                return 1;
            }else {
                return -1 ;
            }
        }

        @Override
        public String toString() {
            return "SortString{" +
                    "key='" + key + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
