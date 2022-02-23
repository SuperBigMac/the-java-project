import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.util.Arrays;


public class Tester{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    
    int boardsize = 7;

    //String setupString = "A---AAA------BBB";
    String setupString = "A-------AA-A-----AA---BAB-----BAB----B--B---B---B";

    Board b = new Board(boardsize, setupString);
    
    

    int x1 = 2;
    int y1 = 2;
    int x2 = 4;
    int y2 = 5;

    int maxMoves = 10;

    long startTime = System.nanoTime();
    int result = Keegan3.movesToTile(x1, y1, x2, y2, maxMoves, b);
    System.out.printf("\nCompleted in %.3fs. Result: %s. Maximum moves: %s\n", (System.nanoTime()-startTime)/1000000000.0, result, maxMoves);
    System.out.printf("Point 1: (%s, %s)\nPoint 2: (%s, %s)\n", x1, y1, x2, y2, maxMoves);
    System.out.println(b.toString());
    //System.out.println("\n\nOptimized— calculates min moves, then uses that to calculate residual\n");
  }
}