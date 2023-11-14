package tester;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import core.BankAccount;
import core.PrimaryKey;
import util.BankOperations;
import static util.BankOperations.*;

public class BankMangementSystem implements BankOperations {

	public static void main(String[] args) {
		// Intialization phase start
		boolean flag = false;
		Map<PrimaryKey, BankAccount> bankMap = new HashMap<>();
		populateMap(bankMap);
// service part start
		try (Scanner sc = new Scanner(System.in)) {
			while (!flag) {
				System.out.println("options as :- 1. Open new account\r\n" + "2. Get Account summary\n"
						+ "3. Withdraw amount\n" + "4. Deposit amount\n" + "5. Transfer Funds\n" + "6. Close Account\n"
						+ "7. Display all account details\n"
						+ "8. unactive all accounts who not use account from 1 year\n"
						+ "9. Sort accounts as per ifscCode and acc no\r\n" + "10. Sort a/cs as per account balance\r\n"
						+ "11. Sort a/cs as per a/c type n creation date.\n" + "12.Complete Kyc\n" + "13.exit");
				System.out.println("enter your choice :");
				try {
					switch (sc.nextInt()) {
					case 1:
						openNewAccount(bankMap, sc);
						break;
					case 2:
						getAccountSummary(sc, bankMap);
						break;
					case 3:
						withdraw(sc, bankMap);
						break;
					case 4:
						deposit(sc, bankMap);
						break;
					case 5:
						transferFund(sc, bankMap);
						break;
					case 6:
						closeAccount(sc, bankMap);
						break;
					case 7:
						displayAll(bankMap);
						break;
					case 8:
						unActiveAcc(bankMap);
						break;
					case 9:
						sortByPrimaryKey(bankMap);
						break;
					case 10:
						sortByBalance(bankMap);
						break;
					case 11:
						sortByAcTypeAndCreateDate(bankMap);
						break;
					case 12:
						completeKyc(sc, bankMap);
						break;
					case 13:// distroy part start here
						System.out.println("Thank you !");
						flag = true;
						break;
					default:
						System.out.println("Enter correct choice !");
					}

				} catch (Exception e) {
					e.printStackTrace();
					sc.nextLine();
				}
			}
		}

	}

}
