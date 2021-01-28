package com.keerthi.leetcode.recursion.backtracking;

public class NQueensProblem {
    int noOfWaysToArrangeAllQueens;
    int size;
    int noOfQueensPlaced;
    int[][] chessboard;

    void initialise(int n){
        size = n;
        chessboard = new int[n][n];
    }

    boolean is_not_under_attack(int row, int col){
        return chessboard[row][col] == 0;
    }

    void place_queen(int row, int col){
        chessboard[row][col] = -1;
        noOfQueensPlaced++;
        for (int i = 0; i < size; i++) {
            if(i != col){
                chessboard[row][i]++;
            }
        }
        for (int i = 0; i < size; i++) {
            if(i != row){
                chessboard[i][col]++;
            }
        }
        //Handle Diagonally
        for (int i = row+1, j = col+1; i < size && j < size; i++, j++) {
            chessboard[i][j]++;
        }

        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            chessboard[i][j]++;
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
            chessboard[i][j]++;
        }

        for (int i = row + 1, j = col - 1; i < size && j >= 0; i++, j--) {
            chessboard[i][j]++;
        }
    }

    void remove_queen(int row, int col){
        chessboard[row][col] = 0;
        noOfQueensPlaced--;
        for (int i = 0; i < size; i++) {
            if(i != col){
                chessboard[row][i]--;
            }
        }
        for (int i = 0; i < size; i++) {
            if(i != row){
                chessboard[i][col]--;
            }
        }
        //Handle Diagonally
        for (int i = row+1, j = col+1; i < size && j < size; i++, j++) {
            chessboard[i][j]--;
        }

        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            chessboard[i][j]--;
        }

        for (int i = row-1, j = col+1; i >= 0 && j < size; i--, j++) {
            chessboard[i][j]--;
        }

        for (int i = row+1, j = col-1; i < size && j >= 0; i++, j--) {
            chessboard[i][j]--;
        }
    }

    public int totalNQueens(int n) {
        initialise(n);
        totalNQueensHelper(0);
        return noOfWaysToArrangeAllQueens;
    }

    void displayChessBoard(){
        System.out.println("[");
        for (int[] row : chessboard) {
            for (int col : row) {
                System.out.print(col < 0 ? "Q " : "x"  + " ");
            }
            System.out.println("");
        }
        System.out.println("]");
    }

    public void totalNQueensHelper(int row) {
        for (int col = 0; col < size; col++) {
            if(is_not_under_attack(row,col)){
                place_queen(row,col);
                if(row + 1 == size && noOfQueensPlaced == size){
                    noOfWaysToArrangeAllQueens++;
                    displayChessBoard();
                }else {
                   totalNQueensHelper(row + 1);
                }
                remove_queen(row,col);
            }
        }
        return ;
    }

    public static void main(String[] args) {
        NQueensProblem solution = new NQueensProblem();
        solution.totalNQueens(4);
    }
}
