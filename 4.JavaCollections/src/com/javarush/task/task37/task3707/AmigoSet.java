package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by user on 13.08.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private transient HashMap<E,Object> map;
    private static final Object PRESENT = new Object();

    public AmigoSet() {
        this.map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int)(collection.size()/0.75f +1));
        map = new HashMap<E, Object>(capacity);
        this.addAll(collection);
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator iterator() {

        return map.keySet().iterator();
    }

    @Override
    public int size() {

        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet clone = (AmigoSet) super.clone();
            clone.map = (HashMap) map.clone();
            return clone;
        } catch (Exception e) {
            throw new InternalError();
        }
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeInt(map.size());
        for(E element : map.keySet()) {
            out.writeObject(element);
        }
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        float loadFactor = in.readFloat();
        int capacity = in.readInt();
        int size = in.readInt();
        map = new HashMap(capacity, loadFactor);
        for(int i = 0; i < size; i++) {
            add((E) in.readObject());
        }
    }
}
