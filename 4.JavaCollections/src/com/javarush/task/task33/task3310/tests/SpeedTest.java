package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTime = new Date();
        for(String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTime = new Date();
        for(Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }

    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for(int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();

        long time1 = getTimeForGettingIds(shortener1, origStrings, ids);
        ids.clear();
        long time2 = getTimeForGettingIds(shortener2, origStrings, ids);

        Assert.assertNotEquals(time1, time2);
        origStrings.clear();
        time1 = getTimeForGettingStrings(shortener1, ids, origStrings);
        origStrings.clear();
        time2 = getTimeForGettingStrings(shortener2, ids, origStrings);

        Assert.assertEquals(time1, time2, 30);

    }
}
