
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
}