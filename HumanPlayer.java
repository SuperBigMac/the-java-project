
//For playing the game w/ user input
class HumanPlayer extends PlayerAlg{

  static final String COLOR_A = "\u001B[31m"; //red
  static final String COLOR_B = "\u001B[32m"; //green

  public HumanPlayer(int initX, int initY, int claimVal){
    super(initX, initY, claimVal, "Human");
  }

  public int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b){
    boolean firstRun = true;
    int inputX = -1;
    int inputY = -1;

    do{
      try{
        if(!firstRun){
          System.out.printf("(%s, %s) is an invalid move. Please try again\n\n", inputX, inputY);
        }
        System.out.println(b.toStringWithColor(this.x, this.y, opponentX, opponentY, COLOR_A, COLOR_B));
        System.out.printf("x %s: ", this.getName());
        inputX = Integer.valueOf(sc.nextLine().replaceAll("[^\0-9]", ""));
        System.out.printf("y %s: ", this.getName());
        inputY = Integer.valueOf(sc.nextLine().replaceAll("[^\0-9]", ""));

        firstRun = false;
      } catch(Exception e){
        System.out.println("\nBad input. Please try again\n");
        //e.printStackTrace(); //System.exit(0);  //for debugging
      }
    } while(!b.isValidMove(inputX,inputY,this.x,this.y));

    return new int[]{inputX,inputY};
  }
}






