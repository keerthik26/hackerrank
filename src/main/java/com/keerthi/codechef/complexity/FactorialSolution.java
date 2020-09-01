package com.keerthi.codechef.complexity;

import java.util.Scanner;

public class FactorialSolution {
    public static void main(String[] args) throws Exception {
        runTests();
        runMain();
    }

    static void runMain() {
        Scanner scanner = new Scanner(System.in);
        int tests = scanner.nextInt();
        Factorial factorial = new Factorial();
        for (int i = 0; i < tests; i++) {
            int number = scanner.nextInt();
            System.out.println(factorial.numOfTrailingZeroes(number));
        }

    }

    static void runTests() {
        Factorial factorial = new Factorial();
        assert factorial.numOfTrailingZeroes(3) == 0 : "Z(3) should be 0";
        assert factorial.numOfTrailingZeroes(4) == 0 : "Z(4) should be 0";
        assert factorial.numOfTrailingZeroes(5) == 1 : "Z(5) should be 1";
        assert factorial.numOfTrailingZeroes(6) == 1 : "Z(6) should be 1";
        assert factorial.numOfTrailingZeroes(60) == 14 : "Z(60) should be 14";
        assert factorial.numOfTrailingZeroes(100) == 24 : "Z(100) should be 24";
        assert factorial.numOfTrailingZeroes(1024) == 253 : "Z(1024) should be 253";
        assert factorial.numOfTrailingZeroes(23456) == 5861 : "Z(23456) should be 5861";
        assert factorial.numOfTrailingZeroes(8735373) ==  2183837 : "Z(8735373) should be 2183837";

    }

}

class Factorial {

    public  int numOfTrailingZeroes(int number){
        int count = 0;
        while (number > 0) {
            count += (number / 5);
            number /= 5;
        }
        return count;
    }



}


