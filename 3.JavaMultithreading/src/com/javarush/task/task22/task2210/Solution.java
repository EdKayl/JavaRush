package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String [] getTokens(String query, String delimiter) {
        if(query == null || delimiter == null || query.isEmpty() || delimiter.isEmpty()) return null;
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        ArrayList<String> result = new ArrayList<>();
        while(tokenizer.hasMoreElements()) {
            result.add(tokenizer.nextToken());
        }

        return result.toArray(new String[result.size()]);
    }
}
