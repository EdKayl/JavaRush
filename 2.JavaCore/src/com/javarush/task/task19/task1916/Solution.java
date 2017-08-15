package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedReader file1 = new BufferedReader(new FileReader(fileName1));
        BufferedReader file2 = new BufferedReader(new FileReader(fileName2));

       ArrayList<String> file1Lines = new ArrayList<>();
       ArrayList<String> file2Lines = new ArrayList<>();
        while(file1.ready()) {
            file1Lines.add(file1.readLine());

        }
        while(file2.ready()) {
            file2Lines.add(file2.readLine());
        }

        for(int i = 0; i < file1Lines.size(); i++) {
            if(i > file2Lines.size()) {
                lines.add(new LineItem(Type.REMOVED, file1Lines.get(i)));
            }
            if(i < file2Lines.size()) {
                if(file1Lines.get(i).equals(file2Lines.get(i))) {
                    lines.add(new LineItem(Type.SAME, file1Lines.get(i)));
                }else {
                    if(i == file2Lines.size()) {
                        lines.add(new LineItem(Type.ADDED, file2Lines.get(i)));
                        file2Lines.remove(i);
                    } else {
                        if (!(file1Lines.get(i).equals(file2Lines.get(i + 1)))) {
                            lines.add(new LineItem(Type.REMOVED, file1Lines.get(i)));
                            file2Lines.add(i, "");
                        } else {
                            lines.add(new LineItem(Type.ADDED, file2Lines.get(i)));
                            file2Lines.remove(i);
                            i--;
                        }
                    }
                }
            }
        }
        for(LineItem item : lines) {
            System.out.println(item.type + " " + item.line);
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
