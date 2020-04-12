package com.example.minesweeper;

public class BoardLogic {
    private int board[][];
    int numSafe;
    boolean gameOver;
    int rows;
    int collumns;
    double percentmines;


    public BoardLogic(int rows, int collumns, String difficulty) {
        board = new int[rows][collumns];
        rows = rows;
        collumns = collumns;
        percentmines = 0;
        if (difficulty.equals("easy")) {
            percentmines = .15;
        }
        if (difficulty.equals("medium")) {
            percentmines = .25;
        }
        if (difficulty.equals("hard")) {
            percentmines = .35;
        }

    }

    public int numRows() {
        return rows;
    }

    public int numCollumns() {
        return collumns;
    }

    public int numSpaces() {
        return rows * collumns;
    }

    public int numOpen() {
        return numSafe;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getSpace(int row, int col) {
        return board[row][col];
    }

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < collumns; j++) {
                board[i][j] = 0;
                addMines(i, j);
                if (board[i][j] != 9) {
                    populateBoard(i, j);
                }

            }
        }
    }

    private void addMines(int i, int j) {
        double x = Math.random();
        if (x < percentmines) {
            board[i][j] = 9;
        }
    }

    private void populateBoard(int i, int j) {
        int tally = 0;
        for (int h = i - 1; h < i + 1; h++) {
            for (int m = j; m < j + 1; m++) {
                if (board[h][m] == 9) {
                    tally++;
                }
            }
        }
        board[i][j] = -1 * tally;
    }

    public void pickSpace(int i, int j) {
        if (board[i][j] != 0) {
            board[i][j] *= -1;
        } else {
            for (int h = i - 1; h < i + 1; h++) {
                for (int m = j; m < j + 1; m++) {
                    board[h][m] *= -1;
                }

            }

        }
    }
}
