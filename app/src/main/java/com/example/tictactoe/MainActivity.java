package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    /*TODO
       Add app icon
    */
    int oScore = 0;
    int xScore = 0;
    int[] occupiedPlayer = {0, 0, 0, 0, 0, 0, 0, 0, 0}; //0 --> NO PLAYER
    int activePlayer = 1; // 1 --> X, -1 --> O
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean continueGame = true;
    String W = "no one!";
    int a = 0; //  To see if displayWinMsg has been called or not


    public void tap(View view)
    {
        ImageView apv = (ImageView) findViewById(R.id.activePlayerView);
        ImageView xoro = (ImageView) view;
        Log.i("ID", "" + xoro.getTag());
        int temp = (occupiedPlayer[Integer.parseInt(xoro.getTag().toString())]);
        Log.i("Occ", "" + temp);
        if(temp == 0 && continueGame) // Add X or O to empty slot
            {
            if (activePlayer == 1)
            {
                xoro.setImageResource(R.drawable.x);
                activePlayer = -1;
                occupiedPlayer[Integer.parseInt(xoro.getTag().toString())] = 1;
                apv.setImageResource(R.drawable.o);
            }
            else if (activePlayer == -1)
            {
                xoro.setImageResource(R.drawable.o);
                activePlayer = 1;
                occupiedPlayer[Integer.parseInt(xoro.getTag().toString())] = -1;
                apv.setImageResource(R.drawable.x);
            }
            xoro.setTranslationY(-2000);
            xoro.animate().translationYBy(2000).rotationBy(720).setDuration(400);

        TextView xS = (TextView) findViewById(R.id.Xview);
        TextView oS = (TextView) findViewById(R.id.Oview);
        Log.i("Occupancy", "" + Arrays.toString(occupiedPlayer));
        for (int[]wp : winningPositions) // Check if updated occupiedPlayers correspond to any winning positions
            {
                if(occupiedPlayer[wp[0]] == occupiedPlayer[wp[1]] && occupiedPlayer[wp[1]] == occupiedPlayer[wp[2]] && occupiedPlayer[wp[0]] != 0)
                {
                    continueGame = false;
                    if (activePlayer == 1)
                    {
                       W = "O";
                       oScore++;
                       oS.setText("O: "+oScore);
                     }
                     else if (activePlayer == -1)
                    {
                        W = "X";
                        xScore++;
                        xS.setText("X: "+xScore);
                    }
                     Log.i("Winner:", W);
                    if (a == 0)
                    {
                        displayWinMsg();
                        a = 1;
                    }
                }
            }
            }
        if(allSpotsAreOccupied() && a==0)
        {
            displayNoMsg();
            a = 1;
        }
    }

    public boolean allSpotsAreOccupied()
    {
        for(int i:occupiedPlayer)
        {
            if(i==0)
                return false;
        }
        return true;
    }
    public void displayWinMsg()
    {
        ImageView apv = (ImageView) findViewById(R.id.activePlayerView);
        apv.setImageResource(0);
        TextView winner = (TextView) findViewById(R.id.winnerView);
        winner.setTranslationY(2000);
        winner.setText("The winner of the match is " + W);
        winner.animate().translationYBy(-2000).setDuration(650);

        Button myButton = (Button) findViewById(R.id.button);
        myButton.setTranslationX(-2000);
        myButton.animate().alpha(255);
        myButton.animate().translationXBy(2000).setDuration(650);
        myButton.setEnabled(true);
    }
    public void displayNoMsg()
    {
        ImageView apv = (ImageView) findViewById(R.id.activePlayerView);
        apv.setImageResource(0);
        TextView winner = (TextView) findViewById(R.id.winnerView);
        winner.setTranslationY(2000);
        winner.setText("There is no winner! Try again!");
        winner.animate().translationYBy(-2000).setDuration(650);

        Button myButton = (Button) findViewById(R.id.button);
        myButton.setTranslationX(-2000);
        myButton.animate().alpha(255);
        myButton.animate().translationXBy(2000).setDuration(650);
        myButton.setEnabled(true);
    }
    public void initializer(View view)
    {
        ImageView apv = (ImageView) findViewById(R.id.activePlayerView);
        apv.setImageResource(R.drawable.x);
        occupiedPlayer = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        continueGame = true;
        activePlayer = 1;
        W = "";
        TextView winner = (TextView) findViewById(R.id.winnerView);
        winner.setText("");
        Button myButton = (Button) findViewById(R.id.button);
        myButton.animate().alpha(0).setDuration(10);
        myButton.setEnabled(false);
        a = 0;

        ImageView[] reset = {findViewById(R.id.One),(ImageView) findViewById(R.id.Two),(ImageView) findViewById(R.id.Three),(ImageView) findViewById(R.id.Four),(ImageView) findViewById(R.id.Five),(ImageView) findViewById(R.id.Six),(ImageView) findViewById(R.id.Seven),(ImageView) findViewById(R.id.Eight),(ImageView) findViewById(R.id.Nine)};
        for(int i = 0; i < 9; i++)
        {
            reset[i].setImageResource(0);
        }
    }
    public void resetScores(View view)
    {
        xScore = 0;
        oScore = 0;
        TextView xS = (TextView) findViewById(R.id.Xview);
        TextView oS = (TextView) findViewById(R.id.Oview);
        xS.setText("X: "+xScore);
        oS.setText("O: "+oScore);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
