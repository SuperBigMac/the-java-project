import java.util.concurrent.ThreadLocalRandom;


//Keegan, but instead of selecting the longest ray, it selects the shortest ray with nonzero length
public class DumbKeegan extends PlayerAlg{
  public DumbKeegan(int initX, int initY, int claimVal){
    super(initX, initY, claimVal, "Dumb Keegan");
  }

  public int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b){
    int[] checkX = new int[]{-1,-1,-1,0,0,1,1,1};
    int[] checkY = new int[]{-1,0,1,-1,1,-1,0,1};

    int[] rayLengths = new int[8];

    int minLength = Integer.MAX_VALUE;

    for(int i=0;i<8;i++){
      rayLengths[i] = raySize(checkX[i], checkY[i], b, playerX, playerY, 0);
      if(rayLengths[i] < minLength && rayLengths[i] > 0){
        minLength = rayLengths[i];
      }
    }

    int countMin = 0;
    for(int i=0; i<8; i++){
      if(rayLengths[i] == minLength){
        countMin++;
      }
    }

    int[] minX = new int[countMin];
    int[] minY = new int[countMin];
    int count = 0;
    for(int i=0; i<8; i++){
      if(rayLengths[i] == minLength){
        minX[count] = checkX[i];
        minY[count] = checkY[i];
        count++;
      }
    }

    if(minX.length==0){
      return new int[]{-1,-1};
    }
    int randomVal = (int) ThreadLocalRandom.current().nextDouble(0, countMin);

    return new int[]{minX[randomVal] + playerX, minY[randomVal] + playerY};
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