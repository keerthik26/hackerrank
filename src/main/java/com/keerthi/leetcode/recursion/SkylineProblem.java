package com.keerthi.leetcode.recursion;

import java.util.*;

public class SkylineProblem {
    TreeMap<Integer,Integer> heightsMapByPoint = new TreeMap<>();
    public List<List<Integer>> getSkyline_V1(int[][] buildings) {
        if(buildings.length == 0) return null;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> points = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] building = buildings[i];
            int x1 = building[0];
            int x2 = building[1];
            int y = building[2];
            points.add(x1);
            points.add(x2);
            for (int j = x1; j <= x2; j++) {
                if(j == x2){
                    y = 0;
                }

                if(heightsMapByPoint.containsKey(j)){
                    heightsMapByPoint.put(j, Math.max(y, heightsMapByPoint.get(j)));
                }else {
                    heightsMapByPoint.put(j,y);
                }
            }
        }
        Collections.sort(points);
        int prevHeight = -1;
        for (Integer num : points) {
            Integer currHeight = heightsMapByPoint.get(num);
            if(currHeight != prevHeight) {
                result.add(Arrays.asList(num, currHeight));
                prevHeight = currHeight;
            }
        }
        return result;
    }



    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> result = new ArrayList<>();
        int size = buildings.length;
        if(size == 0){
            return result;
        }
        if(size == 1){
            result.add(Arrays.asList(buildings[0][0],buildings[0][2]));
            result.add(Arrays.asList(buildings[0][1],0));
            return result;
        }
        List<List<Integer>> leftSkyline = getSkyline(Arrays.copyOfRange(buildings, 0, size / 2));
        List<List<Integer>> rightSkyline = getSkyline(Arrays.copyOfRange(buildings,  size / 2,size));
        return merge(leftSkyline,rightSkyline);
    }

    private List<List<Integer>> merge(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline){
        List<List<Integer>> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        int leftHeight, rightHeight, currHeight = 0;
        int leftSize = leftSkyline.size();
        int rightSize = rightSkyline.size();
        int currX = 0;
        while (leftIndex < leftSize && rightIndex < rightSize){
            if(leftSkyline.get(leftIndex).get(0) <= rightSkyline.get(rightIndex).get(0)){
                currX = leftSkyline.get(leftIndex).get(0);
                leftHeight = leftSkyline.get(leftIndex).get(1);
                rightHeight = getHeight(rightSkyline,rightIndex,currX);
                currHeight = Math.max(leftHeight,rightHeight);
                leftIndex++;
            }else {
                currX = rightSkyline.get(rightIndex).get(0);
                leftHeight = getHeight(leftSkyline,leftIndex,currX);
                rightHeight = rightSkyline.get(rightIndex).get(1);
                currHeight = Math.max(leftHeight,rightHeight);
                rightIndex++;
            }
            if(resultIndex > 0 && result.get(resultIndex - 1).get(1) == currHeight){
                continue;
            }else {
                result.add(Arrays.asList(currX,currHeight));
                resultIndex++;
            }

        }

        while (leftIndex < leftSize){
            currHeight = leftSkyline.get(leftIndex).get(1);
            if(resultIndex > 0 && result.get(resultIndex - 1).get(1) == currHeight){
                leftIndex++;
                continue;
            }else {
                result.add(leftSkyline.get(leftIndex));
                leftIndex++;
                resultIndex++;
            }

        }

        while (rightIndex < rightSize){
            currHeight = rightSkyline.get(rightIndex).get(1);
            if(resultIndex > 0 && result.get(resultIndex - 1).get(1) == currHeight){
                rightIndex++;
                continue;
            }else {
                result.add(rightSkyline.get(rightIndex));
                rightIndex++;
                resultIndex++;
            }

        }

        return result;
    }

    public int getHeight(List<List<Integer>> skyline, int index, int x){
        int i = 0;
        int height = 0;
        while (i < skyline.size() && skyline.get(i).get(0) <= x){
            if(skyline.get(i).get(0) == x){
                return skyline.get(i).get(1);
            }
            i++;
        }
        if(i > 0) {
            height = skyline.get(i - 1).get(1);
        }
        return height;
    }

    public static void main(String[] args) {
        SkylineProblem sol = new SkylineProblem();
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
       System.out.println(sol.getSkyline(buildings));
       /* List<List<Integer>> skyline = Arrays.asList(Arrays.asList(2,10),Arrays.asList(3,15),Arrays.asList(7,10),Arrays.asList(9,0));
        System.out.println(sol.getHeight(skyline,2,8));*/
    }
}

