import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int boardsize = 7;
    Board b = new Board(boardsize);
    
    Player player_1 = new Michael(0, 0, 1);
    Player player_2 = new Bob(boardsize-1,boardsize-1, -1);

    player_1.setScanner(sc);
    player_2.setScanner(sc);
    
    //final Player[] moveOrder = new Player[]{player_1, player_2, player_2, player_1};

    //repeat the game 100000 times for testing
    int numGames = 1;

    int wins_1 = 0;
    int wins_2 = 0;
    int ties = 0;
    
    System.out.printf("\n\nSTART (%s vs. %s)\n", player_1.getName(), player_2.getName());
    long startTime = System.nanoTime();


    for(int A = 0; A < numGames; A++){

      //play the game (less elegant than the old one, but is better for tie checking)
      for(int i=0;player_1.movesExist(b)&&player_2.movesExist(b);i++){
        if(i%2==0){
          player_1.move(player_2.getX(), player_2.getY(), b);
          player_2.move(player_1.getX(), player_1.getY(), b);
        } else{
          player_2.move(player_1.getX(), player_1.getY(), b);
          player_1.move(player_2.getX(), player_2.getY(), b);
        }
      }

      //log wins, checking for ties
      if(b.getWinner(player_1, player_2).getClaimVal()==-1){
        wins_2++;
      } else if(b.getWinner(player_1, player_2).getClaimVal()==1){
        wins_1++;
      } else if(b.getWinner(player_1, player_2).getClaimVal()==0){
        ties++;
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
    System.out.printf("\n\nBoard size: %s x %s\n# of games: %d\nPlayer 1 (%s) wins: %s (%.1f%%)\nPlayer 2 (%s) wins: %s (%.1f%%)\nTies: %s (%.1f%%)\n\n", boardsize, boardsize, numGames, player_1.getName(), wins_1, (double)wins_1*100/(double)numGames, player_2.getName(), wins_2, (double)wins_2*100/(double)numGames, ties, (double)ties*100/(double)numGames);

    System.out.println("Final board:\n" + b.finalBoard(player_1.getX(), player_1.getY(), player_2.getX(), player_2.getY(), "\u001B[31m", "\u001B[32m"));
    System.out.println("A: " + b.count(player_1.getClaimVal()));
    System.out.println("B: " + b.count(player_2.getClaimVal()));
    System.out.println(b.getWinner(player_1, player_2));
    sc.close();
  }
}