package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception{
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream in = new FileInputStream(fileName);
        load(in);
        in.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        ArrayList<Map.Entry<String, String>> entries = new ArrayList<>(Solution.properties.entrySet());
        for(Map.Entry<String, String> entry : entries) {
            prop.setProperty(entry.getKey(), entry.getValue());
        }
        prop.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);
        ArrayList<Map.Entry<Object, Object>> entries = new ArrayList<>(prop.entrySet());
        for(Map.Entry<Object, Object> entry : entries) {
            Solution.properties.put((String)entry.getKey(), (String)entry.getValue());
        }
    }

    public static void main(String[] args) {

    }
}
