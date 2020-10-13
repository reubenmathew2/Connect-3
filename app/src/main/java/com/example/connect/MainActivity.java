package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; //red, 1: yellow, 2: empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean t = false;
    public void dropIn (View v) {
        boolean finish = true;
        ImageView counter = (ImageView) v;
        Log.i("Tag", counter.getTag().toString());

        boolean flag = false;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && !t) {
            counter.setTranslationY(-1500);
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.red);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.yellow);
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            int i = 0, w = 2, j = 0;
            for (i = 0; i < 8; i++) {

                if ((gameState[winningPositions[i][j]] == gameState[winningPositions[i][j + 1]]) && (gameState[winningPositions[i][j + 2]] == gameState[winningPositions[i][j + 1]]) && (gameState[winningPositions[i][j]] != 2)) {
                    w = gameState[winningPositions[i][j]];
                    flag = true;
                    break;
                }

            }
            for (int k = 0; k < 9; k++) {
                if (gameState[k] == 2)
                    finish = false;
            }
            String msg;
            if (w == 0)
                msg = "Red";
            else if (w == 1)
                msg = "Yellow";
            else
                msg = "Neither Red nor Yellow";
            Log.i("Won", Boolean.toString(flag));
            Log.i("Winner", Integer.toString(w));
            Log.i("Finish", Boolean.toString(finish));
            TextView win = (TextView) findViewById(R.id.textView);
            if (finish || flag) {
                //Toast.makeText(this, msg + "  Won!", Toast.LENGTH_LONG).show();
                win.setText(msg + "  Won!");
                win.setVisibility(View.VISIBLE);
                t = true;
            }
        }
    }

    public void playAgain (View v) {

        TextView win = (TextView) findViewById(R.id.textView);
        win.setVisibility(View.INVISIBLE);
        ImageView c1 = (ImageView) findViewById(R.id.c1);
        c1.setImageDrawable(null);
        ImageView c2 = (ImageView) findViewById(R.id.c2);
        c2.setImageDrawable(null);
        ImageView c3 = (ImageView) findViewById(R.id.c3);
        c3.setImageDrawable(null);
        ImageView c4 = (ImageView) findViewById(R.id.c4);
        c4.setImageDrawable(null);
        ImageView c5 = (ImageView) findViewById(R.id.c5);
        c5.setImageDrawable(null);
        ImageView c6 = (ImageView) findViewById(R.id.c6);
        c6.setImageDrawable(null);
        ImageView c7 = (ImageView) findViewById(R.id.c7);
        c7.setImageDrawable(null);
        ImageView c8 = (ImageView) findViewById(R.id.c8);
        c8.setImageDrawable(null);
        ImageView c9 = (ImageView) findViewById(R.id.c9);
        c9.setImageDrawable(null);

        for(int i = 0; i < gameState.length; i++)
            gameState[i] = 2;
        activePlayer = 0; //red, 1: yellow, 2: empty
        t = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}