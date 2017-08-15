package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        if(n == 1) return;
        int delim = 2;
        while(true) {
            if((n % delim) == 0) {
                System.out.print(delim + " ");
                recursion(n / delim);
                break;
            }
            delim++;
        }
    }
}
