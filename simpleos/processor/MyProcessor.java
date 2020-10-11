package simpleos.processor;

import simpleos.memory.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.ArrayList;

public class MyProcessor extends Processor {

    private MyMemory PC;    
    private MyMemory IR;    
    private MyMemory ACC;    
		private ArrayList<Integer> sequence = new ArrayList<Integer>();
		private Map<String, BiConsumer<Integer,MyMemory>> commands = new HashMap<>();

		// Initialize the processor with defined memory size and known input commands

		public MyProcessor(){

			commands.put("1", (input,mem) -> {
				System.out.println("Load AC from memory location"+ input);
				int val = mem.getValue(input);

				ACC.setValue(0, val);
				}
				);

			commands.put("2", (input,mem) -> {
				System.out.println("Store AC to memory location"+ input );
				int val = ACC.getValue(0);
				mem.setValue(input, val);
				}
				);

			commands.put("5",	(input,mem) ->{
				 System.out.println("Add to AC from memory location"+ input);
				 int val = mem.getValue(input);
				 int acc_Val = ACC.getValue(0) + val;
				 ACC.setValue(0,acc_Val);
				 }
				 );

			commands.put("4", (input,mem) ->{
				 System.out.println("Subtract from AC from memory location"+ input);
				 int val = mem.getValue(input);
				 int acc_Val = ACC.getValue(0) - val;
				 ACC.setValue(0,acc_Val);
				 }
				 );

			commands.put("3", (input,mem) -> System.out.println("Load AC from stdin"));

			commands.put("7", (input,mem) -> {
				System.out.println("Store AC to stdout");
				int output = ACC.getValue(0);
				System.out.print("\033[H\033[2J");
System.out.flush();
				sequence.add(output);
				System.out.println("***OUTPUT****: "+ output);

				System.out.println("sequence generated so far is " + sequence.toString());

				System.out.println("Remember to press enter to end program")
				;
				
				});

			commands.put("8", (input,mem) -> {
				System.out.println("Add AC to memory location "+ input);
				 int val = mem.getValue(input);
				 int acc_Val = ACC.getValue(0) + val;
				 mem.setValue(input,acc_Val);
				});

			commands.put("9", (input,mem) -> {
				System.out.println("Store Memory Location " + input+ " to stdout");

				int output = mem.getValue(input);
				System.out.print("\033[H\033[2J");
				System.out.flush();
				sequence.add(output);

				System.out.println("***OUTPUT****: "+ output);
				System.out.println("sequence generated so far is " + sequence.toString());
				System.out.println("Remember to press enter to end program");


				});


			this.PC = new MyMemory(1);
			this.IR = new MyMemory(1);
			this.ACC = new MyMemory(1);
			this.ACC.setValue(0,0);
		}
		
		

		// Decodes the input to get the OPCode
		public String decode(String input){
		 String opCode = input.substring(0,1);

		 return opCode;
		}

		public int getOperand(String input){
			int operand = Integer.parseInt(input.substring(1));
			return operand;
		}

		// Fetches the next instruction and passes it to the IR
    public int fetch(int step, int instruct){

			
        System.out.println("Processor is now fetching..");

				this.PC.setValue(0, step);
				this.IR.setValue(0 , instruct);

				String template = "PC: %s\nACC: %s\nIR: %s";
				String displayMSG = String.format(template, step, ACC.getValue(0), Integer.toHexString(instruct));

				System.out.println(displayMSG);

        return 1;
    } 


		// Executes the current instruction in the IR and increments the PC

    public int execute(int step, MyMemory memory){

			 String current_cmd = Integer.toHexString(this.IR.getValue(0));
			
        System.out.println("Processor is now execting..");
	
				String opCode = decode(current_cmd);
				System.out.println("THE CURRENT COMMAND IS " + current_cmd);
				int operand = getOperand(current_cmd);
				commands.getOrDefault(opCode, (input,_step) -> {
				try {
					System.out.println("No Such Command");
					}
				catch(StringIndexOutOfBoundsException e) {
					System.out.println("Not a valid 8 bit arg");
				}
				}

				).accept(operand,memory);
				
        return 1;
    } 

} //end abstract class Processor
