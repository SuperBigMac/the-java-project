import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Bob extends PlayerAlg{
  public Bob(int initX, int initY, int claimVal){
    super(initX, initY, claimVal, "Bob");
  }

  public int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b){
    int[] checkX = new int[]{-1,-1,-1,0,0,1,1,1};
    int[] checkY = new int[]{-1,0,1,-1,1,-1,0,1};

    ArrayList<Integer> validX = new ArrayList<Integer>();
    ArrayList<Integer> validY = new ArrayList<Integer>();

    for(int i=0; i<8; i++){
      if(b.isValidMove(playerX+checkX[i], playerY+checkY[i], playerX, playerY)){
        validX.add(playerX+checkX[i]);
        validY.add(playerY+checkY[i]);
      }
    }

    if(validX.size()==0){
      return new int[]{-1,-1};
    }

    int randomVal = (int) ThreadLocalRandom.current().nextDouble(0, validX.size());

    return new int[]{validX.get(randomVal), validY.get(randomVal)};
  }
}