package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution extends SimpleFileVisitor<Path> {
    private int countDir = 0;
    private int countFiles = 0;
    private long allSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        reader.close();

        Path rootPath = Paths.get(path);

        if(!Files.isDirectory(rootPath)) {
            System.out.println(rootPath.toString() + " - не папка");
            return;
        }

        Solution solution = new Solution();
        Files.walkFileTree(rootPath, solution);
        System.out.println("Всего папок - " + (solution.countDir - 1));
        System.out.println("Всего файлов - " + solution.countFiles);
        System.out.println("Общий размер - " + solution.allSize);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            countFiles++;
            allSize += Files.size(file);
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        countDir++;
        return super.postVisitDirectory(dir, exc);
    }
}
