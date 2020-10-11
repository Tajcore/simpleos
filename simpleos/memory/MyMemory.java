package simpleos.memory;

public class MyMemory extends Memory {
        public MyMemory(int size){
            super(size);
        }

        public void printSize(){
            System.out.println("The Size of the memory is: " + getSize());
        }
}//end abstract class memory
