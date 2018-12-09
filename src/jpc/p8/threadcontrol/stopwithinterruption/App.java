package jpc.p8.threadcontrol.stopwithinterruption;


import java.util.concurrent.ThreadLocalRandom;

class Countdown implements Runnable
{
	private int type = 0; // 0: Alphabetic / 1: numeric / 2: dot / 3 or else: plus
	private volatile Thread currentThread = null;
	
    /**
	 * @param type
	 */
	public Countdown(int type) {
		super();
		this.type = type;
	}

	public void run()
    {
    	currentThread = Thread.currentThread();
    	long startTime = System.nanoTime();
    	while(! currentThread.isInterrupted()) {
            try
            {
                Thread.sleep (10);
            }
            catch (InterruptedException exception){}
    		display(2);
    	}
    	long endTime   = System.nanoTime();
		System.out.println("\n\tCanceled after: " + (( endTime - startTime) / 1000000) + " millis." );
		System.out.println("\n\tSupposed to be canceled after 5000 millis!");
        
    }
	
	public void cancel() {
		currentThread.interrupt();
	}
    
    private void display(int loop) {
        int i = 0,
    		length = 26;
        if(this.type == 1)
        	length = 9;
    	boolean augmente = true;
    	StringBuilder str = new StringBuilder();
    	
        for (int count = 0; count <= (length * 2 * loop); count++)
        {
	    	str.setLength(0);
	    	for (int j = 0; j <= (length - i); j++) {
	    		str.append(" ");
	    	}
	    	for (int j = 0; j <= i; j++) {
	    		switch(this.type) {
	    		case 0:
	    			str.append((char)(j + 65)); // 65 : A / 97: a
	    			break;
	    		case 1:
	    			str.append((char)(j + 48)); // 48: 0
	    			break;
	    		case 2:
	    			str.append(".");
	    			break;
	    		default:
	    			str.append("+");
	    		}
	    		
	    	}
	    	System.out.println(str + "" +  str.deleteCharAt(str.length() - 1).reverse());
	    	
	    	int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
	    		if(randomNum < 2) augmente = !augmente;
	    	
	    	if (i >= length) augmente = false;
	    	if (i == 0) augmente = true;
	    	if (augmente) i++;
	    	else i--;
	    	
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				currentThread.interrupt();
			}
	    	if(currentThread.isInterrupted()) break;
	    }
    }
}

public class App {
	public static void main(String[] args) {
		Countdown countDown = new Countdown(0);
		new Thread(countDown).start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countDown.cancel();
		
	}
}
