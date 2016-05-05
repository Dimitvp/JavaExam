package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Problem02_Crossfire {
    public static void main(String[] args) {
        //Getting Input
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine();

        //Create and fill the matrix
        int[][] matrix = new int[rows][cols];
        int count = 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = count++;
            }
        }

        //Destroying cells of the matrix until we get "Nuke it from orbit"
        String line;
        while (!"Nuke it from orbit".equals(line = sc.nextLine())) {

            //Getting the destruction coordinates
            int[] inputs = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int row = inputs[0];
            int col = inputs[1];
            int radius = inputs[2];

            //Destroy cells horizontally
            for (int currentCol = col - radius; currentCol <= col + radius; currentCol++) {
                if (row >= 0 && row < matrix.length && currentCol >= 0 && currentCol < matrix[row].length) {
                    matrix[row][currentCol] = 0;
                }
            }

            //Destroy cells vertically
            for (int currentRow = row - radius; currentRow <= row + radius; currentRow++) {
                if (currentRow >= 0 && currentRow < matrix.length && col >= 0 && col < matrix[currentRow].length) {
                    matrix[currentRow][col] = 0;
                }
            }

            //counting the non-empty rows
            int totalRows = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (Arrays.stream(matrix[i]).sum() != 0) {
                    totalRows++;
                }
            }

            //create the new matrix
            int[][] newMatrix = new int[totalRows][];
            int currentRowNewMatrix = 0;

            //fill the new matrix with the non-empty rows
            for (int currentRow = 0; currentRow < matrix.length; currentRow++) {
                if (Arrays.stream(matrix[currentRow]).sum() != 0) {
                    newMatrix[currentRowNewMatrix++] = Arrays.stream(matrix[currentRow]).filter(n -> n != 0).toArray();
                }
            }

            matrix = newMatrix;
        }

        //print matrix - what is left after the destruction of all cells
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
