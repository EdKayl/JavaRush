package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(this.fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
        Solution solution = new Solution("d:/12.txt");
        solution.writeObject("test solution");
        FileOutputStream fos = new FileOutputStream("d:/14.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(solution);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/14.txt"));
        Solution s2 = (Solution) ois.readObject();
        s2.writeObject("test s2");
    }
}
