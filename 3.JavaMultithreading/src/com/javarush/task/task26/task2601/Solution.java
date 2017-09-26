package com.javarush.task.task26.task2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array = new Integer[5];
        array[0] = 13;
        array[1] = 8;
        array[2] = 15;
        array[3] = 5;
        array[4] = 17;
        array = sort(array);

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);

        int tmp;
        if(array.length % 2 != 0) {
            tmp = array[array.length / 2];
        } else {
            tmp = (array[array.length / 2  - 1 ] + array[array.length / 2]) / 2 ;
        }

        final int median = tmp;
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(array));
        Collections.sort(result, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer distance1 = Math.abs(o1 - median);
                Integer distance2 = Math.abs(o2 - median);
                return distance1.compareTo(distance2);
            }
        });

        for(int i = 0; i < array.length; i++) {
            array[i] = result.get(i);
        }
        return array;
    }
}
