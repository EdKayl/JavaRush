package com.javarush.task.task22.task2202;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if(string ==  null || string.isEmpty() || string.length() < 5) throw new TooShortStringException();

        int start = 0;
        int end = 0;
        int pos = 0;
        for(int i = 0; i < 5; i++) {
            pos = string.indexOf(" ", pos + 1);
            if(pos == -1)  {
                if(i == 4) {
                    end = string.length();
                    break;
                } else {
                    throw new TooShortStringException();
                }
            }
            if(i == 0) start = pos + 1;
            if(i == 4) end = pos;
        }
        return string.substring(start, end);
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
