package com.javarush.task.task06.task0606;

import java.io.*;
import java.nio.Buffer;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        reader.close();

        int currNumber = 0;
        int raz = 10;
        int num = number;
        do {
            currNumber = number % 10;
            number = number / 10;
            if(currNumber % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        } while (number != 0);
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
