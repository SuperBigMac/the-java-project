import java.util.concurrent.ThreadLocalRandom;

public class Keegan extends PlayerAlg{
  
  public Keegan(int initX, int initY, int claimVal){
    super(initX, initY, claimVal, "Keegan");
  }

  public int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b){
    int[] checkX = new int[]{-1,-1,-1,0,0,1,1,1};
    int[] checkY = new int[]{-1,0,1,-1,1,-1,0,1};

    int[] rayLengths = new int[8];

    int maxLength = -1;

    for(int i=0;i<8;i++){
      rayLengths[i] = raySize(checkX[i], checkY[i], b, playerX, playerY, 0);
      if(rayLengths[i] > maxLength){
        maxLength = rayLengths[i];
      }
    }

    int countMax = 0;
    for(int i=0; i<8; i++){
      if(rayLengths[i] == maxLength){
        countMax++;
      }
    }

    int[] maxX = new int[countMax];
    int[] maxY = new int[countMax];
    int count = 0;
    for(int i=0; i<8; i++){
      if(rayLengths[i] == maxLength){
        maxX[count] = checkX[i];
        maxY[count] = checkY[i];
        count++;
      }
    }

    int randomVal = (int) ThreadLocalRandom.current().nextDouble(0, countMax);

    return new int[]{maxX[randomVal] + playerX, maxY[randomVal] + playerY};
  }
  
  public static int raySize(int incrementX, int incrementY, Board b, int playerX, int playerY, int rayLength){
    int checkX = playerX + incrementX;
    int checkY = playerY + incrementY;

    if(!b.isValidMove(checkX, checkY, playerX, playerY)){
      return rayLength;
    }

    return raySize(incrementX, incrementY, b, checkX, checkY, rayLength + 1);
  }

}