import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Michael extends PlayerAlg{

  public Michael(int initX, int initY, int claimVal){
    super(initX, initY, claimVal, "Michael");
  }

  protected int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b){
    ArrayList<Integer> validX = new ArrayList<Integer>();
    ArrayList<Integer> validY = new ArrayList<Integer>();

    for(int i=0; i<8; i++){
      if(b.isValidMove(playerX+CHECK_X[i], playerY+CHECK_Y[i], playerX, playerY)){
        validX.add(playerX+CHECK_X[i]);
        validY.add(playerY+CHECK_Y[i]);
      }
    }

    int maxDist = -1;
    for(int i=0; i<validX.size(); i++){
      int distance = (int) dist(validX.get(i), validY.get(i), opponentX, opponentY);
      if(distance>maxDist){
        maxDist = distance;
      }
    }
    ArrayList<Integer> maxX = new ArrayList<Integer>();
    ArrayList<Integer> maxY = new ArrayList<Integer>();
    
    for(int i=0; i<validX.size(); i++){
      if((int) dist(validX.get(i), validY.get(i), opponentX, opponentY)==maxDist){
        maxX.add(validX.get(i));
        maxY.add(validY.get(i));
      }
    }
    //choosing random value from distances if there is a tie
    if(maxX.size()==0){
      return new int[]{-1,-1};
    }
    int randomVal = (int) ThreadLocalRandom.current().nextInt(0, maxX.size());
    return new int[]{maxX.get(randomVal), maxY.get(randomVal)};
  }

  private static double dist(int x1, int y1, int x2, int y2){
    return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
  }
}