package com.company;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem04_GUnit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Regex pattern
        Pattern pattern = Pattern.compile("^([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+)$");

        //Database stores all classes, their methods and all of their tests
        //Using TreeMaps for classes and methods because the last sorting condition is "alphabetically"
        //Using Set because the tests are unique - "If even the test is not new, you should do nothing."
        //      class           method          test
        TreeMap<String, TreeMap<String, TreeSet<String>>> database = new TreeMap<>();

        //fill the database with all valid input lines
        String line;
        while (!"It's testing time!".equals(line = sc.nextLine())) {

            Matcher m = pattern.matcher(line);

            //only of the line matches the pattern continue filling the database with the corresponding class, method and test
            while (m.find()) {
                String classs = m.group(1);
                String method = m.group(2);
                String test = m.group(3);

                //if database does not contain this class - add it
                if (!database.containsKey(classs)) {
                    database.put(classs, new TreeMap<>());
                }
                //if this class does not have this method yet - add it
                if (!database.get(classs).containsKey(method)) {
                    database.get(classs).put(method, new TreeSet<>());
                }

                //finally add the test to the set - all non-unique values wont be stored twice (Set)
                database.get(classs).get(method).add(test);
            }
        }

        //Create another map for the classes and the total number of their tests
        //      class   total amount of tests
        TreeMap<String, Integer> classTotalTests = new TreeMap<>();

        //loop trough all classes
        for (Map.Entry<String, TreeMap<String, TreeSet<String>>> classs : database.entrySet()) {
            int totalTests = 0;
            //loop trough all methods
            for (Map.Entry<String, TreeSet<String>> method : classs.getValue().entrySet()) {
                //get the size of the Set of each method = the total number of tests of each method
                totalTests += method.getValue().size();
            }
            //fill the new map
            classTotalTests.put(classs.getKey(), totalTests);
        }

        /*
        1. The classes should be ordered first by the amount of unit tests it has – descending,
        then by the amount of methods it has – ascending, and then alphabetically.

        2.The methods should be ordered by the amount of unit tests they have - descending,
        and then alphabetically.

        3. The unit tests should be ordered by the length of their names – ascending and
        then by alphabetically.
         */

        //Sort and print output
        //First method - using Integer.compare() and .compareTo()
        database.entrySet().stream().sorted((c1, c2) -> Integer.compare(c1.getValue().size(), c2.getValue().size())).
                sorted((c1, c2) -> classTotalTests.get(c2.getKey()).compareTo(classTotalTests.get(c1.getKey()))).
                forEach(classs -> {
                    //Print class - the Key of each entry
                    System.out.println(classs.getKey() + ":");

                    //loop trough the value of each entry - the TreeMap of each class, containing its methods and their corresponding tests
                    classs.getValue().entrySet().stream().sorted((m1, m2) -> Integer.compare(m2.getValue().size(), m1.getValue().size())).
                            forEach(method -> {

                                //Print method - the key of each entry
                                System.out.println("##" + method.getKey());

                                //loop trough the value of each entry - the TreeSet of each method, contains its tests
                                method.getValue().stream().sorted((t1, t2) -> Integer.compare(t1.length(), t2.length())).
                                        //finally print each test
                                        forEach(test -> System.out.println("####" + test));

                            });

                });


        //Second method - using subtraction instead of Integer.compare() and .compareTo()
        /*
        database.entrySet().stream().sorted((c1, c2) -> c1.getValue().size() - c2.getValue().size()).
                sorted((c1, c2) -> classTotalTests.get(c2.getKey())- classTotalTests.get(c1.getKey())).
                forEach(classs -> {
                    //Print class - the Key of each entry
                    System.out.println(classs.getKey() + ":");

                    //loop trough the value of each entry - the TreeMap of each class, containing its methods and their corresponding tests
                    classs.getValue().entrySet().stream().sorted((m1, m2) -> m2.getValue().size() - m1.getValue().size()).
                            forEach(method -> {

                                //Print method - the key of each entry
                                System.out.println("##" + method.getKey());

                                //loop trough the value of each entry - the TreeSet of each method, contains its tests
                                method.getValue().stream().sorted((t1, t2) -> t1.length()- t2.length()).
                                        //finally print each test
                                                forEach(test -> System.out.println("####" + test));

                            });

                });
        */


        //Third method = using subtraction without exchanging the positions of the two arguments
        /*
        database.entrySet().stream().sorted((c1, c2) -> c1.getValue().size() - c2.getValue().size()).
                sorted((c1, c2) -> -(classTotalTests.get(c1.getKey())- classTotalTests.get(c2.getKey()))).
                forEach(classs -> {
                    //Print class - the Key of each entry
                    System.out.println(classs.getKey() + ":");

                    //loop trough the value of each entry - the TreeMap of each class, containing its methods and their corresponding tests
                    classs.getValue().entrySet().stream().sorted((m1, m2) -> -(m1.getValue().size() - m2.getValue().size())).
                            forEach(method -> {

                                //Print method - the key of each entry
                                System.out.println("##" + method.getKey());

                                //loop trough the value of each entry - the TreeSet of each method, contains its tests
                                method.getValue().stream().sorted((t1, t2) -> t1.length()- t2.length()).
                                        //finally print each test
                                                forEach(test -> System.out.println("####" + test));

                            });

                });

       */
    }
}
