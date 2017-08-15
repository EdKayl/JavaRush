package com.javarush.task.task31.task3111;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();
    public List<Path> getFoundFiles() {
        return foundFiles;
    }



    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean addCurFiles = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) {
                addCurFiles = false;
        }

        if(partOfContent != null) {
            String content = new String(Files.readAllBytes(file));
            if(!content.contains(partOfContent)) {
                addCurFiles = false;
            }
        }

        if(minSize > 0 && !(Files.size(file) > minSize)) {
            addCurFiles = false;
        }

        if(maxSize > 0 && !(Files.size(file) < maxSize)) {
            addCurFiles = false;
        }

        if(addCurFiles) foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }
}
