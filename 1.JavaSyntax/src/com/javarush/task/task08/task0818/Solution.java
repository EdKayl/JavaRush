package com.javarush.task.task08.task0818;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Name1", 200);
        map.put("Name2", 600);
        map.put("Name3", 200);
        map.put("Name4", 300);
        map.put("Name5", 400);
        map.put("Name6", 500);
        map.put("Name7", 200);
        map.put("Name8", 500);
        map.put("Name9", 800);
        map.put("Name10", 700);
        return map;

    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        ArrayList<String> keys = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() < 500) keys.add(entry.getKey());
        }
        for(String key : keys) map.remove(key);
    }

    public static void main(String[] args) {

    }
}