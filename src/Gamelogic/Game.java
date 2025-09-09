package Gamelogic;


import TeamManagement.*;

public class Game {
    public static void main(String[] args) {
        Team t1 = new Team(true);
        Team t2 = new Team(false);
        Ball ball = new Ball(null);
        ConsoleHandler handler = new ConsoleHandler(t1, t2, ball, 20);
        handler.startGame();
    }
}