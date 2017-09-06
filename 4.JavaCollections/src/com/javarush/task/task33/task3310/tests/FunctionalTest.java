package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by user on 05.09.2017.
 */
public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String[] strings = new String[3];
        strings[0] = Helper.generateRandomString();
        strings[1] = Helper.generateRandomString();
        strings[2] = new String(strings[0]);

        Long[] ids = new Long[3];
        ids[0] = shortener.getId(strings[0]);
        ids[1] = shortener.getId(strings[1]);
        ids[2] = shortener.getId(strings[2]);

        Assert.assertNotEquals(ids[0], ids[1]);
        Assert.assertEquals(ids[0], ids[2]);

        String[] str = new String[3];
        str[0] = shortener.getString(ids[0]);
        str[1] = shortener.getString(ids[1]);
        str[2] = shortener.getString(ids[2]);

        Assert.assertArrayEquals(strings, str);
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}
