package jpc.p1.thread;

import java.util.concurrent.ThreadLocalRandom;

class Countdown extends Thread
{
    public void run()
    {
    	int randomNum;
    	while(true) {
    		try {
    			display(2);    			
    			randomNum = ThreadLocalRandom.current().nextInt(1, 6);
				Thread.sleep(randomNum * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
        
    }
    
    private void display(int loop) {
        int i = 0,
    		length = 26;
    	boolean augmente = true;
    	StringBuilder str = new StringBuilder();
    	
        for (int count = 0; count <= (length * 2 * loop); count++)
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

class Dot extends Thread
{
    public void run()
    {
    	int randomNum;
    	while(true) {
    		try {
    			display(2);
    			randomNum = ThreadLocalRandom.current().nextInt(1, 6);
				Thread.sleep(randomNum * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
        
    }
    
    private void display(int loop) {
        int i = 0,
    		length = 26;
    	boolean augmente = true;
    	StringBuilder str = new StringBuilder();
    	
        for (int count = 0; count <= (length * 2 * loop); count++)
        {
	    	str.setLength(0);
	    	for (int j = 0; j <= (length - i); j++) {
	    		str.append(" ");
	    	}
	    	for (int j = 0; j <= i; j++) {
	    		str.append(".");
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
		Thread threadCD = new Countdown();
		Thread threadDOT = new Dot();
		/*
		System.out.println("\nUsing Thread.run(): " + threadCD.getName() + "\n");
		threadCD.run();
		*/
		System.out.println("\nUsing Thread.start(): " + threadCD.getName() + "\n");
		threadCD.start(); // Créer un thread d'exécution et exécute la méthode run()
		System.out.println("\nUsing Thread.start(): " + threadDOT.getName() + "\n");
		threadDOT.start(); // Créer un thread d'exécution et exécute la méthode run()
	}
}
