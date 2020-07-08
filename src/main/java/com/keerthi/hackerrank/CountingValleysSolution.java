package com.keerthi.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class CountingValleysSolution {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        char[] steps = s.toCharArray();
        boolean startValley = false;
        int relativePositionToSea = 0;
        int noOfValleys = 0;
        for (char stepChar : steps) {
            int step = stepChar == 'U'? 1: -1;
            if(relativePositionToSea == 0){
                startValley = step < 0 ? true : false;
            }
            relativePositionToSea += step;
            if(relativePositionToSea == 0 && startValley) {
                noOfValleys++;
            }
        }
        if(relativePositionToSea != 0){
            throw new RuntimeException("Invalid Hike path");
        }

        return noOfValleys;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);
        System.out.println(result);
        scanner.close();
    }
}
