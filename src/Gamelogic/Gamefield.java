package src.Gamelogic;

import src.TeamManagement.Player;
import src.TeamManagement.Team;

public class Gamefield {
    private GameObject[][] gamefieldArray;
    private Team left;
    private Team right;

    public Gamefield(Team left, Team right) {

        gamefieldArray = new GameObject[9][20];
    }

    protected boolean checkGoal(Ball ball) {
        if (ball.posHorizontal == 0 && ball.posVertical > 2 && ball.posVertical < 6 ){
            right.addScore();
            return true;
        }

        return false;
    }

    public boolean addGameObject (GameObject object,int vertical, int horizontal){
        if(gamefieldArray[vertical][horizontal]==null){
            gamefieldArray[vertical][horizontal]=object;
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

      if (newPos == null || newPos instanceof Ball && object instanceof Player){
          if (object instanceof Player && newPos instanceof Ball){
              ((Ball) newPos).setHolder((Player) object);
              gamefieldArray[posVertical][posHorizontal] = null;

          }
      }

    }

    private void resetAfterGoal(){
        if (ball == null) return;

        int v = ball.getPosVertical();
        int h = ball.getPosHorizontal();
        
        if (v >= 0 && v < gamefieldArray.length && h >= 0 && h < gamefieldArray[0].length && gamefieldArray[v][h] == ball) {
            gamefieldArray[v][h] = null;
        }

        ball.releaseHolder();
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
