package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        HashMap<String, Double> salary = new HashMap<>();

        String fileName = args[0];
        ArrayList<String> fileLines = new ArrayList<>();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while(file.ready()) fileLines.add(file.readLine());
        file.close();

        for(String line : fileLines) {
            String[] fields = line.split(" ");
            if(salary.containsKey(fields[0])) {
                Double newValue = salary.get(fields[0]) + Double.parseDouble(fields[1]);
                salary.replace(fields[0], newValue);
            } else {
                salary.put(fields[0], Double.parseDouble(fields[1]));
            }
        }
        ArrayList<Map.Entry<String, Double>> list = new ArrayList<>(salary.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        ArrayList<Map.Entry<String, Double>> maxSalary = new ArrayList<Map.Entry<String, Double>>();
        double max = list.get(0).getValue();
        for(int i = 0; i < list.size(); i++) {
            if(max == list.get(i).getValue()) {
                maxSalary.add(list.get(i));
            } else {
                break;
            }
        }

        Collections.sort(maxSalary, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for(Map.Entry<String, Double> entry : maxSalary) {
            System.out.println(entry.getKey());
        }
    }
}
