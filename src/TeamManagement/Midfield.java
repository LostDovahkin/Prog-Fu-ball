package src.TeamManagement;

import src.Gamelogic.Gamefield; // Warum ist nur bei dieser Klasse an Spielern das Gamefield importiert
import src.Gamelogic.Position;

public class Midfield extends Player{

    public Midfield(int id, Position startPos) {
        super(id, 3, 0.8, 100,'M', startPos);


    }

}
