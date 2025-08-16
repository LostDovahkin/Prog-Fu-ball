package src.Gamelogic;

import src.TeamManagement.Player;

public class Ball implements GameObject{

    int posHorizontal;
    int posVertical;

    private Player holder;

    private char Symbol = 'O';


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

    /**
     * @param holder
     * Sets a new Player to the Parameter holder and changes the Players hasBall Attribute to true.
     */
    public void setHolder(Player holder){
        this.holder = holder;
        holder.setHasBall(true);
    }
}
