package com.keerthi.codechef.lineardatastructures;

import java.util.ArrayList;
import java.util.Scanner;

public class ChefAndStreetFoodSolution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            int numOfFoodItems = Integer.parseInt(in.nextLine());
           // ArrayList<StreetFood> streetFoods = new ArrayList<>();
            int maxProfit = 0;
            for (int j = 0; j < numOfFoodItems; j++) {
                String[] foodDetails = in.nextLine().split(" ");
                //streetFoods.add(new StreetFood(Integer.parseInt(foodDetails[0]),Integer.parseInt(foodDetails[1]),Integer.parseInt(foodDetails[2])));
                maxProfit = Math.max(new StreetFood(Integer.parseInt(foodDetails[0]),Integer.parseInt(foodDetails[1]),Integer.parseInt(foodDetails[2])).getDailyProfitForNewStore(),maxProfit);
            }
            System.out.println(maxProfit);
        }
    }
}
class StreetFood{
    int numOfStores;
    int numOfCustomers;
    int price;

    public StreetFood(int numOfStores, int numOfCustomers, int price) {
        this.numOfStores = numOfStores;
        this.numOfCustomers = numOfCustomers;
        this.price = price;
    }

    public int getDailyProfitForNewStore(){
        return Math.floorDiv(numOfCustomers,numOfStores+1) * price;
    }
}
