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

    protected Team checkGoal(Team team){
        return team;
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
}
