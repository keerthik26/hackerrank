package com.keerthi.codechef.lineardatastructures;

import java.util.Arrays;
import java.util.Scanner;
/**Chef made N pieces of cakes, numbered them 1 through N and arranged them in a row in this order. There are K possible types of flavours (numbered 1 through K); for each valid i, the i-th piece of cake has a flavour Ai.
 Chef wants to select a contiguous subsegment of the pieces of cake such that there is at least one flavour which does not occur in that subsegment. Find the maximum possible length of such a subsegment of cakes.

 Input
 The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
 The first line of each test case contains two integers N and K.
 The second line contains N space-separated integers A1,A2,…,AN.

 Output
 For each test case, print a single line containing one integer ― the maximum length of a valid subsegment.

 Constraints
 1≤T≤1,000
 1≤N≤105
 2≤K≤105
 1≤Ai≤K for each valid i the sum of N over all test cases does not exceed 106

 Example Input
 2
 6 2
 1 1 1 2 2 1
 5 3
 1 1 2 2 1

 Example Output
 3
 5
 */
public class NotAllFlavoursSolution {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            String[] numInput = in.nextLine().split(" ");
            int numOfCakePieces = Integer.parseInt(numInput[0]);
            int numOfFlavours = Integer.parseInt(numInput[1]);
            String cakePieces = in.nextLine();
            System.out.println(getMaxSubsequence(cakePieces,numOfCakePieces,numOfFlavours));
        }
    }

    public static int getMaxSubsequence(String input, int numOfPieces, int numOfFlavours){
        int[] cakePieces = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int[] flavours = new int[numOfFlavours+1];
        int currentDistinctFlavours = 0;
        Arrays.fill(flavours,0);
        while (right < numOfPieces) {
            while (right < numOfPieces && currentDistinctFlavours < numOfFlavours){
                if(flavours[cakePieces[right]] == 0){
                    currentDistinctFlavours++;
                }
                flavours[cakePieces[right]]++;
                if(currentDistinctFlavours < numOfFlavours){
                    maxLength = Math.max(maxLength,right + 1 - left);
                }
                right++;
            }
            //if(right == numOfPieces) break;
            while (right < numOfPieces && left < right && (currentDistinctFlavours == numOfFlavours)){
                flavours[cakePieces[left]]--;
                if(flavours[cakePieces[left]] == 0){
                    currentDistinctFlavours--;
                }
                left++;
            }
        }
        return maxLength;
    }

   /* public static int getMaxSubsequence(String input, int numOfPieces, int numOfFlavours){
        int[] cakePieces = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxLength = 0;
        int indexOfFirstNextFlavour = -1;
        int i = 0;
        while ( i < numOfPieces) {
            HashSet<Integer> evaluatedFlavours = new HashSet<>();
            int currentLength = 0;
            int j = i;
            while (j < numOfPieces && (evaluatedFlavours.contains(cakePieces[j]) || (evaluatedFlavours.size() < numOfFlavours - 1))) {
                currentLength++;
                evaluatedFlavours.add(cakePieces[j]);
                if(indexOfFirstNextFlavour < i && cakePieces[i] != cakePieces[j]){
                    indexOfFirstNextFlavour = j;
                }
                j++;
            }
            maxLength = Math.max(currentLength, maxLength);
            if (j == numOfPieces)
                break;
            //This is when K=2
            if(indexOfFirstNextFlavour < i){
                indexOfFirstNextFlavour = j;
            }
            i = indexOfFirstNextFlavour > i  ? indexOfFirstNextFlavour : i+1;
        }
        return maxLength;
    }*/
    public static void runTests(){
        assert getMaxSubsequence("1 1 1 2 2 1",6,2)==3 : "Should be 3";
        assert getMaxSubsequence("1 1 2 2 1",5,3)==5 : "Should be 5";
        assert getMaxSubsequence("1 1 2 2 1 3",6,3)==5 : "Should be 5";
        assert getMaxSubsequence("1 1 1 1 1 1",6,3)==6 : "Should be 6";
        assert getMaxSubsequence("1 2 3 4 5 5 3",7,5)==6 : "Should be 6";
        assert getMaxSubsequence("1 1 2 3 4 5 5 3 5",9,5)==7 : "Should be 7";
    }
}
