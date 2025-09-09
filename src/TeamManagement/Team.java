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
    public Player getPlayerById(int searchId) {
        for (Player p : players) {
            if (p != null && p.getId() == searchId) return p;
        }
        return null;
    }
    public String getName(){
        if(leftSide){
            return "Linkes Team";
        }
        else{return "Rechtes Team";}
    }

    public void printPlayers() {
        for (Player p : players) {
            if (p != null) {
                System.out.println("ID: " + p.getId() + " | Rolle: " + p.getClass().getSimpleName() + " | Position: (" + p.getPosition().getX() + ", " + p.getPosition().getY() + ")");
            }
        }
    }
    /**
     * Constructor for teams. Checks the attribute leftSide and intitializes player objects for each team and stores them in the players Array.
     * @param leftSide true if team is on the left
     */
    public Team(boolean leftSide) {
        this.leftSide = leftSide;
         players = new ArrayList<>();

         if (leftSide) {
        	    // Linkes Team (Start am linken Rand)
        	    players.add(new Goalkeeper(PlayerNumber, new Position(4, 1)));
        	    players.add(new Defense(PlayerNumber, new Position(2, 3)));
        	    players.add(new Midfield(PlayerNumber, new Position(4, 4)));
        	    players.add(new Midfield(PlayerNumber, new Position(4, 6)));
        	    players.add(new Striker(PlayerNumber, new Position(6, 5)));
        	} else {
        	    // Rechtes Team (Start am rechten Rand)
        	    players.add(new Goalkeeper(PlayerNumber, new Position(4, 19)));
        	    players.add(new Defense(PlayerNumber, new Position(6, 17)));
        	    players.add(new Midfield(PlayerNumber, new Position(4, 16)));
        	    players.add(new Midfield(PlayerNumber, new Position(4, 14)));
        	    players.add(new Striker(PlayerNumber, new Position(2, 15)));
        	}

    }
}