package com.keerthi.codechef.lineardatastructures;

import java.util.Scanner;

/**
Teams A and B are having a penalty shoot-out to decide the winner of their football match. Each team gets to take a shot N times, and the team with the higher number of scored goals at the end wins. If the number of goals scored during the shoot-out by each team turns out to be the same even after all 2N
 shots, then they stop and agree that the result is a draw.

 The two teams take shots alternately — team A shoots first, then team B shoots, then team A and so on until team B takes its N-th shot (at which point each team has taken exactly N shots). Even though all 2N shots are taken, the result of the shoot-out is often known earlier — e.g. if team A has already scored N−1 goals and team B has missed at least two shots, team A
 is definitely the winner.

For each shot, you know whether it was a goal or a miss. You need to find the earliest moment when the winner is known — the smallest s
(0≤s≤2N) such that after s shots, the result of the shoot-out (whether team A won, team B won or the match is drawn) would be known even if we did not know the results of the remaining 2N−s
 shots.

Input
 The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
 The first line of each test case contains a single integer N.
 The second line contains a string S with length 2⋅N, where for each valid i, the i-th character is '0' if the i-th shot was a miss or '1' if it was a goal.

Output
For each test case, print a single line containing one integer — the smallest s such that after s shots, the result of the shoot-out is known.

Constraints
 1≤T≤105
 1≤N≤105
 S contains only characters '0' and '1'
 the sum of N over all test cases does not exceed 106

Example Input

2
3
101011
3
100001

Example Output

4
6
*/

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
