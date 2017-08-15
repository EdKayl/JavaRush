package com.javarush.task.task22.task2209;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> listLines = Files.readAllLines(Paths.get(reader.readLine()));
        reader.close();

        TreeSet<String> setWords = new TreeSet<>();
        for(String line : listLines) {
            String[] lineWords = line.split(" ");
            for(String word : lineWords) setWords.add(word);
        }
        String[] words = setWords.toArray(new String[setWords.size()]);

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0) return result;
        if(words.length == 1) return result.append(words[0]);

        ArrayList<String> listWords = new ArrayList<>(Arrays.asList(words));
        boolean isCorrect;
        while (true) {
            Collections.shuffle(listWords);
            isCorrect = true;
            for (int i = 0; i < listWords.size(); i++) {
                if (i + 1 != listWords.size()) {
                    String first = listWords.get(i);
                    String second = listWords.get(i + 1);
                    if (!first.substring(first.length() - 1).equals(second.substring(0, 1).toLowerCase())) {
                        isCorrect = false;
                        break;
                    }
                }
            }
            if (isCorrect) break;
        }

        for (int i = 0; i < listWords.size(); i++) {
            result.append(listWords.get(i));
            if (i != listWords.size() - 1) {
                result.append(" ");
            }
        }
        return result;
    }
    /*
    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if(words == null || words.length == 0) return result;

        ArrayList<String> listWords = new ArrayList<>(Arrays.asList(words));

        //first city
        String currWord = listWords.get(0);
        result.append(currWord);
        String lastSymbol = currWord.substring(currWord.length() - 1);
        listWords.remove(0);

        for(int i = 0; i < listWords.size(); i++) {
            currWord = listWords.get(i);
            String firstSymbol = currWord.substring(0, 1);
            if(lastSymbol.equals(firstSymbol.toLowerCase())) {
                result.append(" " + currWord);
                listWords.remove(i);
                lastSymbol = currWord.substring(currWord.length() - 1);
                i = -1;
            }
        }
        return result;
    }
    */
}
