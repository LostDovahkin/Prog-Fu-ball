package TeamManagement;

import Gamelogic.Ball;
import Gamelogic.GameObject;
import Gamelogic.Gamefield;
import Gamelogic.Position;
import java.io.Serializable;

public abstract class Player implements GameObject, java.io.Serializable {

    private int id;
    private int speed;
    private int energy;
    private Ball ballObj;
    private Position position;
    private Position startPosition;


    public Player(int id, int speed, double precision, int energy, char symbol, Position startPos) {
        this.id = id;
        this.speed = speed;
        this.energy = energy;
        this.startPosition = new Position(startPos.getX(), startPos.getY());
        this.position = new Position(startPos.getX(), startPos.getY());
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
    public boolean shoot(int dx, int dy, Gamefield gamefield, Ball ball) {
        int playerX = this.getPosition().getX();
        int playerY = this.getPosition().getY();
        int ballX = ball.getPosition().getX();
        int ballY = ball.getPosition().getY();

        if (!(playerX == ballX && playerY == ballY && ball.getHolder() == this)) {
            System.out.println("Du kannst nur schießen, wenn du auf dem Ball stehst und ihn besitzt!");
            return false;
        }

        if (energy >= 24) {
            if (gamefield.moveObject(ball, dx, dy)) {
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
            return false;
        }
        System.out.println("Zu wenig Energie");
        return false;
    }
    public int getId() {
        return this.id;
    }
    
    public void loadEnergy() {
        this.energy = Math.min(100, this.energy + 25);
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
    
    public boolean hasBall(){
        return this.ballObj != null;
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



    /**
     * Attempts to steal the ball from a neighboring player and assign it to the current player.
     * <p>
     * Conditions:
     * <ul>
     *   <li>The current player must have at least 20 energy points.</li>
     *   <li>Checks the four adjacent positions (up, down, left, right) for a player who possesses the ball.</li>
     *   <li>If such a player is found, the ball is removed from them and assigned to the current player.</li>
     *   <li>The ball's position is updated to match the current player's position.</li>
     *   <li>Upon successful ball transfer, 20 energy points are deducted from the current player.</li>
     * </ul>
     *
     * @param gamefield The game field containing all game objects and their positions.
     */

    public void stealBallFromNeighbor(Gamefield gamefield) {
        if (this.energy < 20) {
            System.out.println("Nicht genug Energie zum Ball abnehmen.");
            return;
        }

        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        // Directions: up, down, left, right
        int[][] directions = {
                {-1, 0}, // up
                {1, 0},  // down
                {0, -1}, // left
                {0, 1}   // right
        };

        for (int[] dir : directions) {
            int neighborX = x + dir[0];
            int neighborY = y + dir[1];

            GameObject obj = gamefield.getObjectAt(neighborX, neighborY);

            if (obj instanceof Player) {
                Player neighbor = (Player) obj;
                if (neighbor.hasBall()) {
                    Ball stolenBall = neighbor.ballObj;
                    neighbor.setHasBall(null); // delete ball from neighbor
                    this.setHasBall(stolenBall); // take ball
                    stolenBall.setPosition(x, y); // update the balls position
                    this.energy -= 20; // update energy
                    System.out.println("Spieler " + this + " hat den Ball von Spieler " + neighbor + " übernommen. Energie: " + this.energy);
                    return;
                }
            }
        }

        System.out.println("Kein benachbarter Spieler mit Ball gefunden.");
    }


}