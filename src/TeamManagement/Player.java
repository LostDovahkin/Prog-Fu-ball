package src.TeamManagement;

import src.Gamelogic.GameObject;
import src.Gamelogic.Gamefield;

public abstract class Player implements GameObject {

    private int id;
    private int speed;
    private double precision;
    private int energy;
    private static int playerCount;
    private char symbol;


    public Player(int id, int speed, double precision, int energy,char symbol) {
        this.id = id;
        this.speed = speed;
        this.precision = precision;
        this.energy = energy;
        this.symbol = symbol;
        Team.PlayerNumber++;


    }

    @Override
    public String toString() {
        return ""+symbol;
    }

    public void movePlayer(int x, int y){

    }
}
