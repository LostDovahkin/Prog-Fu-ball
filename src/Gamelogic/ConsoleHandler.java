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

    public ConsoleHandler(Team t1, Team t2, Ball ball, int maxRounds) {
        this.teamt1 = t1;
        this.teamt2 = t2;
        this.ball = ball;
        this.maxRounds = maxRounds;
        this.currentRound = 0;
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
            currentRound = 0;
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
            System.out.println("Welche Aktion? (1=Laufen, 2=Schießen, 3=Energie aufladen)");
            int choice = -1;

            // Eingabe-Check für choice – wiederholt bis gültig
            while (choice < 1 || choice > 3) {
                try {
                    choice = Integer.parseInt(sc.next());
                    if (choice < 1 || choice > 3) {
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
                    // Vor dem Schuss prüfen: Steht Spieler wirklich neben Ball?
                    int playerX = player.getPosition().getX();
                    int playerY = player.getPosition().getY();
                    int ballX = ball.getPosition().getX();
                    int ballY = ball.getPosition().getY();
                    int distX = Math.abs(playerX - ballX);
                    int distY = Math.abs(playerY - ballY);

                    if (!((distX <= 1) && (distY <= 1) && !(distX == 0 && distY == 0))) {
                        System.out.println("Du musst genau neben dem Ball stehen, um zu schießen!");
                        // Startet wieder oben mit Aktionsauswahl!
                        break;
                    }
                    try {
                        System.out.print("Schuss-Richtung dx (+unten/-oben): ");
                        int hdx = Integer.parseInt(sc.next());
                        System.out.print("Schuss-Richtung dy (+rechts/-links): ");
                        int hdy = Integer.parseInt(sc.next());
                        boolean shot = player.shoot(hdx, hdy, gamefield, ball);
                        if (shot) {
                            actionDone = true; // NUR bei echtem Schuss weiter!
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
            }
        }
    }
    private Team swapCurrentTeam(Team t) {
        return (t == teamt1) ? teamt2 : teamt1;
    }
}