package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class GameBoardActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    int rows;
    int collumns;
    Chronometer cmTimer;
    MainActivity main;
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
        Button[][] buttons = new Button [rows][collumns];

        //making game board
        linearLayout = findViewById(R.id.gameboard);
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
                        //main.updateGraphics();
                    }
                });
                buttons[j][h] = b;
                row.addView(b);
            }


        }



        //puts the percent of the mines depending on diffuclty
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

}
