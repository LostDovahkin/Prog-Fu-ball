package src.Gamelogic;


import src.TeamManagement.Team;

public class Game {
    public static void main(String[] args) {

        Team t1 = new Team(true);
        Team t2 = new Team(false);
        Gamefield Gamefield = new Gamefield(t1,t2);
        Gamefield.addGameObject(t1.getPlayers().get(1),1,1 );
        Gamefield.printField();

    }
}
