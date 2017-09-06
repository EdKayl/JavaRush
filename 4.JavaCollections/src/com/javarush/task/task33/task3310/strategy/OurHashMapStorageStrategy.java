package com.javarush.task.task33.task3310.strategy;

/**
 * Created by user on 01.09.2017.
 */
public class OurHashMapStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;


    int hash(Long k) {
        return k.hashCode();
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        if(size == 0) return null;

        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for(Entry e = table[index]; e != null; e = e.next) {
            if(e.hash == hash && e.key == key) {
                return e;
            }
        }
        return null;
    }

    void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (loadFactor * newCapacity);
    }

    void transfer(Entry[] newTable) {
        for(Entry e : table) {
            while(e != null) {
                Entry next = e.next;
                int index = indexFor(hash(e.key), newTable.length);
                newTable[index] = e;
                e.next = null;
                e = next;
            }
        }

    }
    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if((size  >= threshold) && table[bucketIndex] != null) {
            int newCapacity = table.length * 2;
            resize(newCapacity);
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }
    void createEntry(int hash, Long key, String value, int bucketIndex) {
        // memory current first entry in bucket or null
        Entry e = table[bucketIndex];
        // insert new entry in begin linked list (make it first entry)
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;

    }

    @Override
    public boolean containsKey(Long key) {
        if(key == null) return false;
        if(getEntry(key) != null) return true;
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        if(getKey(value) != null) return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if(key == null) return;
        int hash = hash(key);
        int bucket = indexFor(hash, table.length);
        for(Entry e = table[bucket]; e != null; e = e.next) {
            if((e.hash == hash) && (e.key == key)) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, bucket);
    }

    @Override
    public Long getKey(String value) {
        for(Entry bucket : table) {
            for(Entry entry = bucket; entry != null ; entry = entry.next) {
                if(entry.getValue().equals(value)) return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {

        return getEntry(key).getValue();
    }
}
