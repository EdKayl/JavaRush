package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File fileRoot = new File(root);
        List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, fileRoot.listFiles());

        while (!fileTree.isEmpty()) {
            File currFile = fileTree.remove();
            if(currFile.isDirectory()) {
                Collections.addAll(fileTree, currFile.listFiles());
            } else {
                result.add(currFile.getAbsolutePath());
            }
        }
        return result;

    }
}
