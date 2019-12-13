package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0-O
    //1-X
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    /*
    gameState
    0-O
    1-X
    2-NULL
     */

    boolean activeState = true;
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!activeState) {
            reset(view);
        } else {
            if (gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 1) {
                    activePlayer = 0;
                    img.setImageResource(R.drawable.cross);
                    TextView text = findViewById(R.id.status);
                    text.setText("Android's Turn");

                } else {
                    activePlayer = 1;
                    img.setImageResource(R.drawable.zero);
                    TextView text = findViewById(R.id.status);
                    text.setText("Apple's Turn");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
        }
        // Checking for win
        for (int[] winposs : winPos) {
            if (gameState[winposs[0]] == gameState[winposs[1]] && gameState[winposs[2]] == gameState[winposs[1]] && gameState[winposs[0]] != 2) {

                if (gameState[winposs[0]] == 0) {
                    TextView text = findViewById(R.id.status);
                    text.setText("Android has Won");
                    activeState = false;
                } else {
                    TextView text = findViewById(R.id.status);
                    text.setText("Apple has Won");
                    activeState = false;
                }
            }
        }
        //Checking for a tie
        if (activeState) {
            boolean flag = false;
            for (int i = 0; i < 9; i++) {
                if (gameState[i] == 2) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                reset(view);
            }
        }
    }

    public void reset(View view) {
        for (int i = 0; i < 9; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        activeState = true;
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        TextView text = findViewById(R.id.status);
        text.setText("Android's turn");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
