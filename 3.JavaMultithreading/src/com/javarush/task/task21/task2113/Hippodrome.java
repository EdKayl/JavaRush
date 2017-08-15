package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19.06.2017.
 */
public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException{
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Ada", 3, 0));
        horses.add(new Horse("Jone", 3, 0));
        horses.add(new Horse("Speedy", 3, 0));

        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException{
        for(int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }

    }
    public void move() {
        List<Horse> horses = getHorses();
        for(Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        List<Horse> horses = getHorses();
        for(Horse horse : horses) {
            horse.print();
        }
        for(int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }

    public Horse getWinner() {
        List<Horse> horses = this.getHorses();
        double winDistance = 0;
        Horse winner = horses.get(0);
        for(Horse horse : horses) {
            if(horse.getDistance() > winner.getDistance()) {
                winner = horse;
                }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
