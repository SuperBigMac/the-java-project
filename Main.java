import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int boardsize = 7;
    Board b = new Board(boardsize);
    
    Player player_1 = new Bob(0, 0, 1);
    Player player_2 = new Keegan(boardsize-1,boardsize-1, -1);

    player_1.setScanner(sc);
    player_2.setScanner(sc);
    
    final Player[] moveOrder = new Player[]{player_1, player_2, player_2, player_1};

    //repeat the game 100000 times for testing
    int numGames = 100000;

    int wins_1 = 0;
    int wins_2 = 0;
    
    System.out.printf("\n\nSTART (%s vs. %s)\n", player_1.getName(), player_2.getName());
    long startTime = System.nanoTime();


    for(int A = 0; A < numGames; A++){

      //play the game
      for(int i=0;moveOrder[i%4].movesExist(b) && moveOrder[(i+2)%4].movesExist(b);i++){
        b = moveOrder[i%4].move(moveOrder[(i+2)%4].getX(), moveOrder[(i+2)%4].getY(), b);
        //System.out.print(b); //display board after every move
      }

      //log wins
      if(player_1.movesExist(b)){
        wins_1++;
      } else{
        wins_2++;
      }

      //Progress reports (comment out one)
      if(((int)numGames/100) > 0 && A % ((int)numGames/100) == 0){System.out.printf("\r%s%% completed", A/(int)(numGames/100));}
      //System.out.println("\nGame " + (A+1) + ":\n"+ b.finalBoard(player_1.getX(), player_1.getY(), player_2.getX(), player_2.getY(), "\u001B[31m", "\u001B[32m"));
      
      //reset everything for next game
      if(A<numGames-1){
        b = new Board(boardsize);
        player_1.resetPosition();
        player_2.resetPosition();
      }
    }
    
    System.out.printf("\r100%% completed in %.3fs", (System.nanoTime()-startTime)/1000000000.0); //kinda bothered me that it would end at 99% every time
    System.out.printf("\n\nBoard size: %s x %s\n# of games: %d\nPlayer 1 (%s) wins: %s (%.1f%%)\nPlayer 2 (%s) wins: %s (%.1f%%)\n\n", boardsize, boardsize, numGames, player_1.getName(), wins_1, (double)wins_1*100/(double)numGames, player_2.getName(), wins_2, (double)wins_2*100/(double)numGames);

    System.out.println("Final board:\n" + b.finalBoard(player_1.getX(), player_1.getY(), player_2.getX(), player_2.getY(), "\u001B[31m", "\u001B[32m"));

    sc.close();
  }
}