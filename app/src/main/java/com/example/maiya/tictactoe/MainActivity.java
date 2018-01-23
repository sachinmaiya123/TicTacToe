package com.example.maiya.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 for x 1 for y

    boolean isActive= true;
    int active = 0;
    int[][] winState = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    public void playAgain(View view){
        isActive = true;
        LinearLayout ll = (LinearLayout)findViewById(R.id.winnerLayout);
        ll.setVisibility(View.INVISIBLE);
        active = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridlayout.getChildCount(); i++){
            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }
    }

    public void onDrop(View view){

        ImageView iv = (ImageView)view;
        int counter = Integer.parseInt(iv.getTag().toString());

        if(gameState[counter] == 2 && isActive) {
            gameState[counter] = active;
            iv.setTranslationY(-1000f);
            if (active == 0) {
                //gameState[counter] = active;
                        iv.setImageResource(R.drawable.ticxp);
                active = 1;
            } else {
              //  gameState[counter] = active;
                iv.setImageResource(R.drawable.ticop);
                active = 0;
            }
            iv.animate().rotation(360f).translationYBy(1000f).setDuration(1000);

            for(int[] win : winState){
                if(gameState[win[0]] == gameState[win[1]] &&
                        gameState[win[1]] == gameState[win[2]] &&
                          gameState[win[0]] != 2){
                    isActive = false;
                    String msg = "O";
                    if(gameState[win[0]] == 0){
                        msg = "X";
                    }
                    TextView tv = (TextView)findViewById(R.id.textViewWinner);
                    tv.setText(msg+" Has Won");
                  //  Toast.makeText(MainActivity.this,""+gameState[win[0]],Toast.LENGTH_LONG).show();
                    LinearLayout ll = (LinearLayout)findViewById(R.id.winnerLayout);
                    ll.setVisibility(View.VISIBLE);
                } else {
                    boolean isDraw = true;
                    for(int draw: gameState){
                        if(draw == 2)
                            isDraw=false;
                    }
                    if(isDraw){
                        TextView tv = (TextView)findViewById(R.id.textViewWinner);
                        tv.setText("Its Draw");
                        //  Toast.makeText(MainActivity.this,""+gameState[win[0]],Toast.LENGTH_LONG).show();
                        LinearLayout ll = (LinearLayout)findViewById(R.id.winnerLayout);
                        ll.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
