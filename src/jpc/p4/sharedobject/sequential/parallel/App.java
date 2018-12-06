package jpc.p4.sharedobject.sequential.parallel;

public class App {

	public static void main(String[] args) {
		final BankAccount account = new BankAccount(100);
		
		class WithdrawMoney implements Runnable {
			@Override
			public void run() {
				long threadId = Thread.currentThread().getId();
				
				account.withdraw(1);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				account.withdraw(1);
	            System.out.println ("Thread #" 
	            		+ threadId
	            		+ " - "
	            		+ Thread.currentThread().getName() 
	            		+ " : Il reste " 
	            		+ account.getBalance() 
	            		+ " euros sur le compte");
			}
			
		}

		for(int i = 0; i < 200; i++) {
		    new Thread (new WithdrawMoney(), "TH" + i).start();
		}
	}
}