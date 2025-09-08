package TeamManagement;

import java.util.ArrayList;

import Gamelogic.Position;

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

    public String getName(){
        if(leftSide){
            return "Linkes Team";
        }
        else{return "Rechtes Team";}
    }

    /**
     * Constructor for teams. Checks the attribute leftSide and intitializes player objects for each team and stores them in the players Array.
     * @param leftSide true if team is on the left
     */
    public Team(boolean leftSide) {
        this.leftSide = leftSide;
         players = new ArrayList<>();

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