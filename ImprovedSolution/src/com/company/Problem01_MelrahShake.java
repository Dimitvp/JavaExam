package com.company;

import java.util.Scanner;

public class Problem01_MelrahShake {

    public static void main(String[] args) {

        //getting the input
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String pattern = sc.nextLine();

        //doing the Shake
        while (text.length() > 0 && pattern.length() > 0) {
            int firstIndex = text.indexOf(pattern);
            int lastIndex = text.lastIndexOf(pattern);

            //Do a Shake only if there are at least two instances of the pattern in the text
            if (firstIndex != -1 && lastIndex != -1 && firstIndex != lastIndex){

                //remove border instances of the pattern in the text
                StringBuilder sb = new StringBuilder();
                sb.append(text.substring(0, firstIndex));
                sb.append(text.substring(firstIndex + pattern.length(), lastIndex));
                sb.append(text.substring(lastIndex + pattern.length(), text.length()));
                text = sb.toString();

                //remove a character from the pattern each time we have a Shake
                int indexToBeRemoved = pattern.length()/2;
                sb.setLength(0); //empty the StringBuilder, in order to use it again for the pattern
                pattern = sb.append(pattern).deleteCharAt(indexToBeRemoved).toString();

                //After a successful shake, print:
                System.out.println("Shaked it.");
            }
            else {
                //if there are less than two instances of the patttern in the string -> exit
                break;
            }
        }

        //Print final output
        System.out.println("No shake.");
        System.out.println(text);
    }
}
