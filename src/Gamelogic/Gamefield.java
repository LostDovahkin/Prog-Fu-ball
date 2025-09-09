package Gamelogic;

import TeamManagement.Player;
import TeamManagement.Team;
import java.io.Serializable;

public class Gamefield implements Serializable{
    private final GameObject[][] gamefieldArray;
    private final Team left;
    private final Team right;
    

    public Gamefield(Team left, Team right) {
        this.left = left;
        this.right = right;
        this.gamefieldArray = new GameObject[9][21];
    }

    public int getFieldWidth() {
        return gamefieldArray.length;
    }

    public int getFieldHeight() {
        return gamefieldArray[0].length;
    }
    public GameObject getObjectAt(int x, int y) {
        if (x < 0 || x >= gamefieldArray.length || y < 0 || y >= gamefieldArray[0].length) {
            return null;
        }
        return gamefieldArray[x][y];
    }


    private boolean checkGoal(Ball b) {
        if (b == null) {
            return false;
        }

        int v = b.getPosition().getX();
        int h = b.getPosition().getY();
        int lastCol = gamefieldArray[0].length - 1;

        boolean inGate = (v > 2 && v < 6);
        if (!inGate) return false;

        return (h == 0) || (h == lastCol);
    }

    public boolean addGameObject(GameObject object, int x, int y) {
        if (gamefieldArray[x][y] == null) {
            gamefieldArray[x][y] = object;
            return true;
        } else {
            return false;
        }
    }

    public void printField() {

        for (GameObject[] subArray : gamefieldArray) {
            for (GameObject gameObject : subArray) {
                if (gameObject == null) {
                    System.out.print("[  ]");
                } else {
                    System.out.print("[" + gameObject + " ]");
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }

    public boolean moveObject(GameObject object, int dx, int dy) {
        int posX = object.getPosition().getX();
        int posY = object.getPosition().getY();
        int newX = posX + dx;
        int newY = posY + dy;

        // Grenzprüfung: X (Zeile), Y (Spalte)
        if (newX < 0 || newX >= gamefieldArray.length) return false;
        if (newY < 0 || newY >= gamefieldArray[0].length) return false;

        GameObject newPos = gamefieldArray[newX][newY];

        // === Spieler bewegt sich ===
        if (object instanceof Player) {
            // Spieler läuft auf Ballfeld: Ball übernehmen
            if (newPos instanceof Ball) {
                Ball foundBall = (Ball) newPos;
                foundBall.setHolder((Player) object);
                gamefieldArray[posX][posY] = null;
                object.setPosition(newX, newY);
                gamefieldArray[newX][newY] = object;
                return true;
            }
            // Normale Bewegung auf freies Feld
            if (newPos == null) {
                gamefieldArray[posX][posY] = null;
                object.setPosition(newX, newY);
                gamefieldArray[newX][newY] = object;
                return true;
            }
        }

        // === Ball bewegt sich (z.B. Schuss) ===
        if (object instanceof Ball) {
            // Ball schießt auf Spieler: Spieler bekommt Ballbesitz, Spieler bleibt im Array!
            if (newPos instanceof Player) {
                object.setPosition(newX, newY);
                ((Player) newPos).setHasBall((Ball) object);
                // Spielfeldarray NICHT überschreiben, Spieler bleibt am Feld!
                return true;
            }
            // Ball schießt auf leeres Feld: NUR Ball bewegen!
            if (newPos == null) {
                gamefieldArray[posX][posY] = null;
                object.setPosition(newX, newY);
                gamefieldArray[newX][newY] = object;
                return true;
            }
        }

        return false;
    }
    private Team detectScorer(Ball b) {
        int h = b.getPosition().getY();
        int lastCol = gamefieldArray[0].length - 1;
        if (h == 0) return right;       // Ball über linke Linie → Punkt für rechts
        if (h == lastCol) return left;  // Ball über rechte Linie → Punkt für links
        return null;
    }
    public void resetField(Ball ball) {
        // 1. Alles löschen
        for (int x = 0; x < gamefieldArray.length; x++) {
            for (int y = 0; y < gamefieldArray[0].length; y++) {
                gamefieldArray[x][y] = null;
            }
        }

        // 2. Ball in die Mitte
        ball.resetPosition();
        addGameObject(ball, ball.getPosition().getX(), ball.getPosition().getY());

        // 3. Beide Teams zurücksetzen
        for (Player p : left.getPlayers()) {
            if (p != null) {
                Position start = p.getStartPosition(); // ← Muss die KONSTRUKTIONS-Startposition sein!
                p.setPosition(start.getX(), start.getY());
                addGameObject(p, start.getX(), start.getY());
            }
        }
        for (Player p : right.getPlayers()) {
            if (p != null) {
                Position start = p.getStartPosition();
                p.setPosition(start.getX(), start.getY());
                addGameObject(p, start.getX(), start.getY());
            }
        }
    }
    public void checkAndHandleGoal(Ball b){
        if (!checkGoal(b)) return;

        Team scorer = detectScorer(b);
        if (scorer != null) {
            scorer.addScore();
            System.out.println(
            "TOR für Team " + (scorer == left ? "LINKS" : "RECHTS")
            + "! Neuer Spielstand: " + left.getScore() + " : " + right.getScore()
            ); 
            resetField(b);
        }
    }

    public void postTurnStatus(Player movedPlayer, Ball b) {
        String possession = "Keiner";
        if (b != null && b.getHolder() != null) {
            possession = "Spieler " + b.getHolder();
        }

        String energyInfo = (movedPlayer != null)
                ? ("Energie von " + movedPlayer + ": " + movedPlayer.getEnergy())
                : "Energie: n/a";

        System.out.println("Status ▸ Ballbesitz: " + possession
                + " | " + energyInfo
                + " | Spielstand: " + left.getScore() + " : " + right.getScore());
    }

    public void printScore() {
        System.out.println("Spielstand: " + left.getScore() + " : " + right.getScore());
    }
}