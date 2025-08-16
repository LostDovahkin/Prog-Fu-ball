package src.Gamelogic;

import src.TeamManagement.Team;

public class Gamefield {
    private GameObject[][] gamefieldArray;

    public Gamefield() {
        gamefieldArray = new GameObject[10][20];
    }

    protected Team checkGoal(Team team){
        return team;
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
}
