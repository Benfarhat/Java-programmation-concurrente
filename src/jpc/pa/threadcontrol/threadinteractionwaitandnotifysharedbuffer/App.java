package jpc.pa.threadcontrol.threadinteractionwaitandnotifysharedbuffer;

import java.util.Arrays;

class Consumer implements Runnable{
    private final SharedIntBuffer buffer;

    public Consumer(SharedIntBuffer sb){
        buffer = sb;
    }

    public void run() {
    	System.out.println("\tConsumer: " + Thread.currentThread().getName() +" running:");
        while(!buffer.isEmpty()) {
            int i = buffer.read();
            System.out.println("\t\tConsumer " + Thread.currentThread().getName() +" reads "+i); // this print may not be in the order
        }
    }
}


class Producer implements Runnable{
    private final SharedIntBuffer buffer;

    Producer(SharedIntBuffer sb) {
        this.buffer = sb;
    }

    public void run(){
    	System.out.println("\tProducer: " + Thread.currentThread().getName() +" running:");
    	while(!buffer.isFull()) {
            int n = (int) (Math.random()*100);
            buffer.put(n);
            System.out.println("\t\tProducer " + Thread.currentThread().getName() +" puts "+n);
        }
    }
}

class SharedIntBuffer
{
    private final int[] buffer = new int[20];
    private int first, last, size = 20, inBuffer;
 
    public synchronized int read()
    {
        // Tant qu'il y a des �l�ments dzans le buffer on retourne le suivant qu'on supprime par la m�me occasion
        // Si le buffer est vide, bloquer le process jusqu'a ajout d'un �lement
        while (inBuffer == 0)
        {
            try
            {
                wait();
            }
            catch (InterruptedException exception){}
        }
     
        first = (first + 1) % size;
        inBuffer--;
        displayContent();
        notifyAll();
        return buffer[first];
    }
    

 
    public synchronized void put (int i)
    {
        // On bloque le buffer tant qu'il n'y a pas de place
    	 while (inBuffer == size)
    	    {
    	        try
    	        {
    	            wait();
    	        }
    	        catch (InterruptedException exception){}
    	    }
    	 
    	    last = (last + 1) % size;
    	    inBuffer++;
    	    buffer[last] = i;
    	    notifyAll();
    }


    public synchronized boolean isEmpty(){
            return (inBuffer == 0);
    }  
    
    public synchronized boolean isFull(){
        return (inBuffer == size);
    }    
    
    public synchronized void displayContent() {
    	System.out.println(Thread.currentThread().getName() + " Buffer content : " + Arrays.toString(buffer));
    }
}
public class App {
	private volatile static SharedIntBuffer sib = new SharedIntBuffer();
	public static void main(String[] args) {
		for(int i=0; i < 10; i++) {
			System.out.println("\t" + i + ":");
			new Thread(new Producer(sib)).start();
			new Thread(new Consumer(sib)).start();
			
		}
		
	}
}
