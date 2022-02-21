import java.util.concurrent.ThreadLocalRandom; //Random number generator

public class Node {
  //fields
  double[] weights;
  double bias;

  //constructors

  //weights and bias are known
  public Node(double[] weights, double bias){
    this.weights = weights;
    this.bias = bias;
  }

  //size = # of weights
  public Node(double weightRange, double biasRange, int size){
    this(weightRange*-1, weightRange, ThreadLocalRandom.current().nextDouble(biasRange*-1, biasRange), size);
  }

  public Node(double minWeight, double maxWeight, double bias, int size){
    this.weights = new double[size];

    for(int i=0; i<size; i++){
      weights[i] = ThreadLocalRandom.current().nextDouble(minWeight, maxWeight);
    }

    this.bias = bias;
  }

  //size = # of weights
  public Node(int size){
    this(-1,1,1,size);
  }
  

  //methods
  //sets the bias
  public void setBias(double newBias){
    this.bias = newBias;
  }

  //sets the weights
  public void setWeights(double[] weights){
    this.weights = weights;
  }

  //returns the size of the weights array
  public int getNumWeights(){
    return weights.length;
  }

  //takes each weight and adds/subtracts a random number within the specified range
  public void changeWeights(double weightChangeRange) {
    for(int i=0; i<weights.length; i++) {
      weights[i] = weights[i] + ThreadLocalRandom.current().nextDouble(weightChangeRange * -1, weightChangeRange);
    }
  }

  //returns the normalized output of the node given an input
  public double getNormalizedOutput(double[] input){
    double count = 0;

    for(int i=0; i<input.length && i<weights.length; i++) {
      count += input[i] * weights[i];
    }
    count += this.bias;

    return (1 / (Math.pow(0.135, count) + 1)); //sigmoid function
  }
  
}