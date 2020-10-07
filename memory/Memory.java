package simpleos.memory;

public abstract class Memory {

    private int memloc [];
    int size;

    public Memory(int size){
        System.out.println("Initializing memory size");
        this.size=size;
        memloc = new int[size];
        
    }

    public int getSize(){
        return size;
    }

    public int getValue(int index){
       return memloc[index]; 
    }



    public int setValue(int index, int value) {
        try{
            memloc[index] = value;
            System.out.println("Value set successfully");
            return 1;

        }catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }//set Value

}//end abstract class memory
