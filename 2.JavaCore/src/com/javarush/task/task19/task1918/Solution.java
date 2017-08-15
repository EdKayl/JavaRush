package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        String fileName = reader.readLine();
        reader.close();

        //String fileName = "d:/5.txt";
        FileReader file = new FileReader(fileName);
        StringBuffer fileContent = new StringBuffer();
        while(file.ready()) {
            int c = file.read();
            if(c > 30) {
                fileContent.append((char) c);
            }
        }
        file.close();

        int[] startPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int[] endPos = new int[10];
        int index = 0;
        int currPosition = 0;
        String tag = args[0];
        //String tag = "span";
        String content = fileContent.toString();
        while(true) {
            int pos = content.indexOf(tag, currPosition);
            if(pos == -1) break;
            if(content.substring(pos-1, pos).equals("<")) {
                startPos[index] = pos - 1;
                endPos[index] = -1;
                index++;
            }
            if(content.substring(pos-1, pos).equals("/")) {
                for(int j = endPos.length - 1; j >= 0; j--) {
                    if(endPos[j] == -1) {
                        endPos[j] = pos + tag.length() + 1;
                        break;
                    }
                }
            }
            currPosition = pos + 1;
        }
        //
        for(int i = 0; i < startPos.length; i++) {
            if(startPos[i] == -1) break;
            System.out.println(content.substring(startPos[i], endPos[i]));
        }
    }
}
