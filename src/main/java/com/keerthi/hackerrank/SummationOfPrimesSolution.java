package com.keerthi.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SummationOfPrimesSolution {
    public static void main(String[] args) {
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
        result.forEach(System.out::println);

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
        primes = primeNumbersTill(MAX_NUMBER);
    }

   Long summationOfPrimes(int left, int right){
       return primes.stream().mapToLong(number -> (long) number).filter(number -> number >= left && number <= right).sum();
   }

    /*This is best Java 8 approach */
    public  List<Integer> primeNumbersTill(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(this::isPrime).boxed()
                .collect(Collectors.toList());
    }
    private  boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .noneMatch(n -> number % n == 0);
    }

}
