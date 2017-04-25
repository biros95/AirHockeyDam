package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;

/**
 * Created by MarcosPortatil on 19/04/2017.
 */

public class ScroeBoard {
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    //Member variable:
    private float totalTime = 5 * 60; //starting at 5 minutes

    void render() {
        float deltaTime = Gdx.graphics.getDeltaTime(); //You might prefer getRawDeltaTime()

        totalTime -= deltaTime; //if counting down

        int minutes = ((int) totalTime) / 60;
        int seconds = ((int) totalTime) % 60;


    }

    public ScroeBoard() {
    }

    public void start (){

    }

    public void terminar(){

    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public float getTotalTime() {
        return totalTime;
    }
}
