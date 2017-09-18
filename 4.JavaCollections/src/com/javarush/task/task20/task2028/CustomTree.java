package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList implements Cloneable, Serializable {
    Entry<String> root;

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        //System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        //System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null)
                availableToAddLeftChildren = false;
            if (rightChild != null)
                availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    public String get(int index) {
        throw new UnsupportedOperationException();
        //return null;
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
        //return super.addAll(index, c);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
        //return super.subList(fromIndex, toIndex);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
        //super.removeRange(fromIndex, toIndex);
    }

    public boolean add(String s) {
        if (root == null) {
            root = new Entry<>(s);
            return false;
        }
        Queue<Entry<String>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        boolean isAdded = false;
        while (!isAdded && !queue.isEmpty()) {
            Entry<String> currentEntry = queue.remove();
            if (!currentEntry.availableToAddLeftChildren && currentEntry.leftChild!=null) {
                queue.add(currentEntry.leftChild);
            } else {
                if (!isAdded && currentEntry.availableToAddLeftChildren) {
                    Entry<String> newEntry = new Entry<String>(s);
                    newEntry.parent = currentEntry;
                    currentEntry.leftChild = newEntry;
                    isAdded = true;
                    currentEntry.checkChildren();
                }
            }
            if (!currentEntry.availableToAddRightChildren && currentEntry.rightChild!=null) {
                queue.add(currentEntry.rightChild);
            } else {
                if (!isAdded && currentEntry.availableToAddRightChildren) {
                    Entry<String> newEntry = new Entry<String>(s);
                    newEntry.parent = currentEntry;
                    currentEntry.rightChild = newEntry;
                    isAdded = true;
                    currentEntry.checkChildren();
                }
            }
        }
        return false;
    }

    public void remove(String s) {
        if (root == null) {
            return;
        }
        Queue<Entry<String>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        boolean isRemoved = false;
        while (!isRemoved && !queue.isEmpty()) {
            Entry<String> currentEntry = queue.remove();
            if (!currentEntry.leftChild.elementName.equals(s)) {
                queue.add(currentEntry.leftChild);
            } else {
                if (!isRemoved) {
                    currentEntry.leftChild = null;
                    isRemoved = true;
                }
            }
            if (!currentEntry.rightChild.elementName.equals(s)) {
                queue.add(currentEntry.rightChild);
            } else {
                if (!isRemoved) {
                    currentEntry.rightChild = null;
                    isRemoved = true;
                }
            }
        }
    }

    @Override
    public int size() {
        Entry<String> rootElement;
        Queue<Entry<String>> queue = new LinkedList<>();
        if (root.leftChild != null) queue.offer(root.leftChild);
        if (root.leftChild != null) queue.offer(root.rightChild);
        int count = 0;
        while (!queue.isEmpty()) {
            //берём элемент из очереди, считаем его, потом добавляем в очередь существующих потомков, и всё по новой
        }
        return count;
    }

    public String getParent(String s) {
        if (root == null) {
            return null;
        }
        Queue<Entry<String>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        String parentName = null;
        while (parentName == null && !queue.isEmpty()) {
            Entry<String> currentEntry = queue.remove();
            if (currentEntry.leftChild != null ) {
                if (!currentEntry.leftChild.elementName.equals(s)) {
                    queue.add(currentEntry.leftChild);
                } else {
                    parentName = currentEntry.elementName;
                }
            }
            if (currentEntry.rightChild != null) {
                if (!currentEntry.rightChild.elementName.equals(s)) {
                    queue.add(currentEntry.rightChild);
                } else {
                    parentName = currentEntry.elementName;
                }
            }
        }
        return parentName;
    }
}