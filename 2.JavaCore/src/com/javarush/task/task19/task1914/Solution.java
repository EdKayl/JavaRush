package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        testString.printSomething();
        String out = outputStream.toString();
        System.setOut(consoleStream);

        String[] operands = out.split(" ");
        int firstOperand = Integer.parseInt(operands[0]);
        int secondOperand = Integer.parseInt(operands[2]);
        int result = 0;
        switch (operands[1]) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
        }
        out = operands[0] + " " + operands[1] +" " + operands[2] +" " + operands[3] + " " +result;
        System.out.println(out);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

