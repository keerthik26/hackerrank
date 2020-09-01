package com.keerthi.codechef.complexity;

import java.util.Scanner;

public class CoinFlipSolution {
    public static void main(String[] args) {
        //runTests();
        Scanner in  = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            int numOfGames = Integer.parseInt(in.nextLine());
            for (int j = 0; j < numOfGames; j++) {
                String[] input = in.nextLine().split(" ");
                boolean isInitialHead = input[0].equals("1") ? true : false;
                boolean getFinalHead = input[2].equals("1") ? true : false;
                int numOfCoins = Integer.parseInt(input[1]);
                CoinFlip coinFlip = new CoinFlip();
                System.out.println(coinFlip.getNumOfCoinsForAState(isInitialHead,numOfCoins,getFinalHead));
            }
        }
    }

    static void  runTests(){
        CoinFlip coinFlip = new CoinFlip();
        assert  coinFlip.getNumOfCoinsForAState(true,5,true) == 2 : "Num of coins showing head should be 2";
        assert  coinFlip.getNumOfCoinsForAState(true,5,false) == 3 : "Num of coins showing tails should be 3";
        assert  coinFlip.getNumOfCoinsForAState(false,5,true) == 3 : "Num of coins showing head should be 3";
        assert  coinFlip.getNumOfCoinsForAState(false,5,false) == 2 : "Num of coins showing tails should be 2";
    }
}

class CoinFlip{

    public int getNumOfCoinsForAState(boolean isInitialHead, int numOfCoins, boolean isFinalHead){
        return isInitialHead == isFinalHead ? numOfCoins/2 : (numOfCoins - (numOfCoins/2));
    }
}