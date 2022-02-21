public class NodeLayer{
  //instance vars
  Node[] nodes;
  
  //constructors
  public NodeLayer(int numNodes, double weightRange, double biasRange, int nodeSize){
    this.nodes = new Node[numNodes];

    for(int i=0; i<nodes.length; i++){
      nodes[i] = new Node(weightRange, biasRange, nodeSize);
    }
  }

  //methods


}