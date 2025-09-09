package Gamelogic;
import TeamManagement.*;
import java.util.Scanner;
import java.io.File;
import Gamelogic.SaveLoadUtil;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SaveGame save = null;
        boolean saveExists = new File("savegame.dat").exists();

        boolean loadExisting = false;
        if (saveExists) {
            System.out.print("Möchten Sie ein gespeichertes Spiel fortsetzen? (ja/nein): ");
            String antwort = sc.nextLine();
            loadExisting = antwort.trim().equalsIgnoreCase("ja");
        }

        if (loadExisting) {
            save = SaveLoadUtil.loadGame("savegame.dat");
            if (save != null) {
                Team t1 = save.team1;
                Team t2 = save.team2;
                Ball ball = save.ball;
                int currentRound = save.currentRound;
                int maxRounds = save.maxRounds;
                boolean isTeam1Turn = save.isTeam1Turn;

                ConsoleHandler handler = new ConsoleHandler(t1, t2, ball, maxRounds, currentRound, isTeam1Turn);
                handler.startGame();
                return;
            } else {
                System.out.println("Kein gespeicherter Spielstand gefunden. Starte neues Spiel!");
            }
        }

        // Neues Spiel
        Team t1 = new Team(true);
        Team t2 = new Team(false);
        Ball ball = new Ball(null);
        int maxRounds = 20;
        int currentRound = 0;
        boolean isTeam1Turn = true;

        ConsoleHandler handler = new ConsoleHandler(t1, t2, ball, maxRounds, currentRound, isTeam1Turn);
        handler.startGame();
    }
}