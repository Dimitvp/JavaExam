package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine();
        int[][] matrix = new int[rows][cols];
        int count = 0;

        //fill the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                count++;
                matrix[i][j] = count;
            }
        }

        String line;
        while (!"Nuke it from orbit".equals(line = sc.nextLine())) {
            //get the input
            String[] items = line.split(" ");
            int row = Integer.parseInt(items[0]);
            int col = Integer.parseInt(items[1]);
            int radius = Integer.parseInt(items[2]);

            //destroy horizontally
            for (int i = col - radius; i <= col + radius; i++) {
                if (row >= 0 && row < matrix.length && i < matrix[row].length && i >= 0) {
                    matrix[row][i] = 0;
                }
            }

            //destroy vertically
            for (int i = row - radius; i <= row + radius; i++) {
                if (i >= 0 && i < matrix.length && col >= 0 && col < matrix[i].length) {
                    matrix[i][col] = 0;
                }
            }

            //BUILD A NEW MATRIX

            //count the rows
            int rowss = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (Arrays.stream(matrix[i]).sum() != 0) {
                    rowss++;
                }
            }

            //create and fill the new matrix
            int[][] newMatrix = new int[rowss][];
            int currentRow = 0;

            for (int i = 0; i < matrix.length; i++) {
                List<Integer> whatIsLeft = new ArrayList<>();
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] != 0)
                        whatIsLeft.add(matrix[i][j]);
                }
                if (whatIsLeft.size() > 0) {
                    int[] numbersInt = new int[whatIsLeft.size()];
                    for (int j = 0; j < whatIsLeft.size(); j++) {
                        numbersInt[j] = whatIsLeft.get(j);
                    }
                    newMatrix[currentRow] = numbersInt;
                    currentRow++;
                }
            }
            matrix = newMatrix;
        }

        //print the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
