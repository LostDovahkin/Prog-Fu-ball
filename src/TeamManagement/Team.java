package src.TeamManagement;

import java.util.ArrayList;

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
        players = new ArrayList<>();
        players.add(new Goalkeeper(PlayerNumber));
        players.add(new Defense(PlayerNumber));
        players.add(new Midfield(PlayerNumber));
        players.add(new Midfield(PlayerNumber));
        players.add(new Striker(PlayerNumber));

    }
}
