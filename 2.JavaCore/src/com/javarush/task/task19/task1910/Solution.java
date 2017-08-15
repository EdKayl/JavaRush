package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sourceName = reader.readLine();
        String destinationName = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(sourceName));
        StringBuffer content = new StringBuffer();
        while(fileReader.ready()) {
            content.append((char)fileReader.read());
        }
        fileReader.close();

        String fileContent = content.toString();
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(destinationName));
        fileWriter.write(fileContent.replaceAll("[\\p{Punct}\\n]", ""));
        fileWriter.close();

    }
}
