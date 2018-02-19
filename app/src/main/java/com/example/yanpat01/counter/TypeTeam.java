package com.example.yanpat01.counter;

/**
 * Created by yanpat01 on 18/12/2017.
 */

public class TypeTeam {
    // private String eqType;
    private String eqName;
    private int TimeOutTaken;
    private int score;
    private int setWon;
    private int color;
    private int colorTxt;
    private String image;

    public void initiate(String teamName) {
        this.score = 0;
        this.eqName = teamName;
        this.setWon = 0;
        this.TimeOutTaken = 0;
    }

    public String getEqName() {
        return eqName;
    }
    public int getTimeOutTaken() {
        return TimeOutTaken;
    }
    public int getScore() {
        return score;
    }
    public int getSetWon() {
        return setWon;
    }
    public void setScore(int Score) {
        this.score=Score;
    }
    public void incTimeOut() {
        this.TimeOutTaken++;
    }
    public void setSetWon(int SetWon) {
        this.setWon = SetWon;
    }
    public void setEqName(String EqName) {
        this.eqName = EqName;
    }
    public int incScore() {
        return score++;
    }
    public int decScore() {
        return score--;
    }
    public void newSet() {
        this.score = 0;
        this.TimeOutTaken = 0;
    }
    public void reset() {
        this.score = 0;
        this.TimeOutTaken = 0;
    }
    public void resetfull() {
        this.score = 0;
        this.TimeOutTaken = 0;
        this.eqName = "";
        this.setWon=0;
    }
    public void wonSet() {
        this.score = 0;
        this.TimeOutTaken = 0;
        this.setWon++;
    }
    public void setBgColor(int Color) {
        this.color= Color;
    }
    public void setTxtColor(int Color) {
        this.colorTxt= Color;
    }
    public int getBgColor() {
        return this.color;
    }
    public int getTxtColor() {
        return this.colorTxt;
    }


}
