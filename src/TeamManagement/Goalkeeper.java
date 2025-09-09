package TeamManagement;

import Gamelogic.Position;

public class Goalkeeper extends Player {

    public Goalkeeper(int id, Position startPos) {
        super(id, 2, 0.75, 100, 'G', startPos);
    }
}