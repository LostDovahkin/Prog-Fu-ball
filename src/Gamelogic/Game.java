package src.Gamelogic;


import src.TeamManagement.Team;

public class Game {
    public static void main(String[] args) {
        Gamefield Gamefield = new Gamefield();

        Team t1 = new Team(true);
        Team t2 = new Team(false);
        Gamefield.addGameObject(t1.getPlayers().get(1),1,1 );
        Gamefield.printField();

    }
}
