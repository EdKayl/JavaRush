package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable{
    public static void main(String[] args) {

        System.out.println(new Solution(4));
        try {

            File tmpFile = File.createTempFile("task2014", "");
            OutputStream os = new FileOutputStream(tmpFile);
            InputStream is = new FileInputStream(tmpFile);

            Solution savedObject = new Solution(10);
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(savedObject);

            Solution loadedObject = new Solution(8);
            ObjectInputStream inputStream = new ObjectInputStream(is);
            loadedObject = (Solution) inputStream.readObject();

            System.out.println(loadedObject.string.equals(savedObject.string));

            outputStream.close();
            inputStream.close();
            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
