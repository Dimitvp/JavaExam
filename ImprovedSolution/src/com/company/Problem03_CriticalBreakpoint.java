package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem03_CriticalBreakpoint {
    public static void main(String[] args) {

        //Getting the input
        Scanner sc = new Scanner(System.in);
        String line;

        //lines will contain all rows of input in the format [1, 2, 3, 4]
        List<String> lines = new ArrayList<>();
        long absoluteCriticalRatio = 0;

        //Calculate critical ratio of every line
        while (!"Break it.".equals(line = sc.nextLine())) {
            int[] numbers = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            int x1 = numbers[0];
            int y1 = numbers[1];
            int x2 = numbers[2];
            int y2 = numbers[3];

            lines.add(Arrays.toString(numbers));

            long currentCriticalRatio = Math.abs(((long) x2 + (long) y2) - ((long) x1 + (long) y1));

            //If you find a non-zero critical ratio value it becomes the actual critical ratio
            if (absoluteCriticalRatio == 0 && currentCriticalRatio != 0) {
                absoluteCriticalRatio = currentCriticalRatio;
            }

            //if there is even one line, which’s critical ratio does not equal to the actual critical ratio,
            // and is not equal to zero, the current lines fail to create a critical breakpoint.
            //Then you print “Critical breakpoint does not exist.”.
            if (currentCriticalRatio != 0 && currentCriticalRatio != absoluteCriticalRatio) {
                System.out.println("Critical breakpoint does not exist.");
                return;
            }
        }


        //If a critical breakpoint has been found you need to print each of the lines in the following format:
        //-> “Line: [x1, y1, x2, y2]”
        for (String linee : lines) {
            System.out.println("Line: " + linee);
        }

        //Calculate the Critical Break Point - If a critical breakpoint is formed you need to calculate it.
        // A critical breakpoint is equal to – the remainder of the division of, the critical ratio (which
        // in this case is one for all the lines) powered by the count of lines, and the count of lines.
        BigInteger criticalBreakPoint = BigInteger.valueOf(absoluteCriticalRatio)
                .pow(lines.size()).remainder(BigInteger.valueOf(lines.size()));

        //And after the lines, print the critical breakpoint
        System.out.println("Critical Breakpoint: " + criticalBreakPoint);


    }
}
