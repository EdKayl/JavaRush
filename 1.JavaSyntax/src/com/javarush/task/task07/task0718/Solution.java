package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++) {
            strings.add(reader.readLine());
        }
        reader.close();

        for(int i = 0; i < strings.size() - 1; i++) {
            if(strings.get(i).length() > strings.get(i + 1).length()) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}

