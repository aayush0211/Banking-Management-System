package util;
//Options

//1. Open new account
//2. Get Account summary
//3. Withdraw/deposit
//4. Transfer Funds
//5. Close Account
//Assuming BankManager has logged in(later actual authentication n authorization 
//logic can be added)
//6. Display all account details
//7. Update a/c status of all accounts
//8. Sort accounts as per acct no
//Try n solve below requirements in the lab (if possible !)
//9. Sort a/cs as per account balance
//10. Sort a/cs as per a/c type n creation date.

import static util.BankAccountValidation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import core.BankAccount;
import core.PrimaryKey;

public interface BankOperations {

	public static void openNewAccount(Map<PrimaryKey, BankAccount> bankMap, Scanner sc) throws BankAccountException {
		System.out.println("we have following account as :");
		for (AccountType ty : AccountType.values())
			System.out.println(ty.name() + " Min_Balance : " + ty.getMinBalance());

		System.out.println("enter your details as :  accNo,ifsc Code, Name, AccountType and registration amount : ");
		BankAccount acc = RegistrationValidation(sc.nextInt(), sc.next(), sc.next(), sc.next().toUpperCase(),
				sc.nextInt(), LocalDate.now().toString(), bankMap);

		String kyc = "Y";
		System.out.println("do you want complete kyc together ?enter(y/n) :");
		if (kyc.equals(sc.next().toUpperCase())) {
			System.out.println("enter your kyc details as :- email , phoneNo and AadharNo :- ");
			acc.setKyc(new Kyc(sc.next(), sc.nextLong(), sc.nextLong()));
		}
		System.out.println("succesfully Register !");
		bankMap.put(acc.getKey(), acc);
	}

	public static void completeKyc(Scanner sc, Map<PrimaryKey, BankAccount> bankMap) throws BankAccountException {
		System.out.println("enter your A/c no and ifsc code : ");
		BankAccount acc = signInValidation(sc.nextInt(), sc.next(), bankMap);
		if (acc.isKycStatus()) {
			System.out.println("Already Completed !");
			return;
		}
		System.out.println("enter your kyc details as :- email , phoneNo and AadharNo :- ");
		acc.setKyc(new Kyc(sc.next(), sc.nextLong(), sc.nextLong()));
	}

	public static void getAccountSummary(Scanner sc, Map<PrimaryKey, BankAccount> bankMap) throws BankAccountException {
		System.out.println("enter your A/c no and ifsc code : ");
		BankAccount acc = signInValidation(sc.nextInt(), sc.next(), bankMap);
		System.out.println(acc);
	}

	public static void withdraw(Scanner sc, Map<PrimaryKey, BankAccount> bankMap) throws BankAccountException {
		System.out.println("enter your A/c no and ifsc code : ");
		BankAccount acc = signInValidation(sc.nextInt(), sc.next(), bankMap);
		checkActive(acc);
		transactionValidation(acc);
		System.out.println("enter amount to be withdraw : ");
		int amount = checkBalance(sc.nextInt(), acc);
		acc.setBalance(acc.getBalance() - amount);
		System.out.println(" Successfully withdraw ! updated balance : " + acc.getBalance());

	}

	public static void deposit(Scanner sc, Map<PrimaryKey, BankAccount> bankMap) throws BankAccountException {
		System.out.println("enter your A/c no and ifsc code : ");
		BankAccount acc = signInValidation(sc.nextInt(), sc.next(), bankMap);
		checkActive(acc);
		// transactionValidation(acc);
		System.out.println("enter amount to be deposit : ");
		acc.setBalance(acc.getBalance() + sc.nextInt());
		System.out.println(" Successfully deposit ! updated balance : " + acc.getBalance());
	}

	public static void transferFund(Scanner sc, Map<PrimaryKey, BankAccount> bankMap) throws BankAccountException {
		System.out.println("enter your A/c no and ifsc code : ");
		BankAccount acc1 = signInValidation(sc.nextInt(), sc.next(), bankMap);
		checkActive(acc1);
		transactionValidation(acc1);
		System.out.println("enter other person's A/c no and ifsc code : ");
		BankAccount acc2 = signInValidation(sc.nextInt(), sc.next(), bankMap);
		checkActive(acc1);
		System.out.println("enter amount to be transfer : ");
		int amount = checkBalance(sc.nextInt(), acc1);
		acc1.setBalance(acc1.getBalance() - amount);
		acc2.setBalance(acc2.getBalance() + sc.nextInt());
		System.out.println(" Successfully transfer ! updated balance : " + acc1.getBalance());
	}

	public static void closeAccount(Scanner sc, Map<PrimaryKey, BankAccount> bankMap) throws BankAccountException {
		System.out.println("enter your A/c no and ifsc code : ");
		PrimaryKey tempKey = new PrimaryKey(sc.nextInt(), sc.next());
		if (bankMap.remove(tempKey) == null)
			throw new BankAccountException("Account not exist !");
		System.out.println("Successfully removed !");
		return;
	}

	public static void displayAll(Map<PrimaryKey, BankAccount> bankMap) {
		for (BankAccount c : bankMap.values())
			System.out.println(c);
	}

	public static void sortByPrimaryKey(Map<PrimaryKey, BankAccount> bankMap) {
		TreeMap<PrimaryKey, BankAccount> tm = new TreeMap<>(bankMap);
		for (BankAccount c : tm.values())
			System.out.println(c);
	}

	public static void sortByBalance(Map<PrimaryKey, BankAccount> bankMap) {
		TreeSet<BankAccount> ts = new TreeSet<>(new sortByBalance());
		ts.addAll(bankMap.values());
		for (BankAccount acc : ts)
			System.out.println(acc);
	}

	public static void sortByAcTypeAndCreateDate(Map<PrimaryKey, BankAccount> bankMap) {
		TreeSet<BankAccount> ts = new TreeSet<>(new SortByAcTypeAndCreateDate());
		ts.addAll(bankMap.values());
		System.out.println(ts);
	}

	public static void unActiveAcc(Map<PrimaryKey, BankAccount> bankMap) {
		LocalDate temp = LocalDate.now();
		System.out.println("details of account who going to inactive because of no transaction from last 1 year :");
		List<BankAccount> lists = new ArrayList<>(bankMap.values());
		for (BankAccount acc : lists) {
			Period p = Period.between(acc.getLastDate(), temp);
			if (p.getYears() >= 1) {
				acc.setActive(false);
				System.out.println(acc);
			}
		}
	}

	public static void populateMap(Map<PrimaryKey, BankAccount> bankMap) {
		PrimaryKey p1 = new PrimaryKey(1, "PUNB0010");
		BankAccount acc1 = new BankAccount(1, "PUNB0010", "Govinda Tak", AccountType.valueOf("current".toUpperCase()),
				500, LocalDate.parse("2020-08-18"));
		PrimaryKey p2 = new PrimaryKey(1, "PUNB0011");
		BankAccount acc2 = new BankAccount(1, "PUNB0011", "Goutam Tak", AccountType.valueOf("saving".toUpperCase()),
				1000, LocalDate.parse("2020-08-18"));
		PrimaryKey p3 = new PrimaryKey(2, "PUNB0010");
		BankAccount acc3 = new BankAccount(2, "PUNB0010", "prerak Tak", AccountType.valueOf("fd".toUpperCase()), 10000,
				LocalDate.parse("2015-08-18"));
		PrimaryKey p4 = new PrimaryKey(3, "PUNB0010");
		BankAccount acc4 = new BankAccount(3, "PUNB0010", "Hansa Tak", AccountType.valueOf("current".toUpperCase()),
				500, LocalDate.parse("2023-08-18"));
		PrimaryKey p5 = new PrimaryKey(2, "PUNB0011");
		BankAccount acc5 = new BankAccount(2, "PUNB0011", "Govinda Tak", AccountType.valueOf("current".toUpperCase()),
				500, LocalDate.parse("2010-08-18"));
		PrimaryKey p6 = new PrimaryKey(3, "PUNB0011");
		BankAccount acc6 = new BankAccount(3, "PUNB0011", "kusum Tak", AccountType.valueOf("saving".toUpperCase()),
				1000, LocalDate.parse("2022-11-18"));

		System.out.println(bankMap.put(p4, acc4));
		// System.out.println(bankMap.put(p4,acc4));
		System.out.println(bankMap.put(p1, acc1));
		System.out.println(bankMap.put(p2, acc2));
		System.out.println(bankMap.put(p6, acc6));
		System.out.println(bankMap.put(p3, acc3));
		System.out.println(bankMap.put(p5, acc5));

	}
}
