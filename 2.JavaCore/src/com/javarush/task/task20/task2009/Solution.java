package com.javarush.task.task20.task2009;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's test static string";
        public int i;
        public int j;

        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.write(staticString.getBytes());
            stream.write(i);
            stream.write(j);
        }
        private void readObject(ObjectInputStream stream) throws IOException {
            staticString = stream.readUTF();
            i = stream.readInt();
            j = stream.readInt();
        }
    }

    public static void main(String[] args) {

    }
}
