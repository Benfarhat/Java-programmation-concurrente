package jpc.p5.sharedobject.synchron1zed;

public class App {

	public static void main(String[] args) {
		final BankAccount account = new BankAccount(500);
		
		class WithdrawMoney implements Runnable {
			@Override
			public void run() {
				long threadId = Thread.currentThread().getId();
				
				account.withdraw(100);
	            System.out.println ("Thread #" 
	            		+ threadId
	            		+ " - "
	            		+ Thread.currentThread().getName() 
	            		+ " : Il reste " 
	            		+ account.getBalance() 
	            		+ " euros sur le compte");
			}
			
		}

		for(int i = 0; i < 10; i++) {
		    new Thread (new WithdrawMoney(), "TH" + i).start();
		}
	}
}