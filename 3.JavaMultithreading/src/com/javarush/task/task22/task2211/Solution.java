package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        //FileInputStream inputStream = new FileInputStream("d:/3.txt");
        //FileOutputStream outputStream = new FileOutputStream("d:/4.txt");
        FileInputStream inputStream = new FileInputStream(args[0]);
        FileOutputStream outputStream = new FileOutputStream(args[1]);
        byte[] buffer = new byte[1024];
        int i;
        Charset windows = Charset.forName("Windows-1251");
        Charset utf8 = Charset.forName("UTF-8");
        while ((i = inputStream.read(buffer)) > 0) {
            String s = new String(buffer, 0, i, utf8);
            buffer = s.getBytes(windows);
            outputStream.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }
}
