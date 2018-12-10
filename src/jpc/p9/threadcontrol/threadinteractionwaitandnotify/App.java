package jpc.p9.threadcontrol.threadinteractionwaitandnotify;


class SleepingTask implements Runnable
{
    public void run()
    {
        System.out.println ("1. State in run method: " + Thread.currentThread().getState());
        try
        {
            synchronized (this)
            {	
            	System.out.println ("Go for sleeping");
                this.wait();
                System.out.println ("2. State after calling wait(): " + Thread.currentThread().getState());
            }
 
            System.out.println ("Normal End");
        }
        catch (InterruptedException exception)
        {
            System.out.println ("Interrupted End");
        }
    }
}
public class App {
	public static void main(String[] args) {
	    SleepingTask task = new SleepingTask();
	    Thread thread = new Thread (task);
	    System.out.println ("3. State before start(): " + thread.getState());
	    thread.start();
	    System.out.println ("4. State after start(): " + thread.getState());
	 
	    try
	    {
	        Thread.sleep (5000);
		    System.out.println ("5. State after sleep(): " + thread.getState());
	    }
	    catch (InterruptedException exception){}
	 
	    synchronized (task)
	    {

		    System.out.println ("6. State before notify(): " + thread.getState());
	        task.notify();
		    System.out.println ("7. State after notify(): " + thread.getState());
	    }
	    System.out.println ("Main thread ended (no more action...)");
	    System.out.println ("8. Is thread alive? : " + thread.isAlive());
	    System.out.println ("9. Is thread interrupted? : " + thread.isInterrupted());
	    thread.interrupt();
	    System.out.println ("Main thread interrupted (calling interrupt())");
	    System.out.println ("10. Is thread alive? : " + thread.isAlive());
	    System.out.println ("11. Is thread interrupted? : " + thread.isInterrupted());
	    System.out.println ("12. State after interrupt(): " + thread.getState());
		
	}
}
