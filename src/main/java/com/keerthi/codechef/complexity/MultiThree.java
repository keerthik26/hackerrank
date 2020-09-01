package com.keerthi.codechef.complexity;

import java.util.Scanner;

public class MultiThree {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            String[] input = in.nextLine().split(" ");
            long numOfDigits = Long.parseLong(input[0]);
            int d0 = Integer.parseInt(input[1]);
            int d1 = Integer.parseInt(input[2]);
            System.out.println(isMultipleOfThree(numOfDigits,d0,d1) ? "YES" : "NO");
        }
    }

    /*public static boolean isMultipleOfThree(long numOfDigits, int d0, int d1){
        long sum = d0  ;
        int currentDigit = d1;

        for (long i = 2; i < numOfDigits; i++) {
            sum += currentDigit;
            currentDigit = (int) sum % 10;
        }
        return (sum+currentDigit) % 3 == 0;
    }*/

    public static boolean isMultipleOfThree(long numOfDigits, int d0, int d1){
        long sum ;
        int s = d0 + d1;
        int c  = ((2*s) % 10) + ((4*s) %10) + ((8*s) % 10) + ((16*s) % 10);
        long numOfCycles = (numOfDigits - 3)/4;
        long leftDigits = (numOfDigits - 3) % 4;
        long leftOverSum = 0;
        for (int i = 1; i <= leftDigits; i++) {
            leftOverSum += ((Math.pow(2,i) * s) % 10);
        }
        sum = d0 + d1 + (s % 10) + (numOfCycles * c) + leftOverSum;
        return (sum) % 3 == 0;
    }

    static void runTests(){
        assert isMultipleOfThree(5,3,4) == false : "Should not be a multiple of 3";
        assert isMultipleOfThree(13,8,1) == true : "Should be a multiple of 3";
        assert isMultipleOfThree(760399384224L ,5,1) == true : "Should be a multiple of 3";
    }


}
