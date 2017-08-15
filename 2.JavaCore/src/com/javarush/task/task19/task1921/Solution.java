package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException{
        String fileName = args[0];

        BufferedReader file = new BufferedReader(new FileReader(fileName));
        ArrayList<String> fileContent = new ArrayList<>();
        while(file.ready())  fileContent.add(file.readLine());
        file.close();

        for(String str : fileContent) {
            String[] data = str.split(" ");
            String name = "";
            int day = Integer.parseInt(data[data.length - 3]);
            int month = Integer.parseInt(data[data.length - 2]) - 1;
            int year = Integer.parseInt(data[data.length - 1]);

            for (int i = 0; i < data.length - 3; i++) {
                name += data[i] + " ";
            }
            PEOPLE.add(new Person(name.trim(), new GregorianCalendar(year, month, day).getTime()));
        }
    }
}
