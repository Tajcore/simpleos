package simpleos.processor;

import simpleos.memory.*;


public  class MyProcessor extends Processor {

    private MyMemory PC;    
    private MyMemory IR;    
    private MyMemory ACC;    


    public int fetch(){
        System.out.println("Processor is now fetching..");
        return 1;
    } 
    public int execute(){
        System.out.println("Processor is now execting..");
        return 1;
    } 

} //end abstract class Processor
