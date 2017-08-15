package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution {
    private static ArrayList<String> files = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        scanFolder(new File(path));
        Collections.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String f1 = o1.substring(o1.lastIndexOf("\\"));
                String f2 = o2.substring(o2.lastIndexOf("\\"));
                return f1.compareTo(f2);
            }
        });

        File resultFile = new File(resultFileAbsolutePath);
        File newFile = new File("allFilesContent.txt");
        FileUtils.renameFile(resultFile, newFile);
        FileWriter fileWriter = new FileWriter(newFile);

        for(String fileName : files) {
            FileReader source = new FileReader(fileName);
            while (source.ready()) {
                fileWriter.write(source.read());
            }
            fileWriter.write("\r\n");
            source.close();
        }
        fileWriter.close();
    }

    public static void scanFolder(File file) throws IOException{
       for(File f : file.listFiles()) {
           if(f.isDirectory()) {
               scanFolder(f);
               continue;
           }
           if(f.length() > 50) {
               FileUtils.deleteFile(f);
           } else {
               files.add(f.getAbsolutePath());
           }
       }
    }
    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
