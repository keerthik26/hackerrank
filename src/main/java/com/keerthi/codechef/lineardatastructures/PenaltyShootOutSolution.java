package com.keerthi.codechef.lineardatastructures;

import java.util.Scanner;

public class PenaltyShootOutSolution {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            int numOfShots = Integer.parseInt(in.nextLine());
            System.out.println(getResultShot(in.nextLine(),numOfShots));
        }
    }

    public static int getResultShot(String input, int numOfShots){
        int teamAScore = 0;
        int teamBScore = 0;
        int mid = numOfShots/2;
        int[] shotsOfA = new int[numOfShots];
        int[] shotsOfB = new int[numOfShots];
        String[] shots = input.split("");
        for (int i = 0; i < numOfShots; i++) {
            shotsOfA[i] = Integer.parseInt(shots[2*i]);
            shotsOfB[i] = Integer.parseInt(shots[(2*i)+1]);
        }
        for (int i = 0; i < mid; i++) {
            teamAScore += shotsOfA[i];
            teamBScore += shotsOfB[i];
        }
        for (int i = mid; i < numOfShots; i++) {

                teamAScore += shotsOfA[i];
                if(teamAScore > (teamBScore + (numOfShots - i))){
                    return (2 * i) + 1;
                }
                if(teamBScore > (teamAScore + (numOfShots - i - 1))){
                    return (2 * i) + 1;
                }
                teamBScore += shotsOfB[i];
                if(teamBScore > (teamAScore + (numOfShots - i - 1))){
                    return (2 * i) + 2;
                }
                if(teamAScore > (teamBScore + (numOfShots - i - 1))){
                    return (2 * i) + 2;
                }
        }

        return 2 * numOfShots;
    }
    public static void runTests(){
        assert getResultShot("101011",3) == 4 : "Should be 4";
        assert getResultShot("100001",3) == 6 : "Should be 6";
    }
}
