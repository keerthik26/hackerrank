package com.keerthi.codechef.complexity;

import java.util.Scanner;

public class LadduAccrualSolution {
    public static void main(String[] args) {
        //runTests();
        Scanner in  = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            User user = new User();
            String[] line1 = in.nextLine().split(" ");
            int numOfActivities = Integer.parseInt(line1[0]);
            user.setIndian( "INDIAN".equalsIgnoreCase(line1[1]) ? true : false);
            for (int j = 0; j < numOfActivities; j++) {
                String[] input = in.nextLine().split(" ");
                String activity = input[0];
                switch (activity){
                    case "CONTEST_WON" :
                        user.setContextWinning(Integer.parseInt(input[1]));
                        break;
                    case "TOP_CONTRIBUTOR" :
                        user.setIsTopContributor(true);
                        break;
                    case "BUG_FOUND" :
                        user.setBugFoundSeverity(Integer.parseInt(input[1]));
                        break;
                    case "CONTEST_HOSTED" :
                        user.setContestHosting(true);
                        break;
                }
            }
            System.out.println(user.getMaxNumOfMonthsToRedeemLaddus());
        }
    }


}

class User {
    private int contextWinning = 0;
    private int bugFoundSeverity = 0;
    private int topContribution = 0;
    private int contestHosting = 0;
    private boolean isIndian;


    public int getContextWinning() {
        return contextWinning;
    }

    public void setContextWinning(int rank) {
        int bonus = rank < 20 ? (20 - rank) : 0;
        this.contextWinning += (300 + (bonus));
    }

    public int getBugFoundSeverity() {
        return bugFoundSeverity;
    }

    public void setBugFoundSeverity(int bugFoundSeverity) {
        this.bugFoundSeverity += bugFoundSeverity;
    }


    public int getTopContribution() {
        return topContribution;
    }


    public void setIsTopContributor(boolean isTopContributor) {
        if(isTopContributor)
        this.topContribution += 300;
    }

    public int getContestHosting() {
        return contestHosting;
    }

    public void setContestHosting(boolean hasHosted) {
        if(hasHosted)
        this.contestHosting += 50;
    }



    public boolean isIndian() {
        return isIndian;
    }

    public void setIndian(boolean indian) {
        isIndian = indian;
    }

    public int getMaxNumOfMonthsToRedeemLaddus(){
        int totalLaddus = getContextWinning() + getTopContribution() + getBugFoundSeverity() + getContestHosting();
        return isIndian() ? totalLaddus/200 : totalLaddus/400;
    }
}
