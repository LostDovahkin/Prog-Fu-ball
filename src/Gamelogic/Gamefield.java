package src.Gamelogic;

import java.util.ArrayList;
import java.util.List;
import src.TeamManagement.Player;
import src.TeamManagement.Team;

public class Gamefield {
    private final GameObject[][] gamefieldArray;
    private final Team left;
    private final Team right;

    private Ball ball;
    private final List<Team> goalEvent = new ArrayList<>();

    public Gamefield(Team left, Team right) {
        this.left = left;
        this.right = right;
        this.gamefieldArray = new GameObject[9][20];
    }

    protected boolean checkGoal(Ball b) {
        if (b == null) { 
        return false; 
        }
        
        int v = ball.getPosVertical();
        int h = ball.getPosHorizontal();
        int lastCol = gamefieldArray[0].length - 1;

        boolean inGate = (v > 2 && v < 6);
        if(!inGate){
            return false;
        }

        if(h == 0){
            goalEvent.add(right);
            return true;
        }
        if(h == lastCol){
            goalEvent.add(left);
            return true;
        }
        return false;
    }

    public boolean addGameObject (GameObject object,int vertical, int horizontal){
        if (vertical < 0 || vertical >= gamefieldArray.length || horizontal < 0 || horizontal >= gamefieldArray[0].length) {
            return false;
        }
        if(gamefieldArray[vertical][horizontal]==null){
            gamefieldArray[vertical][horizontal]=object;
            object.setPosVertical(vertical);
            object.setPosHorizontal(horizontal);
            
            if (object instanceof Ball) {
            this.ball = (Ball) object;
            }
            return true;
        }else{
            return false;
        }
    }
      public void printField(){

          for (GameObject[] subArray : gamefieldArray) {
              for (GameObject gameObject : subArray) {
                  if(gameObject==null){
                      System.out.print("[  ]");
                  }else {
                      System.out.print("["+gameObject+ " ]");
                  }
              }
              System.out.println(); // Move to the next line after each row
          }
    }
    public void moveObject(GameObject object, int vertical, int horizontal){
        int posVertical = object.getPosVertical();
        int posHorizontal = object.getPosHorizontal();
        GameObject newPos = gamefieldArray[posVertical+vertical][posHorizontal+horizontal];

        if (newPos == null) {
            gamefieldArray[posVertical][posHorizontal] = null;
            gamefieldArray[posVertical+vertical][posHorizontal+horizontal] = object;
            object.setPosVertical(posVertical+vertical);
            object.setPosHorizontal(posHorizontal+horizontal);
        }
        else if (newPos instanceof Ball && object instanceof Player) {
            ((Ball) newPos).setHolder((Player) object);
            gamefieldArray[posVertical][posHorizontal] = null;
            gamefieldArray[posVertical+vertical][posHorizontal+horizontal] = object;
            object.setPosVertical(posVertical+vertical);
            object.setPosHorizontal(posHorizontal+horizontal);
        }
        postTurnStatus(object instanceof Player ? (Player) object : null);
        /*
        if (newPos == null || newPos instanceof Ball && object instanceof Player){
          if (object instanceof Player && newPos instanceof Ball){
              ((Ball) newPos).setHolder((Player) object);
              gamefieldArray[posVertical][posHorizontal] = null;
            }
        }
        */

    }

    private void resetAfterGoal(){
        if (ball == null) return;

        int v = ball.getPosVertical();
        int h = ball.getPosHorizontal();
        
        if (v >= 0 && v < gamefieldArray.length && h >= 0 && h < gamefieldArray[0].length && gamefieldArray[v][h] == ball) {
            gamefieldArray[v][h] = null;
        }

        ball.releaseHolder();

        int centerV = gamefieldArray.length / 2;
        int centerH = gamefieldArray[0].length / 2;
        addGameObject(ball, centerV, centerH);

    }

    private void postTurnStatus(Player movedPlayer){
        //Tor
        if (checkGoal(ball)) {
            Team scorer = goalEvent.get(goalEvent.size() - 1);
            System.out.println("TOR für Team " + (scorer == left ? "LINKS" : "RECHTS") + "! Neuer Spielstand: " + getScoreLeft() + " : " + getScoreRight());
            resetAfterGoal();
        }

        String possession = "Keiner";
            if (ball != null && ball.getHolder() != null) {
            possession = "Spieler " + ball.getHolder();
        }

        //Ballbesitz
        String energyInfo = (movedPlayer != null)
            ? ("Energie von " + movedPlayer + ": " + movedPlayer.getEnergy())
            : "Energie: n/a";

        //Energie
        System.out.println("Status ▸ Ballbesitz: " + possession
            + " | " + energyInfo
            + " | Spielstand: " + getScoreLeft() + " : " + getScoreRight());
    }

    public int getScoreLeft() {
        return countGoalsRecursive(goalEvent, left, 0);
    }

    public int getScoreRight() {
        return countGoalsRecursive(goalEvent, right, 0);
    }

    private int countGoalsRecursive(List<Team> events, Team team, int idx) {
        if (idx >= events.size()) return 0;
        int add = (events.get(idx) == team) ? 1 : 0;
        return add + countGoalsRecursive(events, team, idx + 1);
    }
    
    public void printScore() {
        System.out.println("Spielstand: " + getScoreLeft() + " : " + getScoreRight());
    }
}
