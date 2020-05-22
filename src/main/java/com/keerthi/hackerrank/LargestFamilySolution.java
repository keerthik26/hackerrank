package com.keerthi.hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class LargestFamilySolution {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        Scanner scanner = new Scanner(System.in);
        int numOfChildren = Integer.parseInt(scanner.nextLine().trim());
        List<String> childrenNames = new ArrayList<String>(numOfChildren);
        for (int i = 0; i < numOfChildren; i++) {
            childrenNames.add(scanner.nextLine().trim());
        }
        LargestFamily sol = new LargestFamily();
        System.out.println(sol.largestFamily(childrenNames));

        runTests();

    }

    static  void runTests(){
        LargestFamily sol = new LargestFamily();
        // Tests
        assert sol.largestFamily(Arrays.asList("abcbcbc", "abcbbcbcbc", "pqr")) == 2 : "Largest family size should be 2";
        assert sol.largestFamily(Arrays.asList("abcbcbcd", "abcbbcbcbc", "pqr")) == 1 : "Largest family size should be 1";
        assert sol.largestFamily(Arrays.asList("abc", "cab", "pqr","xyz","rst")) == 2 : "Largest family size should be 1";
    }
}

class LargestFamily {

    Long largestFamily(List<String> children) {
        return Collections.max(children.stream()
                .collect(Collectors.groupingBy(this::getFamily, Collectors.counting()))
                .values());
    }

    String getFamily(String child){
       return  child
                .chars()
                .distinct()
                .sorted()
                .mapToObj(x -> (String.valueOf((char) x)))
                .collect(Collectors.joining(""));
    }
}