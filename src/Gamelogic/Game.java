package src.Gamelogic;


import src.TeamManagement.*;

public class Game {
    public static void main(String[] args) {

        Team t1 = new Team(true);
        Team t2 = new Team(false);
        Gamefield gamefield = new Gamefield(t1,t2);
        Ball ball = new Ball(gamefield);
        ball.resetPosition();
        gamefield.addGameObject(ball,ball.position.getX(),ball.position.getY());
        Player p1 = t1.getPlayers().get(1);
        gamefield.addGameObject(p1,p1.getPosition().getX(),p1.getPosition().getY());
        gamefield.printField();
        System.out.println();
        p1.movePlayer(1,1,gamefield);
        gamefield.printField();

    }
}
