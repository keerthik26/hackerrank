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
        //runTests();
        Scanner scanner = new Scanner(System.in);
        int numOfInputLines =  Integer.parseInt(scanner.nextLine().trim());

        //System.out.println("Start: " + startTime);
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
        long startTime = System.currentTimeMillis();
        primes = primeNumbersTill(MAX_NUMBER);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

    }

   Long summationOfPrimes(int left, int right){
       return primes.stream().mapToLong(number -> (long) number).filter(number -> number >= left && number <= right).sum();
   }

   /*Using For Loop*/
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

    /*Above getPrimes method is re-written using Java 8 Streams but it is inefficient */
    List<Integer> getPrimesJava8(int number){
        boolean[] composites = new boolean[number+1];
        IntStream.rangeClosed(2, (int) ceil(sqrt(number))).parallel().flatMap(factor -> IntStream.rangeClosed(factor + 1, number).filter(value -> (((value % factor) == 0)))).forEach(
              integer ->   composites[integer] = true
        );

        return IntStream.rangeClosed(2, number).filter(integer -> !composites[integer]).boxed().collect(Collectors.toList());
    }

    /*This is best Java 8 approach */
    public  List<Integer> primeNumbersTill(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(x -> isPrime(x)).boxed()
                .collect(Collectors.toList());
    }
    private  boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .noneMatch(n -> number % n == 0);
    }

}
