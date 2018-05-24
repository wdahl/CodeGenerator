import java.util.*;

public class Main {

  public static void main(String[] args) {
    Queue<Node> adds = new LinkedList<Node>();
    adds.add(new TwoOperandNode('+',new RegisterOperand(0),new AddressOperand(600)));
    adds.add(new TwoOperandNode('+',new RegisterOperand(1),new RegisterOperand(2)));
    adds.add(new TwoOperandNode('+',new RegisterOperand(2),new RegisterOperand(3)));
    adds.add(new TwoOperandNode('+',new RegisterOperand(3),new RegisterOperand(4)));
    adds.add(new TwoOperandNode('+',new RegisterOperand(4),new RegisterOperand(5)));

    Queue<Node> subs = new LinkedList<Node>();
    //subs.add(new WhileNode(new StatementsNode(adds),new StatementsNode(adds)));
    subs.add(new TwoOperandNode('-',new RegisterOperand(0),new RegisterOperand(1)));
    subs.add(new TwoOperandNode('-',new RegisterOperand(1),new RegisterOperand(2)));
    subs.add(new TwoOperandNode('-',new RegisterOperand(2),new RegisterOperand(3)));
    subs.add(new TwoOperandNode('-',new RegisterOperand(3),new RegisterOperand(4)));
    subs.add(new TwoOperandNode('-',new RegisterOperand(4),new RegisterOperand(5)));
    subs.add(new TwoOperandNode('-',new AddressOperand(1000),new AddressOperand(2000)));
    
    Queue<Node> mult = new LinkedList<Node>();
    //subs.add(new WhileNode(new StatementsNode(adds),new StatementsNode(adds)));
    mult.add(new TwoOperandNode('*',new RegisterOperand(0),new RegisterOperand(1)));
    mult.add(new TwoOperandNode('*',new RegisterOperand(1),new RegisterOperand(2)));
    mult.add(new TwoOperandNode('*',new RegisterOperand(2),new RegisterOperand(3)));
    mult.add(new TwoOperandNode('*',new RegisterOperand(3),new RegisterOperand(4)));
    mult.add(new TwoOperandNode('*',new RegisterOperand(4),new RegisterOperand(5)));
    mult.add(new TwoOperandNode('*',new AddressOperand(1000),new AddressOperand(2000)));
    
    Queue<Node> div = new LinkedList<Node>();
    //subs.add(new WhileNode(new StatementsNode(adds),new StatementsNode(adds)));
  
    div.add(new TwoOperandNode('/',new RegisterOperand(1),new RegisterOperand(2)));
    Node nodeW = (Node)(new WhileNode(new StatementsNode(adds),new StatementsNode(subs)));
    div.add(nodeW);
    div.add(new TwoOperandNode('/',new RegisterOperand(1),new RegisterOperand(2)));
    div.add(new TwoOperandNode('/',new RegisterOperand(2),new RegisterOperand(3)));
    div.add(new TwoOperandNode('/',new RegisterOperand(3),new RegisterOperand(4)));
    div.add(new TwoOperandNode('/',new RegisterOperand(4),new RegisterOperand(5)));
    div.add(new TwoOperandNode('/',new AddressOperand(1000),new AddressOperand(2000)));
    


    Queue<Node> baseNodes = new LinkedList<Node>();
    //baseNodes.add();
    baseNodes.add(new TwoOperandNode('-',new AddressOperand(3333),new AddressOperand(4444)));
    //baseNodes.add(new WhileNode(new StatementsNode(adds),new StatementsNode(subs)));
     //baseNodes.add(new WhileNode(new StatementsNode(div),new StatementsNode(mult)));
     baseNodes.add(new WhileNode(new StatementsNode(div),new StatementsNode(mult)));


    Node base = new StatementsNode(baseNodes);
    Visitor.Visit(base);
  }
}
