package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Path path = Paths.get(fileName).toAbsolutePath();

        Properties properties = new Properties();
        try {
            if(fileName.endsWith(".xml")) {
                properties.loadFromXML(new FileInputStream(path.toFile()));
            } else {
                properties.load(new FileReader(path.toFile()));
            }
        } catch (IOException e) {
            properties = new Properties();
        }
        return properties;
    }
}
