package src.TeamManagement;

import src.Gamelogic.Gamefield;
import src.Gamelogic.Position;

public class Striker extends Player{

    private char Symbol = 'S';

    public Striker(int id, Position startPos) {
        super(id, 4, 0.75, 100,'S', startPos);
    }
}
