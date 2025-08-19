package src.Gamelogic;

import src.TeamManagement.Player;

public class Ball implements GameObject{

    Position position;

    private Player holder;

    private char Symbol = 'O';


    @Override
    public Position getPosition() {
        return position;
    }


    @Override
    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }


    /**
     * @param holder
     * Sets a new Player to the Parameter holder and changes the Players hasBall Attribute to true.
     */
    public void setHolder(Player holder){
        this.holder = holder;
        holder.setHasBall(this);
    }
}
