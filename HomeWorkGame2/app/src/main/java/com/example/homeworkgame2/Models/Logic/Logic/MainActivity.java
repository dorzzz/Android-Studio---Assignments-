package com.example.homeworkgame2.Models.Logic.Logic;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;


import com.example.homeworkgame2.Models.Logic.Logic.Logic.GameManager;
import com.example.homeworkgame2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private AppCompatImageView main_IMG_background;

    private ShapeableImageView[] main_IMG_hearts;

    private ShapeableImageView[] main_IMG_obstacles;

    private ShapeableImageView[] main_IMG_motorcycle;

    private ShapeableImageView[] main_IMG_explosive;

    private FloatingActionButton[] floatingActionButtons;


    private final ShapeableImageView[][] board = new ShapeableImageView[4][3];
    private Random randomNumber;

    private GameManager gm;

    private Vibrator v;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        gm = new GameManager(main_IMG_hearts.length);

        randomNumber = new Random();


        //first launch of game
        initBoard();
        contentPlayer();
        contentBoard();

    }

    public void randomStart() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int rand = 0;
                rand = randomNumber.nextInt(3);
                board[0][rand].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 5000);


            }
        };
        handler.post(runnable);
    }

    public void moveColumn1() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int index = 1;

            @Override
            public void run() {

                if (index == 3 && board[3][0].getVisibility() == View.VISIBLE && board[2][0].getVisibility() == View.VISIBLE) {
                    main_IMG_explosive[0].setVisibility(View.VISIBLE);
                    //get hit , minus heart
                    gm.getHit();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        v.vibrate(500);
                    }

                    //toast

                    Toast.makeText(getBaseContext() , "ouch" , Toast.LENGTH_SHORT).show();
                    board[3][0].setVisibility(View.INVISIBLE);
                }

                if (index != 3 && main_IMG_explosive[0].getVisibility() == View.VISIBLE) {
                    main_IMG_explosive[0].setVisibility(View.INVISIBLE);
                    board[3][0].setVisibility(View.VISIBLE);
                }

                if (index == 3) {
                    board[2][0].setVisibility(View.INVISIBLE);
                    index = 1;
                }

                board[index][0].setVisibility(View.VISIBLE);
                board[index - 1][0].setVisibility(View.INVISIBLE);
                index++;

                handler.postDelayed(this, 3000);

            }
        };

        handler.post(runnable);
//        handler.removeCallbacks(runnable);
    }

    public void moveColumn2() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            int index = 1;

            @Override
            public void run() {

                if (index == 3 && board[3][1].getVisibility() == View.VISIBLE && board[2][1].getVisibility() == View.VISIBLE) {
                    main_IMG_explosive[1].setVisibility(View.VISIBLE);
                    //get hit , minus heart
                    gm.getHit();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        v.vibrate(500);
                    }
                    //toast
                    Toast.makeText(getBaseContext() , "you ok? " , Toast.LENGTH_SHORT).show();

                    board[3][1].setVisibility(View.INVISIBLE);
                }

                if (index != 3 && main_IMG_explosive[1].getVisibility() == View.VISIBLE) {
                    main_IMG_explosive[1].setVisibility(View.INVISIBLE);
                    board[3][1].setVisibility(View.VISIBLE);
                }

                if (index == 3) {
                    board[2][1].setVisibility(View.INVISIBLE);
                    index = 1;
                }

                board[index][1].setVisibility(View.VISIBLE);
                board[index - 1][1].setVisibility(View.INVISIBLE);
                index++;

                handler.postDelayed(this, 2000);

            }
        };

        handler.post(runnable);
//        handler.removeCallbacks(runnable);
    }

    public void moveColumn3() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int index = 1;

            @Override
            public void run() {

                if (index == 3 && board[3][2].getVisibility() == View.VISIBLE && board[2][2].getVisibility() == View.VISIBLE) {
                    main_IMG_explosive[2].setVisibility(View.VISIBLE);
                    //get hit , minus heart
                    gm.getHit();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        v.vibrate(500);
                    }
                    //get toast message
                    Toast.makeText(getBaseContext() , "I`ll call 911" , Toast.LENGTH_SHORT).show();
                    board[3][2].setVisibility(View.INVISIBLE);
                }

                if (index != 3 && main_IMG_explosive[2].getVisibility() == View.VISIBLE) {
                    main_IMG_explosive[2].setVisibility(View.INVISIBLE);
                    board[3][2].setVisibility(View.VISIBLE);
                }

                if (index == 3) {
                    board[2][2].setVisibility(View.INVISIBLE);
                    index = 1;
                }


                board[index][2].setVisibility(View.VISIBLE);
                board[index - 1][2].setVisibility(View.INVISIBLE);
                index++;

                handler.postDelayed(this, 2500);

            }

        };
        handler.post(runnable);
//        handler.removeCallbacks(runnable);

    }


    public void obstaclesMovement() {

        randomStart();

        if (board[0][0].getVisibility() == View.VISIBLE) {
            moveColumn1();
        }

        if (board[0][1].getVisibility() == View.VISIBLE) {
            moveColumn2();
        }

        if (board[0][2].getVisibility() == View.VISIBLE) ;
        {
            moveColumn3();

        }


    }

    public void playerMovementListener(int cell) {
        for (FloatingActionButton fab : floatingActionButtons) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fab == findViewById(R.id.fab_BTN_right)) {
                        if (cell == 0) {
                            return;
                        }

                        if (cell == 1) {
                            board[3][0].setVisibility(View.VISIBLE);
                            board[3][1].setVisibility(View.INVISIBLE);
                            return;


                        }

                        if (cell == 2) {
                            board[3][1].setVisibility(View.VISIBLE);
                            board[3][2].setVisibility(View.INVISIBLE);
                            return;
                        }

                    }
                    if (fab == findViewById(R.id.fab_BTN_left)) {
                        if (cell == 2) {
                            return;
                        }
                        if (cell == 1) {
                            board[3][2].setVisibility(View.VISIBLE);
                            board[3][1].setVisibility(View.INVISIBLE);
                            return;
                        }

                        if (cell == 0) {
                            board[3][1].setVisibility(View.VISIBLE);
                            board[3][0].setVisibility(View.INVISIBLE);
                            return;
                        }


                    }
                }
            });
        }
    }

    public int playerLocationFinder() {

        int row = 3;

        int index = 0;


        for (int i = row; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getVisibility() == View.VISIBLE) {
                    index = j;
                }
            }
        }

        return index;

    }


    public void findViews() {
        main_IMG_background = findViewById(R.id.main_IMG_background);

        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)};


        main_IMG_obstacles = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_obstacle1),
                findViewById(R.id.main_IMG_obstacle2),
                findViewById(R.id.main_IMG_obstacle3),
                findViewById(R.id.main_IMG_obstacle4),
                findViewById(R.id.main_IMG_obstacle5),
                findViewById(R.id.main_IMG_obstacle6),
                findViewById(R.id.main_IMG_obstacle7),
                findViewById(R.id.main_IMG_obstacle8),
                findViewById(R.id.main_IMG_obstacle9)};

        floatingActionButtons = new FloatingActionButton[]{
                findViewById(R.id.fab_BTN_left),
                findViewById(R.id.fab_BTN_right)};

        main_IMG_motorcycle = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_motorcycle1),
                findViewById(R.id.main_IMG_motorcycle2),
                findViewById(R.id.main_IMG_motorcycle3)};

        main_IMG_explosive = new ShapeableImageView[]{
                findViewById(R.id.explosion1),
                findViewById(R.id.explosion2),
                findViewById(R.id.explosion3)};
    }


    public void initBoard() {
        int k = 0;

        //init cars (all invisible)
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = main_IMG_obstacles[k];
                k++;
            }
        }

        //init motorcycle
        board[3][0] = main_IMG_motorcycle[0];
        board[3][1] = main_IMG_motorcycle[1];
        board[3][2] = main_IMG_motorcycle[2];


    }


    public void refreshUI() {
        if (gm.isGameEnded()) {
            gm.resetLife();
            main_IMG_hearts[0].setVisibility(View.VISIBLE);
            main_IMG_hearts[1].setVisibility(View.VISIBLE);
            main_IMG_hearts[2].setVisibility(View.VISIBLE);
        }
        if(gm.getHitPoint() >= 1 && gm.getHitPoint() < 4)
        {
            main_IMG_hearts[main_IMG_hearts.length - gm.getHitPoint()].setVisibility(View.INVISIBLE);
        }

    }

    public void refreshBoard(int mill_seconds) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                obstaclesMovement();
                contentBoard();

            }

        };
        handler.postDelayed(runnable, mill_seconds);

    }

    public void refreshPlayer(int mill_seconds) {

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                int cell = playerLocationFinder();
                playerMovementListener(cell);
                contentPlayer();

            }

        };

        handler.postDelayed(runnable, mill_seconds);
    }


    private void contentPlayer() {

        refreshPlayer(0500); // 1/2 second
        refreshUI();


    }

    private void contentBoard() {
        refreshBoard(10000);
    }


}