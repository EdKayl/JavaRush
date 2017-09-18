package com.javarush.task.task25.task2510;

import com.sun.jmx.snmp.EnumRowStatus;

/*
Поживем - увидим
*/
public class Solution extends Thread {

    public Solution() {
        this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if(e instanceof Error) {
                    System.out.println("Нельзя дальше работать");
                } else if (e instanceof Exception) {
                    System.out.println("Надо обработать");
                } else if (e instanceof Throwable) {
                    System.out.println("ХЗ");
                }
            }
        });
    }

    public static void main(String[] args) {
    }
}
