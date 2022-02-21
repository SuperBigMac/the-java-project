class Player{
  //instance vars
  protected int claimVal;
  protected int x;
  protected int y; // the "head"
  protected String name;
  
  final int initialX, initialY;


  //constructor
  public Player(int initX, int initY, int claimVal, String name){
    this.x = initX;
    this.y = initY;

    this.claimVal = claimVal;
    this.name = name;

    this.initialX = initX;
    this.initialY = initY;
  }
  
  //methods
  public String getName(){
    return this.name;
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  /**
  
  */
  public Board move(int chosenX, int chosenY, Board b){
    if(b.isValidMove(chosenX, chosenY, this.x, this.y)){
      b.move(chosenX, chosenY, this.claimVal);
      this.x = chosenX;
      this.y = chosenY;
    }
    return b;
  }

  public boolean movesExist(Board b){
    for (int i=-1; i<2; i++){
      for (int k=-1; k<2; k++){
        if (b.isValidMove((this.x)+i, (this.y)+k, this.x, this.y)){
          return true;  
        }
      }
    } 
    return false;          
  }

  public void resetPosition(){
    this.x = this.initialX;
    this.y = this.initialY;
  }

}