package com.example.yanpat01.counter;

/**
 * Created by yanpat01 on 25/01/2018.
 */

public class TypeGame {

    private TypeTeam eqA;
    private TypeTeam eqB;
    private int TimeOuts;
    private int TimeOutTech;
    private int TimeOutsTime;
    private int TimeOutTechTime;
    private int nbTimeOut;
    private int nbSetsWin;
    // Number of points to win a normal set, last set and nb of pts for a win
    private int nbPointsWin;
    private int nbPointsWinLS;
    private int nbPointsWinWay;

    public void initiate() {
        this.eqA = new TypeTeam();
        this.eqB = new TypeTeam();
        this.TimeOuts = 0;
        this.TimeOutsTime = 30;
        this.TimeOutTech = 0;
        this.TimeOutTechTime = 60;
        this.nbTimeOut = 0;
        this.nbSetsWin = 3;
        this.nbPointsWin = 25;
        this.nbPointsWinLS = 15;
        this.nbPointsWinWay = 2;
    }
    public void initiateAsie() {
        this.eqA = new TypeTeam();
        this.eqB = new TypeTeam();
        this.TimeOuts = 1;
        this.TimeOutTech = 0;
        this.TimeOutsTime = 30;
        this.nbTimeOut = 2;
        this.nbSetsWin = 2;
        this.nbPointsWin = 25;
        this.nbPointsWinLS = 15;
        this.nbPointsWinWay = 2;
    }
    public void InitiateTest() {
        this.eqA = new TypeTeam();
        this.eqB = new TypeTeam();
        this.TimeOuts = 1;
        this.TimeOutTech = 0;
        this.TimeOutsTime = 3;
        this.TimeOutTechTime = 6;
        this.nbTimeOut = 2;
        this.nbSetsWin = 2;
        this.nbPointsWin = 25;
        this.nbPointsWinLS = 15;
        this.nbPointsWinWay = 2;
    }

    public TypeTeam getEqA() {
        return eqA;
    }
    public TypeTeam getEqB() {
        return eqB;
    }
    public int getTimeOuts() {
        return TimeOuts;
    }
    public int getTimeOutsTime() {
        return TimeOutsTime;
    }
    public int getTimeOutTech() {
        return TimeOutTech;
    }
    public int getTimeOutTechTime() {
        return TimeOutTechTime;
    }
    public int getNbTimeOut() {
        return nbTimeOut;
    }
    public int getSetsWin() {
        return nbSetsWin;
    }
    public int getPointsWin() {
        return nbPointsWin;
    }
    public int getPointsWinLS() {
        return nbPointsWinLS;
    }
    public int getPointsWinWay() {
        return nbPointsWinWay;
    }

    public void setEqA(TypeTeam EqA) {
        this.eqA = EqA;
    }
    public void setEqB(TypeTeam EqB) {
        this.eqB = EqB;
    }
    public void setTimeOuts(int timeOuts) {
        this.TimeOuts = timeOuts;
    }
    public void setTimeOutsTime(int timeOutsTime) {
        this.TimeOutsTime = timeOutsTime;
    }
    public void setTimeOutTech(int timeOutsTech) {
        this.TimeOutTech =  timeOutsTech;
    }
    public void setTimeOutTechTime(int timeOutsTechTime) {
        this.TimeOutTechTime = timeOutsTechTime;
    }
    public void setNbTimeOut(int nbTimeOut) {
        this.nbTimeOut = nbTimeOut;    }
    public void setSetsWin(int SetsWin) {
        this.nbSetsWin = SetsWin;
    }
    public void setPointsWin(int PointsWin) {
        this.nbPointsWin = PointsWin;
    }
    public void setPointsWinLS(int PointsWinLS) {
        this.nbPointsWinLS = PointsWinLS;
    }
    public void setPointsWinWay(int PointsWinWay) {
        this.nbPointsWinWay = PointsWinWay;
    }
}
