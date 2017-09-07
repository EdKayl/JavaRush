package com.javarush.task.task24.task2411;

import com.javarush.task.task21.task2109.Solution;

public class C implements JustAnInterface {
    public C() {
        System.out.print("C");
        B localB = B;
    }
}