/* 620118524
The values for PC and IR are to be read in hexadecimal format
*/


package simpleos.sys;

import simpleos.memory.*;
import simpleos.processor.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
public class OS {

    public static void main(String[] args) throws IOException{
			System.out.println("\nThis program aims to generate a fibonacci sequence using the instruction cycle\nIt will run until there is no more memory address left to go to\n");

			int step = 1;
					MyMemory m = new MyMemory(5);

					MyMemory instructionMem = new MyMemory(9);
					MyMemory ioMemory = new MyMemory(4);

					ioMemory.setValue(1,0);
					ioMemory.setValue(2,1);
					
					instructionMem.setValue(0,Integer.parseInt("01110000",2));
					instructionMem.setValue(1,Integer.parseInt("00100001",2));
					instructionMem.setValue(2,Integer.parseInt("10000010",2));
					instructionMem.setValue(3,Integer.parseInt("10010010",2));
					instructionMem.setValue(4,Integer.parseInt("01010010",2));

					MyProcessor p = new MyProcessor();

					boolean blankLine = true;
			loop:
			while (true){
				 int available;
    			while ((available = System.in.available()) == 0) {
        					try {
								
								System.out.println(ioMemory.toString());
								System.out.println("WE ARE AT STEP " + step); 
								p.fetch(step,instructionMem.getValue(step-1));          
					Thread.sleep(2000);
								p.execute(step,ioMemory);

								step ++;
								step = step % 5;
								if (step == 0){
									step = 5;
								}
								
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
   	 }    do {
				System.out.print("Program exited");
         switch (System.in.read()) {
             default:
                 blankLine = false;
                 break;
             case '\n':
                 if (blankLine)
                     break loop;
                 blankLine = true;
								 
                 break;
         }
    } while (--available > 0);

			}
    }// End man method

}// END class OS
