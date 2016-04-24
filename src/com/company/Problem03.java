package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigInteger UltimateCriticalRatio = new BigInteger("0");
        boolean fail = false;
        List<String> lines = new ArrayList<>();

        String line;
        while (!"Break it.".equals(line = sc.nextLine())) {
            //get the input
            String[] items = line.split(" ");
            BigInteger[] numbers = new BigInteger[items.length];
            for (int i = 0; i < items.length; i++) {
                numbers[i] = new BigInteger(items[i]);

            }

            BigInteger x1 = numbers[0];
            BigInteger y1 = numbers[1];
            BigInteger x2 = numbers[2];
            BigInteger y2 = numbers[3];

            //create the line for the output - if needed
            lines.add("[" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + "]");

            //calculate the current critical ratio
            BigInteger currentCriticalRation = x2.add(y2).subtract(x1.add(y1));
            currentCriticalRation = currentCriticalRation.abs();

            //assign the current critical ratio to the ultimate critical ratio - if the condition is met
            if (UltimateCriticalRatio.compareTo(BigInteger.ZERO) == 0) {
                UltimateCriticalRatio = currentCriticalRation;
            }

            //check if there's a critical breakpoint
            if (currentCriticalRation.compareTo(BigInteger.ZERO) != 0) {
                if (currentCriticalRation.compareTo(UltimateCriticalRatio) != 0) {
                    System.out.println("Critical breakpoint does not exist.");
                    fail = true;
                    break;
                }
            }
        }

        //print the output
        if (!fail) {
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Line: " + lines.get(i));
            }
            System.out.println("Critical Breakpoint: " + UltimateCriticalRatio.pow(lines.size()).remainder(BigInteger.valueOf(lines.size())));
        }
    }

}
