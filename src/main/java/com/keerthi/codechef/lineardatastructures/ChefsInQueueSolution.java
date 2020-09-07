package com.keerthi.codechef.lineardatastructures;

import java.util.Scanner;
import java.util.Stack;
/**All the chefs (except the Head Chef) are standing in queue to submit their bills. The chefs have different seniority. In all there are N chefs of K different seniority levels. These are given to you in an array, where A1, A2, ..., AN denote the seniority of chefs in the queue. AN denotes front of the queue and A1 denotes end of the queue.
 Head Chef gets an interesting thought past his head. He begins to think what if every chef starting from the end of the queue begins to delegate his job of submitting bills to a chef least ahead of him in the queue but junior to him. The Head Chef's fearfulness of this scenario is f = i2 - i1 + 1, where i1 is the index of chef in queue and i2 is the index of the junior chef. Head Chef's total fearfulness of chaos is the product of all the fearfulness in Head Chef's mind. Note if a chef has no junior ahead of him/her in the queue then Head Chef's fearfulness for this Chef is 1.
 You are required to find the Head Chef's total fearfulness given an arrangement of Chef's in a queue. Since this number can be quite large output it modulo 1000000007.

 Input
 Input description.
 The first line contains two integers N and K denoting the number of chefs and the number of seniority levels. The second line contains N space-separated integers A1, A2, ..., AN denoting the seniority of chefs in the queue. AN denotes front of the queue and A1 denotes end of the queue.

 Output
 Output description.
 Output a single integer denoting the total fearfulness of the Head Chef.

 Constraints
 1 ≤ N ≤ 1000000
 1 ≤ a_i ≤ 1000000
 2 ≤ K ≤ 100000

 Example

 Input:
 4 2
 1 2 1 2

 Output:
 2

 Explanation
 Example case 1. Only the second chef has a junior in front of him and the fearfulness he causes to the head chef is 3 - 2 + 1 = 2. Every other chef causes a fearfulness of 1 for the Head Chef.
 */
public class ChefsInQueueSolution {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfChefs = in.nextInt();
        int numOfLevels = in.nextInt();
        int[] chefsQueue = new int[numOfChefs];
        for (int i = 0; i < numOfChefs; i++) {
            chefsQueue[i] = in.nextInt();
        }
        System.out.println(getFearfulness(chefsQueue,numOfLevels));
    }


    public static long getFearfulness(int[] chefLevels, int numOfLevels){

        final int modulo = 1000000007;
        long fearfulness = 1L;
        Stack<Chef> chefStack = new Stack<>();
        for (int i = 0; i < chefLevels.length; i++) {
            while (!chefStack.empty() && chefLevels[i] < chefStack.peek().level){
                fearfulness  = (fearfulness * ((i - chefStack.peek().position + 1) % modulo)) % modulo;
                chefStack.pop();
            }
            chefStack.push(new Chef(chefLevels[i], i));
        }

        return fearfulness;
    }

    public static void runTests(){
        assert getFearfulness(new int[]{1,2,1,2}, 2) == 2 : "Should be 2";
        assert getFearfulness(new int[]{1,2,2,2}, 2) == 1 : "Should be 1";
        assert getFearfulness(new int[]{2,2,2,1}, 2) == 24 : "Should be 24";
        assert getFearfulness(new int[]{4,3,2,1}, 4) == 8 : "Should be 8";
        assert getFearfulness(new int[]{2,3,1,1}, 3) == 6 : "Should be 2";
    }
}

class Chef{
    int level;
    int position;

    public Chef(int level, int position) {
        this.level = level;
        this.position = position;
    }
}