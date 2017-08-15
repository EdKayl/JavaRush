package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.TreeSet;
import java.util.zip.ZipInputStream;


/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        String resultFileName = args[0];

        TreeSet<String> fileNamePart = new TreeSet<>();
        for(int i = 1; i < args.length; i++) {
            fileNamePart.add(args[i]);
        }

        ByteArrayOutputStream archiveBytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for(String name : fileNamePart) {
            FileInputStream fis = new FileInputStream(name);
            int len = 0;
            while((len = fis.read(buffer)) > 0) {
                archiveBytes.write(buffer, 0, len);
            }
            fis.close();
        }

        FileOutputStream fos = new FileOutputStream(resultFileName);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(archiveBytes.toByteArray());
        ZipInputStream zis = new ZipInputStream(byteArrayInputStream);
        zis.getNextEntry();
        buffer = new byte[1024];
        int len = 0;
        while((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0 , len);
        }
        zis.close();
        fos.close();
    }
}
