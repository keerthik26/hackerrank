package com.keerthi.leetcode.recursion.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolver {
    List<Set<Integer>> rowWise = new ArrayList<>();
    List<Set<Integer>> colWise = new ArrayList<>();
    List<Set<Integer>> gridWise = new ArrayList<>();
    char[][] board;
    boolean isSolved = false;


    int getGridIndex(Cell cell){
       if(cell.row >= 0 && cell.row < 3 && cell.col >= 0 && cell.col < 3){
           return 0;
       }
       if(cell.row >= 0 && cell.row < 3 && cell.col >= 3 && cell.col < 6){
           return 1;
       }
       if(cell.row >= 0 && cell.row < 3 && cell.col >= 6 && cell.col < 9){
           return 2;
       }
       if(cell.row >= 3 && cell.row < 6 && cell.col >= 0 && cell.col < 3){
           return 3;
       }
       if(cell.row >= 3 && cell.row < 6 && cell.col >= 3 && cell.col < 6){
           return 4;
       }
       if(cell.row >= 3 && cell.row < 6 && cell.col >= 6 && cell.col < 9){
           return 5;
       }
       if(cell.row >= 6 && cell.row < 9 && cell.col >= 0 && cell.col < 3){
           return 6;
       }
       if(cell.row >= 6 && cell.row < 9 && cell.col >= 3 && cell.col < 6){
           return 7;
       }
       if(cell.row >= 6 && cell.row < 9 && cell.col >= 6 && cell.col < 9){
           return 8;
       }
       return -1;
    }

    void initialize(){
        for (int i = 0; i < 9; i++) {
            rowWise.add(new HashSet<>());
            colWise.add(new HashSet<>());
            gridWise.add(new HashSet<>());
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if(board[row][col] != '.'){
                    rowWise.get(row).add(Integer.valueOf(board[row][col] - '0'));
                    colWise.get(col).add(Integer.valueOf(board[row][col] - '0'));
                    gridWise.get(getGridIndex(new Cell(row,col))).add(Integer.valueOf(board[row][col] - '0'));
                }
            }
        }
    }

    public void solveSudoku(char[][] board) {
        this.board = board;
        initialize();
        Cell startCell = new Cell(0, 0);
        backtrack(board[startCell.row][startCell.col] == '.' ? startCell : getNextEmptyCell(startCell));

    }

    void backtrack(Cell current){

        for (int i = 1; i < 10; i++) {
            if(canBePlaced(i, current)){
                place(i,current);
                //if last cell return
                Cell nextEmptyCell = getNextEmptyCell(current);
                if(nextEmptyCell == null) {
                    isSolved = true;
                    return;
                }else {
                    backtrack(nextEmptyCell);
                }
                remove(i,current);
            }
        }
    }



    private void remove(int num, Cell cell) {
        if(!isSolved) {
            board[cell.row][cell.col] = '.';
            rowWise.get(cell.row).remove(num);
            colWise.get(cell.col).remove(num);
            gridWise.get(getGridIndex(cell)).remove(num);
        }
    }

    private Cell getNextEmptyCell(Cell current) {
        Cell nextCell = getNextCell(current);

        while (nextCell != null && board[nextCell.row][nextCell.col] != '.'){
            nextCell = getNextCell(nextCell);
        }
        return nextCell;
    }

    private Cell getNextCell(Cell current) {
        int newRow = current.row;
        int newCol;
        if(current.col < 8){
             newCol = current.col + 1;
        }else {
             newRow = current.row + 1;
             newCol = 0;
        }
        return newRow < 9 ? new Cell(newRow,newCol) : null;
    }

    private void place(int num, Cell cell) {
        board[cell.row][cell.col] = (char) (num + '0');
        rowWise.get(cell.row).add(num);
        colWise.get(cell.col).add(num);
        gridWise.get(getGridIndex(cell)).add(num);

    }

    private boolean canBePlaced(int num, Cell current) {

        return !isPresentInRow(num,current.row) && !isPresentInCol(num,current.col) && !isPresentInGrid(num,current);

    }

    private boolean isPresentInRow(int num, int row){
        return rowWise.get(row).contains(num);
    }

    private boolean isPresentInCol(int num, int col){
        return colWise.get(col).contains(num);
    }

    private boolean isPresentInGrid(int num, Cell current){
        return gridWise.get(getGridIndex(current)).contains(num);
    }

    public static void main(String[] args) {
       SudokuSolver solver = new SudokuSolver();
       char[][] question = { {'.','.','.','.','5','.','.','.','.'},{'.','.','.','7','.','.','1','.','2'},{'.','4','.','.','9','.','.','.','8'},
            {'.','6','.','4','3','.','8','9','.'},{'7','.','.','.','.','.','.','.','.'},
            {'.','.','.','8','.','.','.','.','6'},
            {'2','.','4','.','.','.','.','.','.'},
            {'1','5','.','.','.','.','.','.','.'},
            {'.','3','.','.','.','9','.','5','4'}
        };
       solver.solveSudoku(question);
       solver.displayBoard();
    }

    public void displayBoard(){
        System.out.println("[");
        for (char[] row : board) {
            for (char col : row) {
                System.out.print(col  + " ");
            }
            System.out.println("");
        }
        System.out.println("]");
    }
}

class Cell{
    int row;
    int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
