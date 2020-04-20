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

    //finds number of open covered spaces
    public int numOpen() {
        numSafe = 0;
        for (int i = 0; i < board.length; i++){
           for(int j = 0; j< board[0].length; j++){
               if(board[i][j]< 0 && board[i][j] != -9){
                   numSafe ++;
               }
           }
        }
        return numSafe;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    //returns space you clicked on
    public int getSpace(int row, int col) {

        //makes  sure you can still play
        if (gameOver != true ) {
            if (board[row][col] < 0) {
                return -1;
            } else if (board[row][col] == -10 || board[row][col] == 10) {
                return 0;
            }
            else{ return board[row][col];
            }
        }
        //if game is over returns actual values  to be displayed on board
        else{
            if (board[row][col] == -10 || board[row][col] == 10) {
                return 0;
            }else if (board[row][col] < 0) {
                board[row][col] *= -1;
                return board[row][col];
            }else{
                return board[row][col];
            }

        }
    }

    //full board reset to start the  game from scratch
    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < collumns; j++) {
                board[i][j] = -10;
                addMines(i, j);

            }
        }
        //after adding mines looks for values of the open spaces
        populateBoard();
    }

    //adds mines depending on diffiuclty
    private void addMines(int i, int j) {
        double x = Math.random();

        if (x < percentmines) {
            board[i][j] = -9;
        }
    }

    //
    private void populateBoard() {
        //checks all of the board
        for (int h = 0; h < board.length; h++) {
            for (int m = 0; m < board[1].length; m++) {
                //looks at each board spot to see if it is a mine
                if (board[h][m] == -9) {
                    //if its a mine it goes to all the surrounding pieces and subbrtaces 1 to represent that its next to a mine
                    for (int l = h -1; l <= h+ 1; l++){
                        for (int u = m -1 ; u <= m +1; u++){
                            try{
                                if(board[l][u] != -9){
                                    if(board[l][u] == -10){
                                        board[l][u] =  0;
                                    }
                                    board[l][u]--;
                                }
                                //makes sure that even if you are going out of the board you wont have an error
                            }catch(ArrayIndexOutOfBoundsException e){
                            }
                            }
                        }

                    }
                }
            }

        }



    public boolean pickSpace(int i, int j) {

        //if you click on a mine sets all board values to normal and notifies you that the game is over
        if (board[i][j] == -9){
            for (int h = 0; i < rows; i++) {
                for (int m = 0; j < collumns; j++) {
                    if(board[h][m] < 0){
                        board[h][m] *= -1;
                    }
                }
            }
            gameOver = true;
        }

        if (isGameOver()){
            return false;
        }

        //if open spot it sets it to be visable
        if (board[i][j] != -10) {
            if (board[i][j] < 0) {
                board[i][j] *= -1;
            }

        } //if its a zero checks all the surrounding spots and reveals them
        else if( board[i][j] == -10) {
            board[i][j] = 0;
            for (int h = i - 1; h <= i + 1; h++) {
                    for (int m = j - 1; m <= j + 1; m++) {
                        try {
                            if(board[h][m] == -10){
                                pickSpace(h,m);
                            }else{
                                if (board[h][m] < 0){
                                    board[h][m]*=-1;
                                }
                            }
                        }catch (IndexOutOfBoundsException c) {
                        }
                    }
                }
            }
        return true;
        }

    }

