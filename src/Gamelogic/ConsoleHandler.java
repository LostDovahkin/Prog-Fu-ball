package Gamelogic;

import java.util.Scanner;
import TeamManagement.*;

public class ConsoleHandler {

    private Team teamt1;
    private Team teamt2;
    private Ball ball;
    private int maxRounds;
    private int currentRound;
    private Scanner sc;
    private Gamefield gamefield;
    private boolean isTeam1Turn;

    public ConsoleHandler(Team t1, Team t2, Ball ball, int maxRounds, int currentRound, boolean isTeam1Turn) {
        this.teamt1 = t1;
        this.teamt2 = t2;
        this.ball = ball;
        this.maxRounds = maxRounds;
        this.currentRound = currentRound;
        this.sc = new Scanner(System.in);
        this.gamefield = new Gamefield(t1, t2);

        this.gamefield.addGameObject(ball, ball.getPosition().getX(), ball.getPosition().getY());

        for(Player p : t1.getPlayers()) {
            gamefield.addGameObject(p, p.getPosition().getX(), p.getPosition().getY());
        }
        for(Player p : t2.getPlayers()) {
            gamefield.addGameObject(p, p.getPosition().getX(), p.getPosition().getY());
        }
    }

    public void startGame() {
        boolean playAgain = true;

        while (playAgain) {
            Team currentTeam = teamt1;

            gamefield.printField();
            
            while (currentRound < maxRounds) {
                System.out.println("\nRunde " + (currentRound + 1) + " | Team am Zug: " + currentTeam.getName());
                currentTeam.printPlayers();

                Player activePlayer = selectPlayer(currentTeam);

                playerAction(activePlayer);

                gamefield.checkAndHandleGoal(ball);
                gamefield.postTurnStatus(activePlayer, ball);
                gamefield.printScore();
                gamefield.printField();

                currentRound++;
                currentTeam = swapCurrentTeam(currentTeam);
            }

            System.out.println("Partie beendet.");
            System.out.print("Willst du eine neue Partie spielen? (ja/nein): ");
            String answer = sc.next();
            playAgain = answer.equalsIgnoreCase("ja");
        }
    }

    private Player selectPlayer(Team currentTeam) {
        Player selected = null;
        do {
            System.out.print("Gib die Spieler-ID ein: ");
            String input = sc.next();
            try {
                int chosenId = Integer.parseInt(input);
                selected = currentTeam.getPlayerById(chosenId);
                if(selected == null) System.out.println("Keine gültige Eingabe, bitte versuchen Sie es erneut.");  
            } catch(Exception e) {
                System.out.println("Ungültige Eingabe.");
            }
        } while(selected == null);
        return selected;
    }

    private void playerAction(Player player) {
        boolean actionDone = false;
        while (!actionDone) {
        	System.out.println("Welche Aktion? (1=Laufen, 2=Schießen, 3=Energie aufladen, 4=Ball stehlen)");
            int choice = -1;

            // Eingabe-Check für choice – wiederholt bis gültig
            while (choice < 1 || choice > 4) {
                try {
                    choice = Integer.parseInt(sc.next());
                    if (choice < 1 || choice > 4) {
                        System.out.println("Keine gültige Eingabe, bitte versuchen Sie es erneut.");
                    }
                } catch (Exception e) {
                    System.out.println("Keine gültige Eingabe, bitte versuchen Sie es erneut.");
                    sc.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    // Bewegung solange wiederholen bis sie gültig ist!
                    boolean moved = false;
                    while (!moved) {
                        try {
                            System.out.print("Bewege um dx (+unten/-oben): ");
                            int dx = Integer.parseInt(sc.next());
                            System.out.print("Bewege um dy (+rechts/-links): ");
                            int dy = Integer.parseInt(sc.next());
                            moved = player.movePlayer(dx, dy, gamefield);
                            if (!moved) {
                                System.out.println("Ungültige Eingabe.");
                            }
                        } catch (Exception e) {
                            System.out.println("Ungültige Eingabe.");
                            sc.nextLine();
                        }
                    }
                    actionDone = true;
                    break;
                case 2:
                    int playerX = player.getPosition().getX();
                    int playerY = player.getPosition().getY();
                    int ballX = ball.getPosition().getX();
                    int ballY = ball.getPosition().getY();

                    if (!(playerX == ballX && playerY == ballY && ball.getHolder() == player)) {
                        System.out.println("Du kannst nur schießen, wenn du auf dem Ball stehst und ihn besitzt!");
                        break;
                    }

                    try {
                        System.out.print("Schuss-Richtung dx (+unten/-oben): ");
                        int hdx = Integer.parseInt(sc.next());
                        System.out.print("Schuss-Richtung dy (+rechts/-links): ");
                        int hdy = Integer.parseInt(sc.next());

                        int newX = ball.getPosition().getX() + hdx;
                        int newY = ball.getPosition().getY() + hdy;

                        if (newX < 0 || newX >= gamefield.getFieldWidth() || newY < 0 || newY >= gamefield.getFieldHeight()) {
                            System.out.println("Schuss fehlgeschlagen: Ziel liegt außerhalb des Spielfelds!");
                            break;
                        }

                        boolean moved1;
                        moved1 = gamefield.moveObject(ball, hdx, hdy);

                        GameObject ziel = gamefield.getObjectAt(newX, newY);
                        if (ziel instanceof Player) {
                            ball.setHolder((Player) ziel);
                            System.out.println("Nach dem Schuss besitzt Spieler " + ((Player) ziel).getId() + " den Ball.");
                        } else {
                            ball.setHolder(null);
                            System.out.println("Der Ball ist jetzt frei auf dem Spielfeld.");
                        }

                        if (moved1) {
                            actionDone = true;
                        } else {
                            System.out.println("Schuss fehlgeschlagen oder ungültig!");
                        }
                    } catch (Exception e) {
                        System.out.println("Ungültige Eingabe.");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    player.loadEnergy();
                    System.out.println("Energie wurde aufgeladen.");
                    actionDone = true;
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
                case 4:
                    player.stealBallFromNeighbor(gamefield);
                    actionDone = true; // Nur setzen, wenn du willst, dass das Stehlen einen Zug kostet!
                    break;
            }
        }
        // Alles Speichern
        SaveGame save = new SaveGame(teamt1, teamt2, ball, currentRound, maxRounds, isTeam1Turn);
        if (ball.getHolder() != null) {
            ball.getHolder().setHasBall(ball);
        }
        for (Player p : teamt1.getPlayers()) {
            if (p != ball.getHolder()) p.setHasBall(null);
        }
        for (Player p : teamt2.getPlayers()) {
            if (p != ball.getHolder()) p.setHasBall(null);
        }
        SaveLoadUtil.saveGame(save, "savegame.dat");
    }
    private Team swapCurrentTeam(Team t) {
        return (t == teamt1) ? teamt2 : teamt1;
    }
}