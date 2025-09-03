package src.Gamelogic;

import java.util.Scanner;

import src.TeamManagement.*;

import src.TeamManagement.Player;
import src.TeamManagement.Team;

public class ConsoleHandler {
/*
    private Scanner sc = new Scanner(System.in);
    private Team teamt1;
    private Team teamt2;
    private Ball ball;
    private int maxRounds;
    private int currentRound;


    public ConsoleHandler(Team teamt1, Team teamt2, Ball ball, int maxRounds) {
        this.teamt1 = teamt1;
        this.teamt2 = teamt2;
        this.ball = ball;
        this.maxRounds = maxRounds;
        this.currentRound = 0;
    }


    private void initializePositions() {
        resetToStartPositions();
    }


    private boolean allPlayersInStartPosition() {
        return arePlayersInStartPosition(teamt1) && arePlayersInStartPosition(teamt2);
    }


    private boolean arePlayersInStartPosition(Team team) {
        for (Player p : team.getPlayers()) {
            if (p == null) continue;
            Position current = p.getPosition();
            Position start = p.getPosition();
            if (current.getX() != start.getX() || current.getY() != start.getY()) {
                return false;

            }
        }
        return true;
    }

    private void resetToStartPositions() {
        resetTeamPositions(teamt1);
        resetTeamPositions(teamt2);

    }

    private void resetTeamPositions(Team team) {
        for (Player p : team.getPlayers()) {
            if (p != null) {
                Position start = p.getStartPosition(); // Spieler auf die Startposition bewegen
                p.getPosition().bewegen(
                        start.getX() - p.getPosition().getX(),
                        start.getY() - p.getPosition().getY()
                );
            }
        }
    }


    private void printTeamsAndPlayers() {
        System.out.println("=== Teams und Spieler ===");
        System.out.println("Team t1:");
        teamt1.printPlayers();
        System.out.println("Team t2:");
        teamt2.printPlayers();
        System.out.println("=========================");
    }


    /**
     * Starts the game loop.
     * <p>
     * The method initializes all players to their starting positions and then runs the game for the configures maximum numnber of rounds. Each round follows this structure:
     */
 /*   public void start() {
        boolean playAgain = true;
        while (playAgain) {
            currentRound = 0;
            initializePositions();
        }
        while (currentRound < maxRounds) {
            if (!allPlayersInStartPosition()) {
                System.out.println("Die Spieler finden noch ihre Startposition. WIir bitten um einen Moment Geduld :) .");
                resetToStartPositions();
                continue; //nächste Schleifenrunde
            }

            printTeamsAndPlayers();
            getTeamMoves(teamt1);
            getTeamMoves(teamt2);
            //updateGameState();
            //printGameStatus();


            currentRound++;
            System.out.println("Die Runde ist abgeschlossen.");

        }

        //printWinner();

        System.out.println("Willst du eine neue Partie spielen? (ja/ nein) ");
        String answer = sc.next();
        if (!answer.equalsIgnoreCase("ja")) {
            playAgain = false;
        }
    }

    private boolean isPositionOccupied(int x, int y, Player currentPlayer) {
        for (Player p : teamt1.getPlayers()) {
            if (p != null && p != currentPlayer) {
                if (p.getPosition().getX() == x && p.getPosition().getY() == y) {
                    return true;
                }
            }


        }
        return false;
    }


    private void getTeamMoves(Team team) {
        System.out.println("Züge für das Team " + team.getName() + "eingeben: ");
        for (Player p : team.getPlayers()) {
            if (p == null) continue;

            boolean valid = false;
            int choice=0;
            while (!valid) {
                System.out.println("/nAktion für" + p.toString());
                System.out.println("1 = Laufen");
                System.out.println("2 = Passen");
                System.out.println("3 = Torschuss");
                System.out.println("4 = Blocken");
                System.out.println("5 = Energieaufladen");
                System.out.println("Aktion wählen: ");

                boolean erfolg = false;
                while (!erfolg) {

                    try {
                        choice = sc.nextInt();
                        erfolg = true;
                    } catch (Exception e) {
                        System.out.println("Geben sie eine gültige Zahl ein.");
                    }
                }


                switch (choice) {
                    case 1: //Laufen
                        System.out.println("Bewegung (dx dy): ");
                        int dx = sc.nextInt();
                        int dy = sc.nextInt();

                        if () {
                            p.getPosition().bewegung(dx, dy);
                            System.out.println(p.toString() + "läuft nach (" + p.getPosition().getX() + ", " + p.getPosition().getY() + ")");
                            valid = true;
                        } else {
                            System.out.println(" Diese Aktion ist leider nicht möglich :( \nProbiere es nocheinmal :). ");
                        }
                        break;
                    case 2: //Passen
                        Player mate = chooseTeammate(team, p);
                        if (mate != null) {
                            System.out.println(p.toString() + "passt zu" + mate.toString());
                            ball.setOwner(mate);
                            valid = true;
                        }
                        break;

                    case 3: //Torschuss
                        if (isNearGoal(p)) {
                            System.out.println(p.toString() + "schießt auf das Tor.");
                            //To-Do: Tor-Logik einbauen
                            valid = true;
                        } else {
                            System.out.println("Der Spieler ist leider zu weit von Tor für einen Torschuss entfernt :(");

                        }
                        break;

                    case 4: //Blocken
                        if (p.getRole().equals("Verteidiger")) {
                            Team opponentteam = (team == team1) ? team2 : team1;
                            Player blocked = isAdjacentOpponent(p, opponentTeam);
                            if (blocked != null) {


                            }
                            System.out.println(p.toString() + " möchte den Gegner blocken.");
                            //To-Do: Block-Logik einbauen
                            valid = true;
                        } else {
                            System.out.println("Der Spieler ist leider zu weit von Tor für einen Torschuss entfernt :(");

                        }
                        break;

                    case 5: // Energieaufladen
                        p.regenerateEnergy();
                        System.out.println(p.toString() + "lädt aktuell seine Energie auf.");
                        valid = true;
                        break;
                    default:
                        System.out.println("Die Eingabe ist ungültig. Bitte wähle eine von den 5 aus.");
                }
            }
        }

    }

*/
}
