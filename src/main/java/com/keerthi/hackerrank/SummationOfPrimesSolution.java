package com.keerthi.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class SummationOfPrimesSolution {
    public static void main(String args[] ) throws Exception {
        runTests();
        Scanner scanner = new Scanner(System.in);
        int numOfInputLines =  Integer.parseInt(scanner.nextLine().trim());
        SummationOfPrimes sol = new SummationOfPrimes();
        List<Long> result = new ArrayList<>(numOfInputLines);
        for (int i = 0; i < numOfInputLines; i++) {
            String[] limits = scanner.nextLine().trim().split(" ");
            int left = Integer.parseInt(limits[0]);
            int right = Integer.parseInt(limits[1]);
            result.add(sol.summationOfPrimes(left,right));
        }
        result.stream().forEach(System.out::println);
    }

    static void runTests(){
        SummationOfPrimes sol = new SummationOfPrimes();
        assert sol.summationOfPrimes(1,9) == 17 : "Sum of primes should be 17";
        assert sol.summationOfPrimes(4,13) == 36 : "Sum of primes should be 36";
    }

}

class SummationOfPrimes {
    static final int MAX_NUMBER = 1000000;
    static List<Integer> primes = null;
    public SummationOfPrimes() {
        primes = getPrimes(MAX_NUMBER);
    }

   /* List<Long> summationOfPrimes(List<List<Integer>> input){
        return input.stream().map(limit -> {
            return primes.stream().mapToLong(number -> (long) number).filter(number -> number >= limit.get(0) && number <= limit.get(1)).reduce(0,Long::sum);
        }).collect(Collectors.toList());
    }*/
   Long summationOfPrimes(int left, int right){
       return primes.stream().mapToLong(number -> (long) number).filter(number -> number >= left && number <= right).reduce(0,Long::sum);
   }

    List<Integer> getPrimes(int number){
        boolean[] composites = new boolean[number];
        composites[0] = true;
        for (int factor = 2; factor <= ceil(sqrt(number)); factor++) {
            for (int index = factor - 1; index < number ; index++) {
                if(!composites[index]  && (index+1 != factor) && ((index+1) % factor == 0))
                    composites[index] = true;
            }
        }
        return IntStream.rangeClosed(1, number).filter(integer -> !composites[integer-1]).boxed().collect(Collectors.toList());
    }
}
