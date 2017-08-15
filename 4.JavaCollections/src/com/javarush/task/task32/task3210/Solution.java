package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(number);
        int lenBuffer = text.length();
        byte[] buffer = new byte[lenBuffer];
        raf.read(buffer, 0, lenBuffer);
        raf.seek(raf.length());
        if(text.equals(new String(buffer))) {
            raf.write((new String("true").getBytes()));
        } else {
            raf.write((new String("false").getBytes()));
        }
        raf.close();
    }
}
