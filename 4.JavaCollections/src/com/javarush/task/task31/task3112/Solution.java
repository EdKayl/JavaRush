package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        InputStream stream = url.openStream();
        Path tmpFile = Files.createTempFile("temp-", ".tmp");
        Files.copy(stream, tmpFile);

        String nameResult = downloadDirectory + "\\" + urlString.substring(urlString.lastIndexOf("/")+1);
        Path resultFile = Files.createFile(Paths.get(nameResult));
        Files.move(tmpFile, resultFile, StandardCopyOption.REPLACE_EXISTING);
        return resultFile;
    }
}
