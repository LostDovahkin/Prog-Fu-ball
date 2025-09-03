package src.TeamManagement;

import src.Gamelogic.Ball;
import src.Gamelogic.GameObject;
import src.Gamelogic.Gamefield;
import src.Gamelogic.Position;

public abstract class Player implements GameObject {

    private int id;
    private int speed;
    private double precision;
    private int energy;
    private char symbol;
    private Ball ballObj;
    private Position position;
    private Position startPosition;


    public Player(int id, int speed, double precision, int energy, char symbol, Position startPos) {
        this.id = id;
        this.speed = speed;
        this.precision = precision;
        this.energy = energy;
        this.symbol = symbol;
        this.startPosition = startPos;
        this.position = startPosition;
        Team.PlayerNumber++;


    }

    /**
     * Checks if energy is sufficient. Invokes gamefield.moveObject with the params ballObj,horizontal,vertical.
     * Prints "Nicht genügend Energie" if energy is to low.
     * @param horizontal
     * @param vertical
     * @param gamefield
     * @return true if shoot is successful | false if shoot is unsuccessful
     */
    public boolean shoot(int horizontal, int vertical, Gamefield gamefield) {
        if (energy < 24) {
            if (gamefield.moveObject(ballObj, horizontal, vertical)) {
                energy -= 25;
                return true;
            }
            return false;
        }
        System.out.println("Nicht genügend Energie");
        return false;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    /**
     * Checks if Player has enough energy. If energy is sufficient invokes the moveObject method with the horizontal and vertical param.
     * @param horizontal
     * @param vertical
     * @param gamefield
     * @return false if movement isn't possible | true if movement was successful.
     */
    public boolean movePlayer(int horizontal, int vertical, Gamefield gamefield) {
        if (energy >= speed){
            if (gamefield.moveObject(this, horizontal, vertical)) {
                energy -= speed;
                if (this.ballObj != null){
                    ballObj.setPosition(this.position.getX(), this.position.getY());
                }
                return true;
            }
            System.out.println("Ungültige Position");
            return false;
        }
        System.out.println("Zu wenig Energie");
        return false;
    }

    /**
     * Refuels energy by 50 or sets it to 100 if it would be more than 100.
     */
    public void rechargeEnergy() {
        if (energy + 50 <= 100) {
            energy += 50;
        } else {
            energy = 100;
        }
    }
    public int getEnergy(){
        return energy;
    }

    public void setHasBall(Ball BallObj) {
        this.ballObj = BallObj;
    }
    //returns momentary position
    @Override
    public Position getPosition() {
        return position;
    }

    //Important for player movement
    @Override
    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }
    public Position getStartPosition() {return startPosition;}

}
