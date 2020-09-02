package com.keerthi.codechef.lineardatastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**As usual, I went to work in the morning. Unfortunately, I found out that my manager bought a new machine and I have to learn to operate it.
 There are N boxes in a line (numbered 1 through N). Initially, the boxes are empty, and I need to use the machine to put tokens in them. For each valid i, the i-th box has a maximum capacity Si tokens. I can perform the following operation any number of times: choose an integer L (1≤L≤N) and put one token in each of the boxes 1,2,…,L.
 My manager told me to put as many tokens as possible into the boxes in total (the distribution of tokens in the boxes does not matter). Of course, it is not allowed to perform an operation that would result in the number of tokens in some box exceeding its capacity. Can you help me calculate the maximum number of tokens that can be put in these boxes?
 Input
 The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
 The first line of each test case contains a single integer N.
 The second line contains N space-separated integers S1,S2,…,SN.

 Output
 For each test case, print a single line containing one integer - the maximum number of tokens.
 Constraints
 1≤T≤100
 1≤N≤106
 1≤Si≤109 for each valid i
 the sum of N over all test cases does not exceed 5⋅106

 Example Input
 1
 3
 2 1 3

 Example Output
 4
 */
public class StupidMachineSolution {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            int numOfBoxes = Integer.parseInt(in.nextLine());
            String input = in.nextLine();
            System.out.println(getMaxNumOfTokens(input,numOfBoxes));
        }
    }

    public static long getMaxNumOfTokens(String input, int numOfBoxes){
        long maxNumOfTokens = 0L;
        long[] capacities = Arrays.stream(input.split(" ")).mapToLong(Integer::parseInt).toArray();
        ArrayList<Integer> indices = new ArrayList<>();
        long firstLeast = Integer.MAX_VALUE;
        for (int i = 0; i < numOfBoxes; i++) {
            if(capacities[i] < firstLeast){
                indices.add(i);
                firstLeast = capacities[i];
            }
        }
        for (int i = 0; i < indices.size(); i++) {
            int nextIndex = (i == (indices.size() - 1)) ? numOfBoxes : indices.get(i+1);
            maxNumOfTokens += (capacities[indices.get(i)] * (nextIndex- indices.get(i)));
        }
        return maxNumOfTokens;
    }

    public static void runTests(){
        assert getMaxNumOfTokens("2 1 3", 3) == 4 : "Should be 4";
        assert getMaxNumOfTokens("20 22 18 25 20 10 2 5 4", 9) == 110 : "Should be 110";
    }
}
