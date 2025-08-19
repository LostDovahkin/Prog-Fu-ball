package src.TeamManagement;

import src.Gamelogic.Gamefield;
import src.Gamelogic.Position;

public class Goalkeeper extends Player {

    private final char Symbol = 'G';

    public Goalkeeper(int id, Position startPos) {
        super(id, 2, 0.75, 100, 'G', startPos);
    }
}
