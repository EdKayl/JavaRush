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

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader reader2 = new BufferedReader(new FileReader(reader.readLine()));
//        Сделать ввод с консоли
        collate(reader1, reader2);
        reader.close();
        reader1.close();
        reader2.close();
    }

    private static void collate(BufferedReader reader1, BufferedReader reader2) throws IOException {
        //Работает по принципу стека, срезая строку, тип которой мы уже определили,
        //т.е. если строки SAME - удаляются и обоих массивов
        //Если строка ADDED - удаляется из второго массива, и тогда второй массив окажется либо пустым,
        //либо следующие строки 100% будут SAME (Условие: Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME)
        //По аналогии, если строка REMOVED - удаляется из первого массива, и тогда первый массив окажется либо пустым,
        //либо следующие строки 100% будут SAME (Условие: Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME)
        int maxLength;
        List<String> lines1 = new ArrayList<>();
        List<String> lines2 = new ArrayList<>();
        while (reader1.ready()) lines1.add(reader1.readLine());
        while (reader2.ready()) lines2.add(reader2.readLine());
        String s1 = "", s2 = "";
        while((maxLength = (lines1.size() > lines2.size()) ? lines1.size() : lines2.size()) != 0){
            // Проверки, чтобы не пытаться получить значение из пустого массива
            if(lines1.size() != 0)
                s1 = lines1.get(0);
            if(lines2.size() != 0)
                s2 = lines2.get(0);
            //Если в первый массив пуст, то очевидно, что во втором массиве осталась лишь одна строка и она ADDED
            if(lines1.size() == 0){
                lines.add(new LineItem(Type.ADDED, s2));
                lines2.remove(0);
            }
            //Аналогично если второй массив пуст, то в первом осталась лишь однда строка и она REMOVED
            else if(lines2.size() == 0){
                lines.add(new LineItem(Type.REMOVED, s1));
                lines1.remove(0);
            }
            // Если строки одинаковы, то удаляем по одной строке из каждого массива
            else if(s1.equals(s2)) {
                lines.add(new LineItem(Type.SAME, s1));
                lines1.remove(0);
                lines2.remove(0);
            }
            //Если текущие строки не равны, то проверяем:
            //Если строка из первого массива равна последующей строке из второго
            //Вылет за границы при проверке lines2.get(1) учтен в первых двух if'ах
            else if(s1.equals(lines2.get(1))){
                lines.add(new LineItem(Type.ADDED, s2));
                lines2.remove(0);
            }
            //Если строка из второго массива равна последующей строке из первого
            //Вылет за границы при проверке lines1.get(1) учтен в первых двух if'ах
            else if(lines1.get(1).equals(s2)){
                lines.add(new LineItem(Type.REMOVED, s1));
                lines1.remove(0);
            }
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
