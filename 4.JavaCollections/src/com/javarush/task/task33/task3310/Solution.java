package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;
import com.javarush.task.task33.task3310.tests.FunctionalTest;
import com.javarush.task.task33.task3310.tests.SpeedTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 01.09.2017.
 */
public class Solution {
    public static void main(String[] args) {
        //StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        //Solution.testStrategy(strategy, 10000);.
        /*
        FunctionalTest test = new FunctionalTest();
        test.testDualHashBidiMapStorageStrategy();
        test.testFileStorageStrategy();
        test.testHashBiMapStorageStrategy();
        test.testHashMapStorageStrategy();
        test.testOurHashBiMapStorageStrategy();
        test.testOurHashMapStorageStrategy();
        */
        SpeedTest speedTest = new SpeedTest();
        speedTest.testHashMapStorage();
    }

    // для переданного множества строк возвращает множество идентификаторов
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for(String string : strings) {
            result.add(shortener.getId(string));
        }
        return result;
    }

    //  возвращать множество строк, которое соответствует переданному множеству идентификаторов
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        int i = 0;
        for(Long key : keys) {
            result.add(shortener.getString(key));
            //System.out.println(i++);
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        System.out.println(strategy.getClass().getSimpleName());

        Set<String> stringSet = new HashSet<>();

        for(int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date startDate = new Date();
        Set<Long> ids = Solution.getIds(shortener, stringSet);
        Date stopDate = new Date();
        System.out.println(stopDate.getTime() - startDate.getTime());

        startDate = new Date();
        Set<String> strings = Solution.getStrings(shortener, ids);
        stopDate = new Date();
        System.out.println(stopDate.getTime() - startDate.getTime());

        if(strings.equals(stringSet)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
