//Will Dahl
//ICSI 311
//Michael Phipps
//March 28th, 2018

//Import util liabry to use Queue data structure
import java.util.*;

//Visitor class to read in a Node and print out the contents formatted in assembly 
public class Visitor {
	//Visit method that will start the processing of the node and its contents
	//@parama: 
	//	node - Node type that holds all of the informations to be processed
	public static void Visit(Node node){
		int line = 0;//line count
		// checks if the node passed is a StatementsNode type
		if(node.GetType() == 's'){
			StatementsNode statements = (StatementsNode)node;//turns node into a StatementsNode type to access children filed.
			//Calls statementsNode methond
			//Passes children field of statements which is a Queue of Nodes
			//Passes line count for printing
			statementsNode(statements.children, line);
		}

		//Prints if Prof Phipps trys to be tricky
		else{
			System.out.println("Whoa dude, you're suppose to pass through a node that is a StatementsNode type!");
		}
	}

	//statementsNode methond that handles the diffrent types of nodes stored in its children Queue
	//@parama: 
	//	queue - Queue of nodes thatholds each of the StatementsNode's children
	//	line - int that hodls the number of the line currently on for printing
	//@return:
	//	line
	private static int statementsNode(Queue<Node> queue, int line){
		if(queue.peek() != null && queue.peek().GetType() == 's'){
			StatementsNode statements = (StatementsNode)queue.peek();//turns node into a StatementsNode type to access children filed.
			//Calls statementsNode methond
			//Passes children field of statements which is a Queue of Nodes
			//Passes line count for printing
			line = statementsNode(statements.children, line);
			line = next(queue, line);
		}
		//Checks if current node in the queue is of type WhileNode
		else if(queue.peek() != null && queue.peek().GetType() == 'w'){
			WhileNode whileNode = (WhileNode)queue.peek(); //Sets the current node to a WhileNode to accsess the conditions and statements fields
			//Sets the conditions and statements fields of whileNode to StatementsNodes to access children fields
			StatementsNode conditions = (StatementsNode)whileNode.conditions;
			StatementsNode statements = (StatementsNode)whileNode.statements;
			//Calls whileNode method
			//Passes children field of conditions and statements which are Queue's of Nodes
			//Passes line count for printing
			line = whileNode(conditions.children, statements.children, line);
			line = next(queue, line);
		}

		//Checks if current node is a subtracting TwoOperandNode
		else if(queue.peek() != null && queue.peek().GetType() == '-'){
			TwoOperandNode sub = (TwoOperandNode)queue.peek(); //Sets the current node to a TwoOperanedNode to acsses operand 1 and 2 fields
			//Calls sub method
			//Passes the operand 1 and 2 fildes 
			//Passes line count for printing
			sub(sub.operand1, sub.operand2, line);
			//Calls next method to go onto the next node in the queue
			line = next(queue, line); // sets line equal to the value returned by next
		}

		//Checks if the current node is a plus TwoOperandNode 
		else if(queue.peek() != null && queue.peek().GetType() == '+'){
			TwoOperandNode add = (TwoOperandNode)queue.peek();
			//Calls add method (Same general functinalty as the sub method)
			add(add.operand1, add.operand2, line);
			line = next(queue, line);
		}

		//Checks if the current node is a multiply TwoOperandNode 
		else if(queue.peek() != null && queue.peek().GetType() == '*'){
			TwoOperandNode mul = (TwoOperandNode)queue.peek();
			//Calls add method (Same general functinalty as the sub method)
			mul(mul.operand1, mul.operand2, line);
			line = next(queue, line);
		}

		//Checks if the current node is a divide TwoOperandNode 
		else if(queue.peek() != null && queue.peek().GetType() == '/'){
			TwoOperandNode div = (TwoOperandNode)queue.peek();
			//Calls add method (Same general functinalty as the sub method)
			div(div.operand1, div.operand2, line);
			line = next(queue, line);
		}

		//Checks if the queue is empty
		else if(queue.peek() == null){
			return line; //returns line
		}

		return line;
	}

	//method sub to check if Operand is a AddressOperand or a RegisterOperand
	//@parama:
	//	op1 - Operand field 1
	//	op2 - Operand field 2
	// 	line - int to hold current line on for printing
	private static void sub(Operand op1, Operand op2, int line){
		if(op1.GetType() == 'A' && op2.GetType() == 'A'){
			//Sets op1 and op2 to AddressOperand's
			AddressOperand address1 = (AddressOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the addresSub method
			//Passes the new Address Operands
			//Passes line count for printing
			addressSub(address1, address2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerSub(register1, register2, line);
		}

		if(op1.GetType() == 'A' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			AddressOperand address1 = (AddressOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			addressRegisterSub(address1, register2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'A'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerAddressSub(register1, address2, line);
		}
	} 

	//method add to check if Operand is a AddressOperand or a RegisterOperand
	//@parama:
	//	op1 - Operand field 1
	//	op2 - Operand field 2
	// 	line - int to hold current line on for printing
	private static void add(Operand op1, Operand op2, int line){
		if(op1.GetType() == 'A' && op2.GetType() == 'A'){
			//Sets op1 and op2 to AddressOperand's
			AddressOperand address1 = (AddressOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the addresSub method
			//Passes the new Address Operands
			//Passes line count for printing
			addressAdd(address1, address2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerAdd(register1, register2, line);
		}

		if(op1.GetType() == 'A' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			AddressOperand address1 = (AddressOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			addressRegisterAdd(address1, register2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'A'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerAddressAdd(register1, address2, line);
		}
	}

	private static void mul(Operand op1, Operand op2, int line){
		if(op1.GetType() == 'A' && op2.GetType() == 'A'){
			//Sets op1 and op2 to AddressOperand's
			AddressOperand address1 = (AddressOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the addresSub method
			//Passes the new Address Operands
			//Passes line count for printing
			addressMul(address1, address2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerMul(register1, register2, line);
		}

		if(op1.GetType() == 'A' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			AddressOperand address1 = (AddressOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			addressRegisterMul(address1, register2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'A'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerAddressMul(register1, address2, line);
		}
	}

	private static void div(Operand op1, Operand op2, int line){
		if(op1.GetType() == 'A' && op2.GetType() == 'A'){
			//Sets op1 and op2 to AddressOperand's
			AddressOperand address1 = (AddressOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the addresSub method
			//Passes the new Address Operands
			//Passes line count for printing
			addressDiv(address1, address2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerDiv(register1, register2, line);
		}

		if(op1.GetType() == 'A' && op2.GetType() == 'R'){
			//Sets op1 and op2 to RegisterOperand's
			AddressOperand address1 = (AddressOperand)op1;
			RegisterOperand register2 = (RegisterOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			addressRegisterDiv(address1, register2, line);
		}

		if(op1.GetType() == 'R' && op2.GetType() == 'A'){
			//Sets op1 and op2 to RegisterOperand's
			RegisterOperand register1 = (RegisterOperand)op1;
			AddressOperand address2 = (AddressOperand)op2;
			//Calls the registerSub method
			//Passes the new register Operands
			//Passes line count for printing
			registerAddressDiv(register1, address2, line);
		}
	}

	//whileNode methond which formates the printing of the staemtnets and conditions in a while loop in assembly
	//@parama:
	//	conditions - queue that holds all the condtion nodes for the while loop
	//	statments - queue that holds all the sataments to be exectured in the while loop
	//	line - int to hold the current line number for printing
	private static int whileNode(Queue<Node> conditions, Queue<Node> statments, int line){
		int jmp = line; // holds the line at which the loop start
		line = statementsNode(conditions, line); // sets line to the value returned by the statementsNode method(which is the current line value after the printing of the condtions)
		System.out.println(line + ": bne $00000000" ); //prints exit condition for while loop
		line++; // Incremtns the line count
		line = statementsNode(statments, line);
		System.out.println(line + ": jmp $" + jmp); // pritn jump comand for while loop
		return line;
	}

	//addressSub method to print the address of op1 and 2 being subtracted in assembly format
	//@parama:
	//	op1 - Address 1
	//	op2 - Address 2
	private static void addressSub(AddressOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": sub $" + op1.GetAddress() + ", $" + op2.GetAddress());
	}

	//registerSub method to print the regirsters of op1 and 2 being subtracted in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - register 2
	private static void registerSub(RegisterOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": sub R" + op1.GetRegister() + ", R" + op2.GetRegister());
	}

	//registerAddressSub method to print the regirster op1 and address 2 being subtracted in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - address 2
	private static void registerAddressSub(RegisterOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": sub R" + op1.GetRegister() + ", $" + op2.GetAddress());
	}

	//addressRegisterSub method to print the address op1 and register op2 being subtracted in assembly format
	//@parama:
	//	op1 - address 1
	//	op2 - register 2
	private static void addressRegisterSub(AddressOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": sub $" + op1.GetAddress() + ", R" + op2.GetRegister());
	}

	//addressAdd method to print the address of op1 and 2 being added in assembly format
	//@parama:
	//	op1 - Address 1
	//	op2 - Address 2
	private static void addressAdd(AddressOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": add $" + op1.GetAddress() + ", $" + op2.GetAddress());
	}

	//registerAdd method to print the regirsters of op1 and 2 being added in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - register 2
	private static void registerAdd(RegisterOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": add R" + op1.GetRegister() + ", R" + op2.GetRegister());
	}

	//registerAddressAdd method to print the regirsters of op1 and 2 being added in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - address 2
	private static void registerAddressAdd(RegisterOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": add R" + op1.GetRegister() + ", $" + op2.GetAddress());
	}

	//addressRegisterAdd method to print the address op1 and register op2 being added in assembly format
	//@parama:
	//	op1 - address 1
	//	op2 - register 2
	private static void addressRegisterAdd(AddressOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": add $" + op1.GetAddress() + ", R" + op2.GetRegister());
	}

	//registerMul method to print the regirsters of op1 and 2 being multiplyed in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - register 2
	private static void registerMul(RegisterOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": mul R" + op1.GetRegister() + ", R" + op2.GetRegister());
	}

	//addressMul method to print the addresses of op1 and 2 being multiplyed in assembly format
	//@parama:
	//	op1 - address 1
	//	op2 - address 2
	private static void addressMul(AddressOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": mul $" + op1.GetAddress() + ", $" + op2.GetAddress());
	}

	//registerAddressMul method to print the regirster op1 and address op2 being multiplyed in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - address 2
	private static void registerAddressMul(RegisterOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": mul R" + op1.GetRegister() + ", $" + op2.GetAddress());
	}

	//addressRegisterMul method to print the address op1 and regiester op2 being multiplyed in assembly format
	//@parama:
	//	op1 - address 1
	//	op2 - register 2
	private static void addressRegisterMul(AddressOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": mul $" + op1.GetAddress() + ", R" + op2.GetRegister());
	}

	//registerDiv method to print the regirsters of op1 and 2 being divided in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - register 2
	private static void registerDiv(RegisterOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": div R" + op1.GetRegister() + ", R" + op2.GetRegister());
	}

	//addressDiv method to print the adresses of op1 and 2 being divided in assembly format
	//@parama:
	//	op1 - address 1
	//	op2 - address 2
	private static void addressDiv(AddressOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": div $" + op1.GetAddress() + ", $" + op2.GetAddress());
	}

	//registerAddressDiv method to print the regirster op1 and address op2 being divided in assembly format
	//@parama:
	//	op1 - register 1
	//	op2 - address 2
	private static void registerAddressDiv(RegisterOperand op1, AddressOperand op2, int line){
		System.out.println(line + ": div R" + op1.GetRegister() + ", $" + op2.GetAddress());
	}

	//addressRegisterDiv method to print the address op1 and register op2 being divided in assembly format
	//@parama:
	//	op1 - address 1
	//	op2 - register 2
	private static void addressRegisterDiv(AddressOperand op1, RegisterOperand op2, int line){
		System.out.println(line + ": div $" + op1.GetAddress() + ", R" + op2.GetRegister());
	}

	//next method to get the next node in the queue
	//@parama: 
	//	queue - Queue holding all the nodes to be formatting
	//	line - int valu eof the current line for printing
	//@return:
	//	line
	private static int next(Queue<Node> queue, int line){
		queue.remove(); // removes the current node in the queue 
		line++; // incrments the line number count
		line = statementsNode(queue, line);
		return line; // returns line
	}
}