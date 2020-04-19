package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar seek;
    private TextView TV;
    private SeekBar seek2;
    private TextView TV2;
    private String Difficulty;
    private TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        error = findViewById(R.id.error);
        //seekbar wuth row values
        seek = (SeekBar) findViewById(R.id.rowbar);
        TV = (TextView) findViewById(R.id.NumberRows);
        TV.setText(Integer.toString(seek.getProgress()));
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            //gets current value when changed and assigns it to a variable
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    pval = 1;
                }
                else{
                    pval = progress;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TV.setText(Integer.toString(pval));
            }
        });

        //seekbar with collumn values
        seek2 = (SeekBar) findViewById(R.id.collumnbar);
        TV2 = (TextView) findViewById(R.id.NumberCollumns);
        TV2.setText(Integer.toString(seek.getProgress()));
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval2 = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    pval2 = 1;
                }
                else{
                    pval2 = progress;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TV2.setText(Integer.toString(pval2));
            }
        });
        //setting up all of the buttons
        int togameboard = R.id.playbutton;
        Button registerbutton = findViewById(togameboard);
        registerbutton.setTag(togameboard);
        registerbutton.setOnClickListener(new HandleButton());

        int easy = R.id.easy;
        Button easybutton = findViewById(easy);
        easybutton.setTag(togameboard);
        easybutton.setOnClickListener(new easyButton());

        int medium = R.id.medium;
        Button mediumbutton = findViewById(medium);
        mediumbutton.setTag(togameboard);
        mediumbutton.setOnClickListener(new mediumButton());

        int hard = R.id.hard;
        Button hardbutton = findViewById(hard);
        hardbutton.setTag(togameboard);
        hardbutton.setOnClickListener(new hardButton());

    }

    //setting difficulty based on the button pressed
    private class easyButton implements View.OnClickListener {

        @Override
        public void onClick(View e) {
            Difficulty = "easy";
        }

    }

    private class hardButton implements View.OnClickListener {

        @Override
        public void onClick(View h) {
            Difficulty = "hard";
        }

    }
    private class mediumButton implements View.OnClickListener {

        @Override
        public void onClick(View m) {
            Difficulty = "medium";
        }

    }




    private class HandleButton implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //puts all the data into an intent and starts the gameboard activity
            if (Difficulty == null){
                error.setText("PLEASE ENTER A DIFFICULTY!");
               return;
            }
            Intent i = new Intent(MainActivity.this, GameBoardActivity.class);
            TextView Row = (TextView) findViewById(R.id.NumberRows);
            TextView Collumn = (TextView) findViewById(R.id.NumberCollumns);
            i.putExtra("Rows", Row.getText());
            i.putExtra("Collumns", Collumn.getText());

            i.putExtra("Difficulty", Difficulty);

                startActivity(i);

        }

    }



}
