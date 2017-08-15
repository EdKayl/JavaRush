package com.javarush.task.task31.task3105;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipName = args[1];
        String name = fileName.substring(fileName.lastIndexOf("/") + 1);

        FileInputStream fin = new FileInputStream(zipName);
        ZipInputStream zis = new ZipInputStream(fin);
        Map<ZipEntry, ByteArrayOutputStream> entryContent = new HashMap<>();
        byte[] buffer = new byte[1024];
        while(zis.available() > 0) {
            ZipEntry zipEntry = zis.getNextEntry();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            while ((len = zis.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            entryContent.put(zipEntry, byteArrayOutputStream);
        }
        zis.close();
        fin.close();


        FileInputStream fileForArchive = new FileInputStream(fileName);
        FileOutputStream zipFile = new FileOutputStream(zipName);
        ZipOutputStream zos = new ZipOutputStream(zipFile);
        boolean exist = false;
        for(Map.Entry<ZipEntry, ByteArrayOutputStream> entry : entryContent.entrySet()) {
            String zipEntryName =  entry.getKey().getName();
            zipEntryName = zipEntryName.substring(zipEntryName.lastIndexOf("/") + 1);

            if(zipEntryName.equals(name)) {
                zos.putNextEntry(new ZipEntry(entry.getKey().getName()));
                int len = 0;
                while((len = fileForArchive.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                exist = true;
            } else {
                zos.putNextEntry(entry.getKey());
                entry.getValue().writeTo(zos);
            }
        }
        if(!exist) {
            zos.putNextEntry(new ZipEntry("new/" + name));
            int len = 0;
             while ((len = fileForArchive.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
             }
        }
        zos.close();
        zipFile.close();
        fileForArchive.close();
    }
}
