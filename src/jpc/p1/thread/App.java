package jpc.p1.thread;

class Countdown extends Thread
{
    public void run()
    {
    	int i = 0,
    		length = 26, 
    		loop = 4;
    	boolean augmente = true;
    	StringBuilder str = new StringBuilder();
    	
        for (int count = 0; count <= (length * loop); count++)
        {
        	str.setLength(0);
        	for (int j = 0; j <= (length - i); j++) {
        		str.append(" ");
        	}
        	for (int j = 0; j <= i; j++) {
        		str.append((char)(j + 65));
        	}
        	System.out.println(str + "" +  str.deleteCharAt(str.length() - 1).reverse());
        	if (i >= length) augmente = false;
        	if (i == 0) augmente = true;
        	if (augmente) i++;
        	else i--;
        }
    }
}

public class App {
	public static void main(String[] args) {
		Thread thread = new Countdown();
		System.out.println("\nUsing Thread.run()\n");
		thread.run();
		System.out.println("\nUsing Thread.start()\n");
		thread.start(); // Créer un thread d'exécution et exécute la méthode run()
	}
}
