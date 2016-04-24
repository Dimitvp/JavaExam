package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Problem04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+)$");

        TreeMap<String, TreeMap<String, TreeSet<String>>> databse = new TreeMap<>();

        String line;
        while (!"It's testing time!".equals(line = sc.nextLine())) {
            //get the input
            Matcher m = pattern.matcher(line);

            while (m.find()) {

                String classA = m.group(1);
                String method = m.group(2);
                String test = m.group(3);

                //fill the database
                if (!databse.containsKey(classA)) {
                    databse.put(classA, new TreeMap<>());
                }
                if (!databse.get(classA).containsKey(method)) {
                    databse.get(classA).put(method, new TreeSet<>());
                }

                databse.get(classA).get(method).add(test);
            }
        }

        //Sort classes

        //fill map with classes and the total number of their tests.
        TreeMap<String, Integer> classesMethods = new TreeMap<>();
        for (Map.Entry<String, TreeMap<String, TreeSet<String>>> entry : databse.entrySet()) {
            classesMethods.put(entry.getKey(), entry.getValue().keySet().size());

        }

        //sort the map
        Map<String, Integer> newClassesMethods = sortByValueReverse(classesMethods);

        //fill map with classes and the total number of their tests
        TreeMap<String, Integer> classTests = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : newClassesMethods.entrySet()) {
            Integer totalAmount = 0;
            for (Map.Entry<String, TreeSet<String>> innerEntry : databse.get(entry.getKey()).entrySet()) {
                int numberTest = innerEntry.getValue().size();
                totalAmount += numberTest;
            }
            classTests.put(entry.getKey(), totalAmount);
        }

        //final map for the classes - sort by number of tests
        Map<String, Integer> finalClasses = sortByValueReverse(classTests);

        //fill map with classes, methods and the number of their test
        LinkedHashMap<String, TreeMap<String, Integer>> classMethotNumberOfTests = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : finalClasses.entrySet()) {
            classMethotNumberOfTests.put(entry.getKey(), new TreeMap<>());
            for (Map.Entry<String, TreeSet<String>> innerEntry : databse.get(entry.getKey()).entrySet()) {
                int numberTest = innerEntry.getValue().size();
                classMethotNumberOfTests.get(entry.getKey()).put(innerEntry.getKey(), numberTest);
            }
        }

        //Sort the methods by the number of their test
        LinkedHashMap<String, Map<String, Integer>> classMethotNumberofTests2 = new LinkedHashMap<>();
        for (Map.Entry<String, TreeMap<String, Integer>> entry : classMethotNumberOfTests.entrySet()) {
            Map<String, Integer> newMapMethods = sortByValueReverse(entry.getValue());
            classMethotNumberofTests2.put(entry.getKey(), newMapMethods);
        }


        //printing the output
        for (Map.Entry<String, Integer> entry : finalClasses.entrySet()) {
            //print class
            System.out.println(entry.getKey() + ":");
            for (Map.Entry<String, Integer> innerEntry : classMethotNumberofTests2.get(entry.getKey()).entrySet()) {
                //print method
                System.out.println("##" + innerEntry.getKey());

                //get all tests of this method
                ArrayList<String> tests = new ArrayList<>();
                tests.addAll(databse.get(entry.getKey()).get(innerEntry.getKey()));

                //sort all the tests first
                StringLengthListSort ss = new StringLengthListSort();
                Collections.sort(tests, ss);
                for (String item : tests) {
                    System.out.println("####" + item);
                }
            }
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueReverse(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}

class StringLengthListSort implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }

}
