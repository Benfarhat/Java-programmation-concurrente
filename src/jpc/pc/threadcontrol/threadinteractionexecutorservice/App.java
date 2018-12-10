package jpc.pc.threadcontrol.threadinteractionexecutorservice;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	    
	    
	    System.out.println("\n/t-=<xxxxxxxxxxxxxxxxxxxxxxxxxxx>=-");
	    System.out.println("/t-=< Starting Executor Service >=-");
	    System.out.println("/t-=<xxxxxxxxxxxxxxxxxxxxxxxxxxx>=-\n");
	    System.out.println("Main differences between the Timer and the ExecutorService solution:\r\n" + 
	    		"\r\n" + 
	    		"    Timer can be sensitive to changes in the system clock; ScheduledThreadPoolExecutor is not\r\n" + 
	    		"    Timer has only one execution thread; ScheduledThreadPoolExecutor can be configured with any number of threads\r\n" + 
	    		"    Runtime Exceptions thrown inside the TimerTask kill the thread, so following scheduled tasks won’t run further; with ScheduledThreadExecutor – the current task will be canceled, but the rest will continue to run\r\n");
	    
	    TimerTask repeatedTask = new TimerTask() {
	        public void run() {
	            System.out.println("+ Task " + Thread.currentThread().getName() + " performed on " + new Date());
	        }
	    };
	    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	    delay  = 5000L;
	    period = 1000L;
	    executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
	    try {
			Thread.sleep(delay + period * 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    executor.shutdown();
	    System.out.println(">\tTask " + Thread.currentThread().getName() + " shutdowned on " + new Date());
	    
	}
}
