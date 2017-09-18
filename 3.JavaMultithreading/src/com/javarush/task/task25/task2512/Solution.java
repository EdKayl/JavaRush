package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(t != null) t.interrupt();
        Throwable cause = e.getCause();
        if(cause != null) uncaughtException(t, e.getCause());
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }

    public static void main(String[] args) {
        Thread testThread = new Solution.TestThread();
        testThread.setUncaughtExceptionHandler(new Solution());
        testThread.start();

    }

    public static class TestThread extends Thread {
        @Override
        public void run() {
            int i = 10 / 0;
        }
        private void sendException() throws Exception{
            throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
        }
    }
}
