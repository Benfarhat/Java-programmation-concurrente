package jpc.pb.threadcontrol.threadinteractiontimer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class App {
	public static void main(String[] args) {

	    long delay = 0L;
	    long period = 1000L;
	    // First way
        Timer timer1 = new Timer();
        timer1.schedule (new TimerTask() {
            public void run()
            {
                System.out.println ("- Task  " + Thread.currentThread().getName() + " performed on " + new Date());
            }
        }, delay, period); // after 0 millis and every 1000 ms

        // Another one with cancel() method
	    TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("+ Task " + Thread.currentThread().getName() + " performed on " + new Date());
	            
	        }
	    };
	    Timer timer2 = new Timer("Timer");
	     
	    timer2.scheduleAtFixedRate(task, 1000L, 1000L);
	     
	    try {
			Thread.sleep(1000L * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    timer2.cancel();
	    System.out.println(">\tTask " + Thread.currentThread().getName() + " canceled on " + new Date());	  
	    
	    
	    try {
			Thread.sleep(1000L * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    timer1.cancel();
	    System.out.println(">\tTask " + Thread.currentThread().getName() + " canceled on " + new Date());
	}
}
