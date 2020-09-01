package com.keerthi.codechef.lineardatastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChefAndStreetFoodSolution {

    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            int numOfFoodItems = Integer.parseInt(in.nextLine());
            ArrayList<StreetFood> streetFoods = new ArrayList<>();
            for (int j = 0; j < numOfFoodItems; j++) {
                String[] foodDetails = in.nextLine().split(" ");
                streetFoods.add(new StreetFood(Integer.parseInt(foodDetails[0]),Integer.parseInt(foodDetails[1]),Integer.parseInt(foodDetails[2])));
            }
            System.out.println(getMaxProfit(streetFoods));
        }
    }

   public static int getMaxProfit(List<StreetFood> foodList) {
      return foodList.stream().mapToInt(StreetFood::getDailyProfitForNewStore).max().getAsInt();
   }

   public static void runTests(){
      assert getMaxProfit(Arrays.asList(new StreetFood(4,6,8),new StreetFood(1,4,3),new StreetFood(2,6,6))) == 12 : "Should be 12";
      assert getMaxProfit(Arrays.asList(new StreetFood(7,7,4))) == 0 : "Should be 0";
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
