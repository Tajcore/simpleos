package simpleos.sys;

import simpleos.memory.*;
import simpleos.processor.*;


public class OS {

    public static void main(String[] args){

        try {
            MyMemory m = new MyMemory(5);
            MyProcessor p = new MyProcessor();
            m.printSize();
            p.fetch();            
			Thread.sleep(2000);
            p.execute();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }// End man method

}// END class OS
