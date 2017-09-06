package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;

/**
 * Created by user on 01.09.2017.
 */
public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        Long k1 = getKey();
        Long k2 = ((Entry) o).getKey();
        String value1 = getValue();
        String value2 = ((Entry) o).getValue();

        if ((k1 == k2) && value1.equals(value2)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public String toString() {
        return new String(key + "=" + value);
    }
}
