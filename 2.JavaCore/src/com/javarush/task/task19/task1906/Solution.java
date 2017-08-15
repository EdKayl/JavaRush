package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;

public class Solution {
    private int[][] matrix;
    private int height = 6;
    private int width = 5;

    public static void main(String[] args) throws IOException{
        Solution solution = new Solution();
        solution.matrix = new int[][] {{0,0,0,0,0},{0,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1},{1,1,0,0,1},{1,1,1,1,1}};
        solution.removeFullLines();
        System.out.printf("   ");

    }
    public void removeFullLines() {
        //Например так:
        //Создаем список для хранения линий
        //Копируем все непустые линии в список.
        //Добавляем недостающие строки в начало списка.
        //Преобразуем список обратно в матрицу
        int[][] temp = new int[height][width];
        int curTempArr = height - 1; //текущая строка во временном массиве, идем снизу матрицы
        for(int y = height - 1; y >= 0; y--) {
            int sumOfLine = 0;
            for(int x = 0; x < width; x++) {
                sumOfLine += matrix[y][x];
            }
            //ну пустая и не полностью заполнена - копируем строку во временный массив
            if(sumOfLine != width && sumOfLine != 0 ) {
                temp[curTempArr] = matrix[y];
                curTempArr--;
            }
        }
        //копируем обратно в matrix из временного массива, начинаем снизу
        for(int y = height - 1; y > curTempArr; y--) {
            matrix[y] = temp[y];
        }
        //добавляем новые строки, сразу заполняя их 0
        for(int y = curTempArr; y >= 0; y--) {
            matrix[y] = new int[width];
        }
    }
}
