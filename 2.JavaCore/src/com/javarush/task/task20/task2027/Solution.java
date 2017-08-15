package com.javarush.task.task20.task2027;

import java.util.LinkedList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same");
        for (Word word:list){
            System.out.println(word);
        }

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> foundWords = new LinkedList<>();

        for(String word : words) {
            byte[] symbol = word.getBytes();
            for(int i = 0; i < crossword[0].length; i++) {
                for(int j = 0; j < crossword.length; j++) {
                    if(crossword[j][i] == symbol[0]) {
                        Word newWord = new Word(word);
                        newWord.setStartPoint(i, j);
                        if(searchAround(crossword, word, newWord, i, j)) {
                            foundWords.add(newWord);
                        }
                    }
                }
            }
        }
        return foundWords;
    }
    private static boolean searchAround(int[][] crossword, String word, Word searchWord, int i, int j) {
        int[] moveX = {0,1,1,1,0,-1,-1,-1};
        int[] moveY = {-1,-1,0,1,1,1,0,-1};
        byte[] symbols = word.getBytes();
        for(int k = 0; k < 8; k++) {
            int x = i + moveX[k];
            int y = j + moveY[k];
            try{
                if(crossword[y][x] == symbols[1]) {
                    if (searchEnd(crossword, word, searchWord, x, y, k)) {
                        return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        return false;
    }
    private static boolean searchEnd(int[][] crossword, String word, Word searchWord, int i, int j, int currDirection) {
        int[] moveX = {0,1,1,1,0,-1,-1,-1};
        int[] moveY = {-1,-1,0,1,1,1,0,-1};
        byte[] symbols = word.getBytes();
        int x = i, y = j;
        for(int l = 2; l < symbols.length; l++) {
                x = x + moveX[currDirection];
                y = y + moveY[currDirection];
                try {
                    if(crossword[y][x] != symbols[l]) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    return  false;
                }
        }
        searchWord.setEndPoint(x, y);
        return true;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
