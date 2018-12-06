package jpc.p4.sharedobject.parallel;
public class BankAccount
{
    private int balance;

	public BankAccount(int balance) {
		super();
		this.balance = balance;
	}

	public int getBalance()
    {
        return balance;
    }
 
    public void withdraw (int amount)
    {
    	System.out.println("\nDemande de transaction de " + amount + " euros");
    	System.out.println("===================================");
        if (amount <= balance)
        {
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            balance -= amount;
            System.out.println ("Compte débité de " + amount + " euros");
        }
    }
}