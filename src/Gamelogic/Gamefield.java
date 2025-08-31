package src.Gamelogic;

import src.TeamManagement.Player;
import src.TeamManagement.Team;

import java.util.ArrayList;
import java.util.List;

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

        int v = ball.getPosition().getX();
        int h = ball.getPosition().getY();
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

    public boolean addGameObject (GameObject object,int x, int y){
        if(gamefieldArray[x][y]==null){
            gamefieldArray[x][y]=object;
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
    public boolean moveObject(GameObject object, int x, int y){
        int posVertical = object.getPosition().getX();
        int posHorizontal = object.getPosition().getY();

        if (posHorizontal+x < 0 || posHorizontal+x >= gamefieldArray.length){
            System.out.println("Ungültige Position");
            return false;
        }
        if (posVertical+y < 0 || posVertical+y >= gamefieldArray[0].length){
            System.out.println("Ungültige Position");
            return false;
        }

        GameObject newPos = gamefieldArray[posVertical+x][posHorizontal+y];

      if (newPos == null || newPos instanceof Ball && object instanceof Player){
          if (object instanceof Player && newPos != null){
              ((Ball) newPos).setHolder((Player) object);
              gamefieldArray[posVertical][posHorizontal] = null;
          }
          object.setPosition(posVertical + x,posHorizontal + y);
          gamefieldArray[object.getPosition().getX()][object.getPosition().getY()] = object;
          return true;

      } else if (newPos instanceof Player && object instanceof Ball) {
          object.setPosition(object.getPosition().getX()+x,object.getPosition().getY()+y);
          ((Player) newPos).setHasBall((Ball) object);
          return true;
      }
        return false;
            }



    private void resetAfterGoal(){
        if (ball == null) return;

        int v = ball.getPosition().getX();
        int h = ball.getPosition().getY();

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
