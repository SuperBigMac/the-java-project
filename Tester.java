import java.util.concurrent.ThreadLocalRandom;

public class Tester{
  public static void main(String[] args){
    for(int i=0; i<40; i++){
      System.out.print(ThreadLocalRandom.current().nextInt(0, 2));
    }
  }
}