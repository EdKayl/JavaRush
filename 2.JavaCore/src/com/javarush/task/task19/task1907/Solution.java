package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        FileReader fileReader = new  FileReader(fileName);
        StringBuffer content = new StringBuffer();
        while(fileReader.ready()) {
            content.append((char)fileReader.read());
        }
        fileReader.close();

        int countWorld = 0;
        Pattern pattern = Pattern.compile("\\bworld\\b");
        Matcher matcher = pattern.matcher(content);

        while(matcher.find()) {
            countWorld++;
        }
        System.out.println(countWorld);
    }
}
