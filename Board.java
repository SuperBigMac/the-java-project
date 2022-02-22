
public class Board {
  //instance variables
  private int size;
  private int[][] board;
  

  //constructors
  public Board(int size){
    this.size = size;
    this.board = new int[size][size];
    for(int i=0; i<size; i++){
      for(int k=0; k<size; k++){
        board[i][k] = 0;
      }
    }
    board[0][0] = 1;
    board[size-1][size-1] = -1;
  }

  public Board(int[][] values){
    this.size = values.length;
    this.board = values;
  }

  //methods
  public int[][] getValues(){
    return this.board;
  }

  /*
    0 = square not taken
    1 = self
    -1 = opponent
  */
  public void move(int x, int y, int replaceVal){
    board[x][y] = replaceVal;
  }

  public boolean isTaken(int x, int y){
    return board[x][y]!=0;
  }

  public boolean isValidMove(int checkX, int checkY, int headX, int headY){
    
    //preventing array OOB exceptions
    if((checkX<0 || checkX >= this.size) || (checkY<0 || checkY >= this.size) || (headX<0 || headX>=this.size) || (headY<0 || headY>=this.size)){
      return false;
    }
    if(Math.abs(checkX-headX)>1 || Math.abs(checkY-headY)>1){
      return false;
    }
    if(checkX==headX && checkY==headY){
      return false;
    }
    return !(this.isTaken(checkX, checkY));
  }
  
  public Board opponentView(){
    int[][] opponentVals = new int[this.size][this.size];

    for(int i=0; i<this.size; i++){
      for(int k=0; k<this.size; k++){
        opponentVals[k][i] = this.board[this.size-1-k][this.size-1-i] * -1;
      }
    }
    
    return new Board(opponentVals);
  }

  public void occupySpace(int x, int y) throws ArrayIndexOutOfBoundsException{
    this.board[x][y] = 9; //random nonzero int
  }


  public int count(int val){
    int num = 0;
    for(int x=0; x<board.length; x++){
      for(int y=0; y<board[x].length;y++){
        if(board[x][y]==val){
          num++;
        }
      }
    }

    return num;
  }
  
  public boolean isTied(){
    return (this.count(-1) == this.count(1));
  }

  public String toString(){
    String s = "";
    for(int i=0; i<this.size; i++){
      for(int k=0; k<this.size; k++){
        if(this.board[k][i]==1){
          s = s + "A" + " ";
        } else if(this.board[k][i]==-1){
          s = s + "B" + " ";
        } else {
          s = s + "_" + " ";
        }
        
      }
      s = s + "\n";
    }
    return s;
  }

  /*
    color1 and color2 are ANSI escape sequences for colors
  */
  public String toStringWithColor(int x1, int y1, int x2, int y2, String color1, String color2){
    String s = "";
    for(int i=0; i<this.size; i++){
      for(int k=0; k<this.size; k++){
        if(this.board[k][i]==1){
          if((k == x1 && i == y1) || (k == x2 && i == y2)){
            s = s + color1 + "A" + "\u001B[0m ";
          } else {
            s = s + "A" + " ";
          }
        } else if(this.board[k][i]==-1){
          if((k == x1 && i == y1) || (k == x2 && i == y2)){
            s = s + color2 + "B" + "\u001B[0m ";
          } else {
            s = s + "B" + " ";
          }
        } else {
          s = s + "_" + " ";
        }
        
      }
      s = s + "\n";
    }
    return s;
  }

  public String finalBoard(int x1, int y1, int x2, int y2, String color1, String color2){
    String s = "";
    for(int i=0; i<this.size; i++){
      for(int k=0; k<this.size; k++){
        if(this.board[k][i]==1){
          if((k == x1 && i == y1) || (k == x2 && i == y2)){
            s = s + color1 + "\u001B[43m" + "A" + "\u001B[0m ";
          } else {
            s = s + color1 + "A" + "\u001B[0m ";
          }
        } else if(this.board[k][i]==-1){
          if((k == x1 && i == y1) || (k == x2 && i == y2)){
            s = s + color2 + "\u001B[43m" + "B" + "\u001B[0m ";
          } else {
            s = s + color2 + "B" + "\u001B[0m ";
          }
        } else {
          s = s + "_" + " ";
        }
        
      }
      s = s + "\n";
    }
    return s;
  }

  public Player getWinner(Player p1, Player p2){
    if(p1.movesExist(this)){
      return p1;
    } else if(p2.movesExist(this)){
      return p2;
    } else if(this.isTied()){
      return new Player("TIE");
    } else if(this.count(p1.getClaimVal())>this.count(p2.getClaimVal())){
      return p1;
    } else if(this.count(p1.getClaimVal())<this.count(p2.getClaimVal())){
      return p2;
    } else {
      System.out.println("How did we get here? D: \n");
      System.out.println(this.finalBoard(p1.getX(), p1.getY(), p2.getX(), p2.getY(), "\u001B[31m", "\u001B[32m"));
      System.out.println("A: "+this.count(p1.getClaimVal()));
      System.out.println("B: "+this.count(p2.getClaimVal()));
      System.exit(0);
      return new Player("");
    }
  }
}