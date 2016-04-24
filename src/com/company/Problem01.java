package com.company;

import java.util.Scanner;

public class Problem01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String patt = sc.nextLine();

        //if both lines are empty
        if (text.length() < 1 || patt.length() < 1) {
            System.out.println("No shake.");
            return;
        }

        while (patt.length() > 0 && text.length() > 0) {

            //find two instances
            int indexFirst = text.indexOf(patt);
            int indexLast = text.lastIndexOf(patt);

            //only if there are at least two instances continue
            if ((indexFirst != -1 && indexLast != -1) && indexFirst != indexLast) {

                //remove both instances
                StringBuilder sb = new StringBuilder();
                sb.append(text.substring(0, indexFirst));
                sb.append(text.substring(indexFirst + patt.length(), indexLast));
                sb.append(text.substring(indexLast + patt.length(), text.length()));
                text = sb.toString();
                System.out.println("Shaked it.");

                //change pattern
                int indexToBeRemoved = patt.length() / 2;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(patt.substring(0, indexToBeRemoved));
                sb2.append(patt.substring(indexToBeRemoved + 1, patt.length()));
                patt = sb2.toString();

                //exit if pattern or text is 0
                if (patt.length() < 1 || text.length() < 1) {
                    System.out.println("No shake.");
                    break;
                }

            } else {
                System.out.println("No shake.");
                break;
            }
        }
        System.out.println(text);
    }
}
