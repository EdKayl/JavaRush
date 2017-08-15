package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String input;
        ArrayList<String> fileList = new ArrayList<String>();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
        while ((input = fileReader.readLine()) != null)
            fileList.add(input);
        fileReader.close();

        ArrayList<String> resultWords = new ArrayList<String>();
        for (String aFileList : fileList)
        {
            String[] stringArray = aFileList.split(" ");
            for (String aStringArray : stringArray)
            {
                if (aStringArray.length() > 6)
                    resultWords.add(aStringArray);
            }
        }

        String result = "";
        for (int i = 0; i < resultWords.size(); i++) {
            if (i == resultWords.size()-1)
                result = result + resultWords.get(i);
            else
                result = result + resultWords.get(i) + ",";
        }

        String fileName2 = args[1];
        BufferedWriter file2Writer = new BufferedWriter(new FileWriter(fileName2));
        file2Writer.write(result);
        file2Writer.close();

    }
}
