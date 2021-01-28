package com.keerthi.leetcode.recursion.backtracking;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RobotRoomCleaner {
    Set<Pair<Integer, Integer>> visitedCells = new HashSet<>();
    MyRobotImpl robot;
   public static List<Direction> directions = Arrays.asList(new Direction(-1,0), new Direction(0,1), new Direction(1,0), new Direction(0,-1));


    public void cleanRoom(Robot robot) {
        this.robot = (MyRobotImpl) robot;
        backtrack(0,0,0);
    }

    private void cleanRoom(MyRobotImpl robot, int startRow, int startCol) {
        this.robot =  robot;
        backtrack(startRow,startCol,0);
    }

    void goBack(){
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    void backtrack(int row, int col, int currentDir){

        visitedCells.add( Pair.of(row,col));
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int newDirection = (currentDir + i) % 4;
            int newRow = row + directions.get(newDirection).row;
            int newCol = col + directions.get(newDirection).col;
            Pair<Integer, Integer> neighbour =  Pair.of(newRow, newCol);
            //
            robot.robotDirection = newDirection;
            if(!visitedCells.contains(neighbour) && robot.move()){
                backtrack(newRow, newCol,newDirection);
                goBack();
            }
            robot.turnRight();
        }
    }

    public static void main(String[] args) {
        RobotRoomCleaner roomCleaner = new RobotRoomCleaner();
       // int[][] roomInput = {{1, 1, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 1, 0, 1, 1}, {1, 0, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1}};
        int[][] roomInput = {{1,1,1},{1,1,0},{1,1,1}};
        int startRow = 0;
        int startCol = 0;
        MyRobotImpl robot = new MyRobotImpl(roomInput, startRow, startCol);
        roomCleaner.cleanRoom(robot,startRow, startCol);
        robot.displayRoom();
    }



}

class Direction{
     int row;
     int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();

}

class MyRobotImpl implements Robot{
    int[][] room;
    int[][] roomDirections;
    int currentRow;
    int currentCol;
    int robotDirection;
    int noOfRows;
    int noOfCols;


    public MyRobotImpl(int[][] input, int startRow, int startCol){
        this.room = input;
        this.currentRow = startRow;
        this.currentCol = startCol;
        noOfRows = room.length;
        noOfCols = room[0].length;
    }

    private boolean isWithinBoundaries(int row, int col){
        return row >= 0 && row < noOfRows && col >= 0 && col < noOfCols;
    }
    @Override
    public boolean move() {
        int nextRow = currentRow + RobotRoomCleaner.directions.get(robotDirection).row;
        int nextCol = currentCol + RobotRoomCleaner.directions.get(robotDirection).col;
        if( !isWithinBoundaries(nextRow,nextCol) || room[nextRow][nextCol] == 0){
            return false;
        }
        System.out.println("("+nextRow + "," + nextCol +") -->");
        currentRow = nextRow;
        currentCol = nextCol;
        return true;
    }

    @Override
    public void turnLeft() {
        robotDirection = (robotDirection - 1) % 4;
    }

    @Override
    public void turnRight() {
        robotDirection = (robotDirection + 1) % 4;
    }

    @Override
    public void clean() {
        room[currentRow][currentCol]++;

    }

    public void displayRoom(){
        System.out.println("[");
        for (int[] row : room) {
            for (int col : row) {
                System.out.print((col == 0 ? "O" : col)  + " ");
            }
            System.out.println("");
        }
        System.out.println("]");
    }



}