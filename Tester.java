import java.util.concurrent.ThreadLocalRandom;

public class Tester{
  public static void main(String[] args){
    for(int i=0; i<40; i++){
      System.out.print("\u001B[31m\u001B[43m"+ThreadLocalRandom.current().nextInt(0, 2)+"\u001B[0m");
    }
  }
}