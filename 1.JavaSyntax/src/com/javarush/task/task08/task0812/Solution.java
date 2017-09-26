package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int max = 1, temp = 1;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(Integer.valueOf(reader.readLine()));
        }
        for (int j = 0; j < list.size() - 1; j++) {
            if (list.get(j) != (list.get(j + 1))) {
                temp = 1;
            } else
                temp++;
            if (temp > max)
                max = temp;
        }
        System.out.println(max);
    }
}