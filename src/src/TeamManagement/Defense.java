package TeamManagement;

import Gamelogic.Position;

public class Defense extends Player {

  
    public Defense(int id, Position startPos) {
        super(id, 3, 0.8, 100, 'D', startPos);
    }
}