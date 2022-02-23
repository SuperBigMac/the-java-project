import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Keegan3 extends PlayerAlg{
  public Keegan3(int initX, int initY, int claimVal){
    super(initX, initY, claimVal, "Keegan3");
  }

  public int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b){
    int[] checkX = new int[]{-1,-1,-1,0,0,1,1,1};
    int[] checkY = new int[]{-1,0,1,-1,1,-1,0,1};

    return new int[]{1};
  }
  
  private int path(int x, int y, int steps, Board b){
    return Keegan3.path(this.x, this.y, x, y, steps, b);
  }

  //returns steps left over to get from one tile to another
  public static int path(int xP, int yP, int xF, int yF, int steps, Board b){
    //if tile is out of reach, no point in checking if opponent is blocking
    if(steps<Keegan3.moveDist(xP, yP, xF, yF)){
      return -1;
    }
    if(!b.tileExists(xP,yP)){
      return -1;
    }
    if(b.isTaken(xP, yP)){
      return -1;
    }
    if(xP==xF && yP==yF){
      return steps;
    }
    if(steps==0){
      return -1;
    }

    else {
      int checkX, checkY;
      int result = -1;
      int highestResidual = -1;

      for(int i=0; i<CHECK_X.length;i++){
        for(int k=0; k<CHECK_Y.length;k++){
          checkX = xP + CHECK_X[i];
          checkY = yP + CHECK_Y[k];

          if(b.isValidMove(checkX, checkY, xP, yP)){
            if(highestResidual == -1){
              highestResidual = path(xP + CHECK_X[i], yP + CHECK_Y[k], xF, yF, steps-1, b);
            } else {
              result = path(xP + CHECK_X[i], yP + CHECK_Y[k], xF, yF, (steps-highestResidual)-1, b);
              if(result > 0){
                highestResidual += result;
              }
            }
          }
        }
      }
      return highestResidual;
    }
  }

  public static int path2(int xP, int yP, int xF, int yF, int steps, Board b){
    int half = steps/2;
    int half2 = steps-half;
    return -1;
    
  }
  
  public static int pathOptimized(int xP, int yP, int xF, int yF, int steps, Board b){
    int result = -1;
    int i = 0;
    while(i<=steps){
      result = path(xP, yP, xF, yF, i, b); 
      if(result == 0){
        return(steps-i);
      }
      i++;
    }
    
    return -1; //no path was found in allotted moves
  }

  public static int pathPreOptimization(int xP, int yP, int xF, int yF, int steps, Board b){
    //if tile is out of reach, no point in checking if opponent is blocking
    if(steps<Keegan3.moveDist(xP, yP, xF, yF)){
      return -1;
    }
    if(b.isTaken(xP, yP)){
      return -1;
    }
    if(xP==xF && yP==yF){
      return steps;
    } 
    if(steps==0){
      return -1;
    }

    else {
      ArrayList<Integer> residual = new ArrayList<Integer>();
      int checkX, checkY;
      int result = -1;
      for(int i=0; i<CHECK_X.length;i++){
        for(int k=0; k<CHECK_Y.length;k++){
          checkX = xP + CHECK_X[i];
          checkY = yP + CHECK_Y[k];
          if(b.isValidMove(checkX, checkY, xP, yP)){
            result = pathPreOptimization(xP + CHECK_X[i], yP + CHECK_Y[k], xF, yF, steps-1, b);
            residual.add(result);
          }
        }
      }
      //System.out.println(Arrays.toString(residual.toArray()));
      return Collections.max(residual);
    }
  }

  public static int movesToTile(int xP, int yP, int xF, int yF, int maxSteps, Board b){
    int result = -1;
    int i = 0;
    while(i<=maxSteps){
      result = path(xP, yP, xF, yF, i, b); 
      if(result == 0){
        return(i);
      }
      i++;
    }

    return -1;
  }

  //returns number of moves required to get from point ()
  public static int moveDist(int x1, int y1, int x2, int y2){
    int deltaX = Math.abs(x1-x2);
    int deltaY = Math.abs(y1-y2);
    return (Math.min(deltaX, deltaY) + Math.abs(deltaX-deltaY));
  }

  //closest tile to (x1,y1) within radius of (x2,y2)
  public static int closestTileWithRadius(int x1, int y1, int x2, int y2, int radius){
    return -1;
  }
}