package src.TeamManagement;

import src.Gamelogic.Position;

public class Defense extends Player{

    private final char Symbol= 'D';

    public Defense(int id, Position startPos) {
        super(id, 3, 0.8, 100,'D', startPos);
    }
}
