package src.TeamManagement;

import src.Gamelogic.Ball;
import src.Gamelogic.GameObject;

public abstract class Player implements GameObject {

    private int id;
    private int speed;
    private double precision;
    private int energy;
    private char symbol;
    int posHorizontal;
    int posVertical;
    private boolean hasBall;


    public Player(int id, int speed, double precision, int energy,char symbol) {
        this.id = id;
        this.speed = speed;
        this.precision = precision;
        this.energy = energy;
        this.symbol = symbol;
        Team.PlayerNumber++;


    }
    public boolean shoot(int horizontal, int vertical){
        return false;
    }

    @Override
    public String toString() {
        return ""+id;
    }

    public void movePlayer(int horizontal, int vertical){

    }

    public void rechargeEnergy(){
        if(energy + 50 <= 100 ){
            energy += 50;
        }
        else {
            energy = 100;
        }
    }

    public boolean hasEnoughEnergy(int energyRequired){
        if(energy - energyRequired >= 0){
            return true;
        }
        else {
            return false;
        }
    }

    public void setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
    }

    @Override
    public int getPosHorizontal() {
        return posHorizontal;
    }

    @Override
    public void setPosHorizontal(int posHorizontal) {
        this.posHorizontal = posHorizontal;
    }

    @Override
    public int getPosVertical() {
        return posVertical;
    }

    @Override
    public void setPosVertical(int posVertical) {
        this.posVertical = posVertical;
    }
}
