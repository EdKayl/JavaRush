package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sourceName = reader.readLine();
        String destinationName = reader.readLine();
        reader.close();

        BufferedReader sourceFile = new BufferedReader(new FileReader(sourceName));
        BufferedWriter destinationFile = new BufferedWriter(new FileWriter(destinationName));
        StringBuffer buffer = new StringBuffer();
        while(sourceFile.ready()) {
            buffer.append((char)sourceFile.read());
        }
        sourceFile.close();

        Pattern pattern = Pattern.compile("\\b\\d+?\\b");
        Matcher matcher = pattern.matcher(buffer);
        while(matcher.find()) {
            destinationFile.write(matcher.group() + " ");
        }
        destinationFile.close();
    }
}
