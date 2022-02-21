abstract class PlayerAlg extends Player{
  
  protected PlayerAlg(int initX, int initY, int claimVal, String name){
    super(initX, initY, claimVal, name);
  }

  abstract protected int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b);
  
  protected int[] getNextMove(int opponentX, int opponentY, Board b){
    return this.getNextMove(super.x, super.y, opponentX, opponentY, b);
  }

  public Board move(int opponentX, int opponentY, Board b){
    int[] moves = this.getNextMove(super.x, super.y, opponentX, opponentY, b);
    return super.move(moves[0], moves[1], b);
  }
}