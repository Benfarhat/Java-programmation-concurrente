package jpc.p3.sharedobject.sequential;

public class App {

	public static void main(String[] args) {
		BankAccount account = new BankAccount(250);
		
	    account.withdraw (250);
	    System.out.println ("\tIl reste " + account.getBalance() + " euros sur le compte");
	 
	    account.withdraw (250);
	    System.out.println ("\tIl reste " + account.getBalance() + " euros sur le compte");
		

	}

}
