package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        Random rand = new Random();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean haveDigit = false, haveUpperCase = false, haveLowerCase = false;
        byte[] buffer = new byte[8];
        int i = 0;
        while(i < 8) {
            int symbol = rand.nextInt(75) + 47;
            if(!Character.isLetterOrDigit(symbol)) {
                continue;
            }
            if(Character.isUpperCase(symbol)) haveUpperCase = true;
            if(Character.isLowerCase(symbol)) haveLowerCase = true;
            if(Character.isDigit(symbol)) haveDigit = true;

            buffer[i] = (byte) symbol;
            i++;

            if((i > 5) && !(haveDigit & haveLowerCase & haveUpperCase)) {
                i = i - 2;
            }
        }
        try{
            baos.write(buffer);
        } catch (IOException e) {

        }
        return baos;
    }
}