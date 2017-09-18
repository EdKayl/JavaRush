package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++) {
            strings.add(reader.readLine());
        }
        reader.close();

        int indexTall = 0;
        int indexSmall = 0;

        for(int i = 0; i < strings.size() - 1; i++) {
            if(strings.get(indexTall).length() < strings.get(i).length()) {
                indexTall = i;
            }
            if(strings.get(indexSmall).length() > strings.get(i).length()) {
                indexSmall = i;
            }
        }

        if(indexSmall < indexTall) {
            System.out.println(strings.get(indexSmall));
        } else {
            System.out.println(strings.get(indexTall));
        }
    }
}
