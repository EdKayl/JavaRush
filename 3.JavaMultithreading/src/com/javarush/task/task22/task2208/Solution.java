package com.javarush.task.task22.task2208;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", null);
        map.put("country", null);
        map.put("city", null);
        map.put("age", null);

        System.out.println(getQuery(map));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(params.entrySet());

        for(int i = 0; i < list.size(); i++) {
            Map.Entry<String, String> entry = list.get(i);
            if(entry.getValue() == null) continue;
            result.append(String.format("%s = '%s' and ", entry.getKey(), entry.getValue()));
        }
        String res = result.toString();
        if(res == "" || res.isEmpty()) return "";
        return res.substring(0, res.lastIndexOf(" and "));
    }
}
