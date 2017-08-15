package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        /*
        String inName = args[0];
        String outName = args[1];
        FileReader inFile = new FileReader(inName);

        StringBuffer fileContent = new StringBuffer();
        while (inFile.ready()) fileContent.append((char) inFile.read());
        inFile.close();

        FileWriter outFile = new FileWriter(outName);
        String file = fileContent.toString();
        file = file.replaceAll("\r\n", " ");
        file = file.trim();
        String[] words = file.split(" ");
        for (String word : words) {
            if (word.matches(".*[0-9].*")) outFile.write(word + " ");
        }
        outFile.close();
    }
    */
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] as = line.trim().split(" ");
            for (String s : as) {
                if (s.matches(".*[0-9].*")) writer.write(s + " ");
            }
        }
        reader.close();
        writer.close();
    }
}
