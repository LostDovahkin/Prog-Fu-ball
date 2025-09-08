package Gamelogic;

import TeamManagement.Player;
import TeamManagement.Team;

public class Gamefield {
    private final GameObject[][] gamefieldArray;
    private final Team left;
    private final Team right;
    

    public Gamefield(Team left, Team right) {
        this.left = left;
        this.right = right;
        this.gamefieldArray = new GameObject[9][20];
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

    public boolean moveObject(GameObject object, int x, int y) {
        int posVertical = object.getPosition().getX();
        int posHorizontal = object.getPosition().getY();

        if (posHorizontal + x < 0 || posHorizontal + x >= gamefieldArray.length) {
            System.out.println("Ungültige Position");
            return false;
        }
        if (posVertical + y < 0 || posVertical + y >= gamefieldArray[0].length) {
            System.out.println("Ungültige Position");
            return false;
        }

        GameObject newPos = gamefieldArray[posVertical + x][posHorizontal + y];

        if (newPos == null || newPos instanceof Ball && object instanceof Player) {
            if (object instanceof Player && newPos != null) {
                ((Ball) newPos).setHolder((Player) object);
            }
            gamefieldArray[posVertical][posHorizontal] = null;
            object.setPosition(posVertical + x, posHorizontal + y);
            gamefieldArray[object.getPosition().getX()][object.getPosition().getY()] = object;
            return true;

        } else if (newPos instanceof Player && object instanceof Ball) {
            object.setPosition(object.getPosition().getX() + x, object.getPosition().getY() + y);
            ((Player) newPos).setHasBall((Ball) object);
            return true;
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

    public void checkAndHandleGoal(Ball b){
        if (!checkGoal(b)) return;

        Team scorer = detectScorer(b);
        if (scorer != null) {
            scorer.addScore();
            System.out.println(
            "TOR für Team " + (scorer == left ? "LINKS" : "RECHTS")
            + "! Neuer Spielstand: " + left.getScore() + " : " + right.getScore()
            );
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