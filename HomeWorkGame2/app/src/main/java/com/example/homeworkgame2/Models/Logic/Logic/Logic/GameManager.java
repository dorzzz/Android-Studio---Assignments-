package com.example.homeworkgame2.Models.Logic.Logic.Logic;


import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class GameManager {

    private int life;
    private int hitPoint;






    public GameManager(int life) {
        this.life = life;
        this.hitPoint  = 0 ;

    }

    public void getHit()
    {

        hitPoint++;
    }

    public int getHitPoint()
    {
        return hitPoint;
    }

    public int getLife() {
        return life;
    }
    public int getCurrentLife()
    {
        return life - hitPoint;
    }

    public boolean isGameEnded()
    {
        if(getCurrentLife() == 0 )
        {
            return true;
        }
        else
            return false;

    }

    //endeless game though
    public void resetLife()
    {
        hitPoint = 0;
    }


}

