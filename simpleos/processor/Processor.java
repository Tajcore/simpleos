package simpleos.processor;

import simpleos.memory.MyMemory;

public abstract class Processor {

    public abstract int fetch(int step, int instruct);
    public abstract int execute(int step, MyMemory m);

} //end abstract class Processor
