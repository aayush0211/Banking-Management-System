package core;

import java.time.LocalDate;
import java.util.Objects;

import util.AccountType;
//BankAccount : acctNo, customer name , type(enum :SAVING(10000),CURRENT(5000),FD(50000))  , balance , creation date , last tx date , isActive
import util.Kyc;

public class BankAccount implements Comparable<BankAccount> {
	private PrimaryKey key;
	private Kyc kyc;
	private String customerName;
	private AccountType accType;
	private int balance;
	private LocalDate createDate;
	private LocalDate lastDate;
	private boolean active;
//	private static int count;
	private boolean kycStatus;

//	static {
//		count=0;
//	}
	public BankAccount(int accNo, String ifsc, String customerName, AccountType accType, int balance,
			LocalDate createDate) {
		super();
		this.key = new PrimaryKey(accNo, ifsc);
		this.kycStatus = false;
		kyc = null;
		this.customerName = customerName;
		this.accType = accType;
		this.balance = balance;
		this.createDate = createDate;
		this.lastDate = LocalDate.of(createDate.getYear(), createDate.getMonth(), createDate.getDayOfMonth());
		active = true;
	}

	public BankAccount(int accNo, String ifsc, Kyc kyc, String customerName, AccountType accType, int balance,
			LocalDate createDate) {
		super();
		this.key = new PrimaryKey(accNo, ifsc);
		this.kycStatus = true;
		this.kyc = kyc;
		this.customerName = customerName;
		this.accType = accType;
		this.balance = balance;
		this.createDate = createDate;
		this.lastDate = LocalDate.of(createDate.getYear(), createDate.getMonth(), createDate.getDayOfMonth());
		active = true;
	}

	public void setKyc(Kyc kyc) {
		this.kyc = kyc;
		kycStatus = true;
		System.out.println("Kyc Done !");
	}

	@Override
	public String toString() {
		return "BankAccount details as : " + key + ",\n kyc details" + kyc + ",\n customerName=" + customerName
				+ ", accType=" + accType + ", balance=" + balance + ", createDate=" + createDate + ", lastDate="
				+ lastDate + ", active=" + active + ", kycStatus=" + kycStatus + "\n";
	}

	public PrimaryKey getKey() {
		return key;
	}

	public boolean isKycStatus() {
		return kycStatus;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public AccountType getAccType() {
		return accType;
	}

	@Override
	public int compareTo(BankAccount o) {
		// TODO Auto-generated method stub
		return key.compareTo(o.key);
	}

	public Kyc getKyc() {
		return kyc;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	// because we will make TreeSet from Map than our BankAccount become primary
	// key.
	@Override
	public int hashCode() {
		return key.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BankAccount) {
			BankAccount acc = (BankAccount) obj;
			return key.equals(acc.getKey());
		}
		return false;
	}

}
