import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int boardsize = 7;
    Board b = new Board(boardsize);
    
    Player player_1 = new Bob(0, 0, 1);
    Player player_2 = new Bob(boardsize-1,boardsize-1, -1);
    
    final Player[] moveOrder = new Player[]{player_1, player_2, player_2, player_1};

    //repeat the game 100000 times
    int numGames = 100000;

    int wins_1 = 0;
    int wins_2 = 0;
    
    System.out.printf("\n\nSTART (%s vs. %s)\n", player_1.getName(), player_2.getName());
    long startTime = System.nanoTime();

    for(int A = 0; A < numGames; A++){

      //play the game
      for(int i=0;moveOrder[i%4].movesExist(b) && moveOrder[(i+1)%4].movesExist(b);i++){
        b = moveOrder[i%4].move(moveOrder[(i+2)%4].getX(), moveOrder[(i+2)%4].getY(), b);
        //System.out.print(b); //display board after every move
      }

      //log wins
      if(player_1.movesExist(b)){
        wins_1++;
      } else{
        wins_2++;
      }

      //Progress reports
      if(A % ((int)numGames/100) == 0){
        System.out.printf("\r%s%% completed", A/(int)(numGames/100));
      }

      //reset everything for next game
      b = new Board(boardsize);
      player_1.resetPosition();
      player_2.resetPosition();
    }
    
    System.out.printf("\r100%% completed in %.3fs", (System.nanoTime()-startTime)/1000000000.0); //kinda bothered me that it would end at 99% every time
    System.out.printf("\n\nBoard size: %s x %s\n# of games: %d\nPlayer 1 (%s) wins: %s (%.1f%%)\nPlayer 2 (%s) wins: %s (%.1f%%)\n\n", boardsize, boardsize, numGames, player_1.getName(), wins_1, (double)wins_1*100/(double)numGames, player_2.getName(), wins_2, (double)wins_2*100/(double)numGames);

    sc.close();
  }

  public static int[] getValidUserInput(Player currentPlayer, Board b, Scanner sc){
    boolean firstRun = true;
    int x = -1;
    int y = -1;

    do{
      try{
        if(!firstRun){
          System.out.println("Invalid move. Please try again\n");
        }
        System.out.println(b.toString());
        System.out.printf("x %s: ", currentPlayer.getName());
        x = Integer.valueOf(sc.nextLine().replaceAll("[^\0-9]", ""));
        System.out.printf("y %s: ", currentPlayer.getName());
        y = Integer.valueOf(sc.nextLine().replaceAll("[^\0-9]", ""));

        firstRun = false;
      } catch(Exception e){
        System.out.println("Bad input. Please try again\n");
      }
    } while(!b.isValidMove(x,y,currentPlayer.getX(),currentPlayer.getY()));

    return new int[]{x,y};
  }
}