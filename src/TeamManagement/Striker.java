package TeamManagement;

import Gamelogic.Position;

public class Striker extends Player{

    public Striker(int id, Position startPos) {
        super(id, 4, 0.75, 100,'S', startPos);
    }
}