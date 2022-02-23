abstract class PlayerAlg extends Player {
  protected static final int[] CHECK_X = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
  protected static final int[] CHECK_Y = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

  protected PlayerAlg(int initX, int initY, int claimVal, String name) {
    super(initX, initY, claimVal, name);
  }

  abstract protected int[] getNextMove(int playerX, int playerY, int opponentX, int opponentY, Board b);

  protected int[] getNextMove(int opponentX, int opponentY, Board b) {
    return this.getNextMove(super.x, super.y, opponentX, opponentY, b);
  }

  public Board move(int opponentX, int opponentY, Board b) {
    int[] moves = this.getNextMove(super.x, super.y, opponentX, opponentY, b);
    return super.move(moves[0], moves[1], b);
  }

  public Board move(Player opponent, Board b) {
    return this.move(opponent.getX(), opponent.getY(), b);
  }
}