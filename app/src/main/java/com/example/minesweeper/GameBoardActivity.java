package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameBoardActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    int rows;
    int collumns;
    Chronometer cmTimer;
    MainActivity main;
    BoardLogic logic;
    Button[][] buttons;
    TextView numopen;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);

        main = new MainActivity();

        //pulling data from intent
        Intent intent = getIntent();
        rows = Integer.parseInt(intent.getStringExtra("Rows"));
        collumns = Integer.parseInt(intent.getStringExtra("Collumns"));
        String difficulty = intent.getStringExtra("Difficulty");


        //timer in top right
        cmTimer = (Chronometer) findViewById(R.id.cmTimer);
        cmTimer.start();

        //make 2d array holding buttons
        buttons = new Button [rows][collumns];

        logic = new BoardLogic(rows, collumns, difficulty);
        logic.reset();

        //making game board
        linearLayout = findViewById(R.id.gameboard);

        //linking textview variable
        numopen = findViewById(R.id.numspaces);




        //first for loop puts in a linear layout in each rows
        for (int j = 0; j < rows; j++) {
            LinearLayout row = new LinearLayout(this);
            LinearLayout.LayoutParams linparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(linparams);
            linearLayout.addView(row);
            //second for loops puts a button for each collumn in that row
            for(int h = 0; h< collumns; h++){
                Button b = new Button(this);


                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                b.setLayoutParams(params);
                b.setTag(j+h);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button now = (Button)v;
                        int tag = (Integer)now.getTag();
                        logic.pickSpace(tag/rows, tag % collumns);
                        System.out.println("getspace = " + logic.getSpace(tag/rows,tag% collumns));

                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                updateGraphics();
                            }
                        });
                    }
                });
                buttons[j][h] = b;
                row.addView(b);
            }


        }



        //puts the percent of the mines depending on difficlty
        double percentmines = 0;
        if (difficulty.equals("easy")){
            percentmines = .15;
        }
        if (difficulty.equals("medium")){
            percentmines = .25;
        }
        if (difficulty.equals("hard")){
            percentmines = .35;
        }



    }


    public void updateGraphics() {

        numopen.setText(String.valueOf(logic.numOpen()));
        if(logic.isGameOver()){
            cmTimer.stop();
            if (logic.numOpen() == 0){
                numopen.setText("YOU WIN!");
            }else{
                numopen.setText("YOU LOST :(");
            }
        }


        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[1].length; j++) {
                if(logic.getSpace(i,j) >= 0) {
                    buttons[i][j].setText(String.valueOf(logic.getSpace(i, j)));
                }
            }
        }

    }

}
