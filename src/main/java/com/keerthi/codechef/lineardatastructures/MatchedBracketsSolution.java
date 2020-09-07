package com.keerthi.codechef.lineardatastructures;

import java.util.Scanner;
/**A sequence of opening and closing brackets is well-bracketed if we can pair up each opening bracket with a matching closing bracket in the usual sense. For instance, the sequences (), (()) and ()(()) are well-bracketed, while (, ()), (()(), and )( are not well-bracketed.
 The nesting depth of a well-bracketed sequence tells us the maximum number of levels of inner matched brackets enclosed within outer matched brackets. For instance, the nesting depth of () and ()()() is 1, the nesting depth of (()) and ()(()) is 2, the nesting depth of ((())) is 3, and so on.
 Given a well-bracketed sequence, we are interested in computing the following:
 The nesting depth, and the first position where it occurs–this will be the position of the first opening bracket at this nesting depth, where the positions are numbered starting with 1.
 The maximum number of symbols between any pair of matched brackets, including both the outer brackets, and the first position where this occurs–that is, the position of the first opening bracket of this segment
 For instance, the nesting depth of ()(())()(()())(()()) is 2 and the first position where this occurs is 4. The opening bracket at position 10 is also at nesting depth 2 but we have to report the first position where this occurs, which is 4.
 In this sequence, the maximum number of symbols between a pair of matched bracket is 6, starting at position 9. There is another such sequence of length 6 starting at position 15, but this is not the first such position.

 Input format
 The input consists of two lines. The first line is a single integer N, the length of the bracket sequence. Positions in the sequence are numbered 1,2,…,N. The second line is a sequence of N space-separated integers that encode the bracket expression as follows: 1 denotes an opening bracket ( and 2 denotes a closing bracket ). Nothing other than 1 or 2 appears in the second line of input and the corresponding expression is guaranteed to be well-bracketed.

 Output format
 Your program should print 4 space-separated integers in a line, denoting the four quantities asked for in the following order: nesting depth, first position that achieves the nesting depth, length of the maximum sequence between matching brackets and the first position where such a maximum length sequence occurs.

 Test data
 You may assume that 2 ≤ N ≤ 105. In 30% of the test cases, 2 ≤ N ≤ 103.

 Sample Input
 20
 1 2 1 1 2 2 1 2 1 1 2 1 2 2 1 1 2 1 2 2

 Sample Output
 2 4 6 9
 */
public class MatchedBracketsSolution {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int sequenceLength = Integer.parseInt(in.nextLine());
        int[] input = new int[sequenceLength];
        for (int i = 0; i < sequenceLength; i++) {
            input[i] =  in.nextInt();
        }
        getOutput(input, sequenceLength);
    }

    public static void getOutput(int[] input, int sequenceLength){
        int nestingDepth = 0;
        int maxLength = 0;
        int firstNestingDepthPosition = 0;
        int firstMaxLengthPosition = 0;
        int stackCount = 0;
        int currentSequenceLength = 0;
        for (int i = 0; i < sequenceLength; i++) {
            currentSequenceLength++;
            if(input[i] == 1){
                stackCount++ ;
            }else {
                if(stackCount > nestingDepth){
                    nestingDepth = stackCount;
                    firstNestingDepthPosition = i;
                }
                stackCount--;
                if(stackCount == 0){
                    if(currentSequenceLength > maxLength) {
                        maxLength = currentSequenceLength;
                        firstMaxLengthPosition = (i - currentSequenceLength) + 2;
                    }
                    currentSequenceLength = 0;
                }
            }
        }
        System.out.println(nestingDepth + " " + firstNestingDepthPosition + " " + maxLength + " " + firstMaxLengthPosition);
    }
    public static void runTests(){
        return;
    }
}
