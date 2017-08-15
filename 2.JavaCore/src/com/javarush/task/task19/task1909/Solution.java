package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

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

        String fileContent = buffer.toString();
        destinationFile.write(fileContent.replaceAll("\\.", "!"));
        destinationFile.close();
    }
}
