package com.javarush.task.task20.task2025;

import java.util.ArrayList;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;
        ArrayList<Long> res = new ArrayList<>();
        for(int i = 10; i < N; i++) {
            String stringNum = String.valueOf(i);
            int powerOfNumber = stringNum.length();
            long sumOfNum = 0;
            for(int j = 0; j < stringNum.length(); j++) {
                sumOfNum += Math.pow(Integer.parseInt(stringNum.substring(j, j+1)), powerOfNumber);
            }
            if( i == sumOfNum) res.add(sumOfNum);
        }
        result = new long[res.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        long[] numbers = getNumbers(20000000);
        for(long num : numbers) {
            System.out.println(num);
        }
    }
}
