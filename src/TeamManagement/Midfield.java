package TeamManagement;

import Gamelogic.Gamefield; // Warum ist nur bei dieser Klasse an Spielern das Gamefield importiert
import Gamelogic.Position;

public class Midfield extends Player{

    public Midfield(int id, Position startPos) {
        super(id, 3, 0.8, 100,'M', startPos);

    }

}