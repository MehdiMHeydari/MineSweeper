package com.example.minesweeper;

public class BoardLogic {
    private int board[][];
    int numSafe;
    boolean gameOver;
    int rows;
    int collumns;
    double percentmines;


    public BoardLogic(int row, int collumn, String difficulty) {


        rows = row;
        collumns = collumn;
        board = new int[rows][collumns];
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
        numSafe = 0;
        for (int i = 0; i < board.length; i++){
           for(int j = 0; j< board[0].length; j++){
               if(board[i][j]< 0 && board[i][j] == -9){
                   numSafe ++;
               }
           }
        }
        return numSafe;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public int getSpace(int row, int col) {
        if(board[row][col] == 9 ){
            return 9;
        } else if (board[row][col]< 0){
            return -1;
        } else {
            return board[row][col];
        }
    }

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < collumns; j++) {
                board[i][j] = -10;
                addMines(i, j);

            }
        }
        populateBoard();
    }

    private void addMines(int i, int j) {
        double x = Math.random();

        if (x < percentmines) {
            board[i][j] = -9;
        }
    }

    private void populateBoard() {
        for (int h = 0; h < board.length; h++) {
            for (int m = 0; m < board[1].length; m++) {
                if (board[h][m] == -9) {
                    for (int l = h -1; l <= h+ 1; l++){
                        for (int u = m -1 ; u <= m +1; u++){
                            try{
                                if(board[l][u] != -9){
                                    if(board[l][u] == -10){
                                        board[l][u] = 0;
                                    }
                                    board[l][u]--;
                                }
                            }catch(ArrayIndexOutOfBoundsException e){
                            }
                            }
                        }

                    }
                System.out.println(board[h][m]);
                }
            }

        }



    public boolean pickSpace(int i, int j) {

        System.out.println(board[i][j]);
        if (board[i][j] == -9){
            gameOver = true;
        }
        if (isGameOver()){
            return false;
        }

        if (board[i][j] != -10) {
            if (board[i][j] < 0) {
                board[i][j] *= -1;
            }

        } else if( board[i][j] == -10) {
            System.out.println("in loop");
            for (int h = i - 1; h < i + 1; h++) {
                    for (int m = j - 1; m < j + 1; m++) {
                        try {
                            if(board[h][m] == -10){
                                board[h][m] = 0;
                                pickSpace(h,m);
                            }
                        }catch (IndexOutOfBoundsException c) {
                        }
                    }
                }
            }
        return true;
        }

    }

