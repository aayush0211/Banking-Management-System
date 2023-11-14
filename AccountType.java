package util;
//type(enum :SAVING(1000),CURRENT(500),FD(10000))
public enum AccountType {

	SAVING(1000),CURRENT(500),FD(10000);
	
	private int MinBalance;

	private AccountType(int minBalance) {
		MinBalance = minBalance;
	}

	public int getMinBalance() {
		return MinBalance;
	}
	
	
}
