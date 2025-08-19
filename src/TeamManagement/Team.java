package src.TeamManagement;

import java.util.ArrayList;

import src.Gamelogic.Position;

public class Team {

    public static int PlayerNumber = 1;
    private ArrayList<Player>players;
    private int score = 0;
    private boolean leftSide;

    public ArrayList<Player>getPlayers() {return players;}

    public int getScore() {return score;}

    public void addScore(){
        score++;
    }

    public Team(boolean leftSide) {
        this.leftSide = leftSide;
        ArrayList<Player>players = new ArrayList<>();
        
        if (leftSide) {
        	players.add(new Goalkeeper(PlayerNumber, new Position (0,4)));
        	players.add(new Defense(PlayerNumber, new Position (1,2)));
        	players.add(new Midfield(PlayerNumber, new Position (3,3)));
        	players.add(new Midfield(PlayerNumber, new Position (3,5)));
        	players.add(new Striker(PlayerNumber, new Position (5,4)));
        }
        else {
        	players.add(new Goalkeeper(PlayerNumber, new Position (19,4)));
        	players.add(new Defense(PlayerNumber, new Position (18,2)));
        	players.add(new Midfield(PlayerNumber, new Position (16,3)));
        	players.add(new Midfield(PlayerNumber, new Position (16,5)));
        	players.add(new Striker(PlayerNumber, new Position (14,4)));
        }

    }
}
