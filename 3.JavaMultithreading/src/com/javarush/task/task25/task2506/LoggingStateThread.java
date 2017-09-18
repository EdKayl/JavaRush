package com.javarush.task.task25.task2506;

/**
 * Created by user on 14.09.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread thread;
    private State prevState;

    public LoggingStateThread(Thread target) {
        this.thread = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true){
            State state = thread.getState();
            if (prevState == null) {
                prevState = state;
                System.out.println(state.toString());
            } else if (!state.equals(prevState)) {
                System.out.println(state.toString());
                prevState = state;
            }
            if (state.equals(State.TERMINATED)) { break; }

        }
    }
}
