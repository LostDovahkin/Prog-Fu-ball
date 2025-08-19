package src.Gamelogic;

import src.TeamManagement.Player;
import src.TeamManagement.Team;

import java.lang.reflect.Array;

public class Gamefield {
    private GameObject[][] gamefieldArray;
    private Team left;
    private Team right;

    public Gamefield(Team left, Team right) {

        gamefieldArray = new GameObject[9][20];
    }

    protected boolean checkGoal(Ball ball) {
        if (ball.position.getY() == 0 && ball.position.getX() > 2 && ball.position.getX() < 6 ){
            right.addScore();
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
}
