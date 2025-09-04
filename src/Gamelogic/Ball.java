package src.Gamelogic;

import src.TeamManagement.Player;

public class Ball implements GameObject{

    Position position;

    private Player holder;

    private char Symbol = 'O';

    private Gamefield gamefield;

    public Ball(Gamefield gamefield) {
        this.gamefield = gamefield;
        position = new Position(4,9);
    }


    @Override
    public Position getPosition() {
        return position;
    }


    @Override
    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }


    @Override
    public String toString() {
        return "" + Symbol;
    }



    /**
     * @param holder
     * Sets a new Player to the Parameter holder and changes the Players hasBall Attribute to true.
     */
    public void setHolder(Player holder){
        if (this.holder != null) {
            this.holder.setHasBall(null);
        }
        this.holder = holder;
        if (this.holder != null) {
        this.holder.setHasBall(this);
        }
    }

    public Player getHolder() {
        return holder;
    }

    /**
     * Resets the balls Position to the middle.
     */
    public void resetPosition() {
        position.setX(4);
        position.setY(9);
    }
}
