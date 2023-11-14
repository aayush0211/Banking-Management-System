package util;

import java.time.LocalDate;
import java.util.Map;

import core.BankAccount;
import core.PrimaryKey;

public class BankAccountValidation {

	public static void duplicateValidation(String ifscCode,int accNo,Map<PrimaryKey,BankAccount>bankMap) throws BankAccountException
	{
		PrimaryKey tempKey=new PrimaryKey(accNo,ifscCode);
		if(bankMap.containsKey(tempKey))
		{
			throw new BankAccountException("Acoount already exist !");
		}
	}
	public static void regAmtValidation(int amount,AccountType types) throws BankAccountException
	{
		if(amount<types.getMinBalance())
			throw new BankAccountException("registration amount never match with min balance of your account type !");
	}
	public static int checkBalance(int amount,BankAccount acc) throws BankAccountException
	{	if(!acc.isActive()) throw new BankAccountException("Account Inactive !");
		if(acc.getBalance()-amount<acc.getAccType().getMinBalance()) 
			throw new BankAccountException("Insufficent Balance !");
		return amount;
	}
	public static void transactionValidation(BankAccount acc) throws BankAccountException
	{
		if(!(acc.isKycStatus())) 
			throw new BankAccountException("You can't do transaction. First Submit your KYC !");
	}
	public static void checkActive(BankAccount acc) throws BankAccountException
	{
		if(!(acc.isActive())) 
			throw new BankAccountException("You can't do transaction. your account Inactive. Contact to your Branch Manager!");
	}
	
	public static BankAccount signInValidation(int accNo,String ifsc,Map<PrimaryKey,BankAccount>bankmap) throws BankAccountException
	{
		PrimaryKey tempKey=new PrimaryKey(accNo,ifsc);
		BankAccount ac=bankmap.get(tempKey);
		if(ac==null) throw new BankAccountException("Invalid A/c no or ifsc code !");
		return ac;
	}
	
	// registration validation
//	public static BankAccount fullRegistrationValidation(int accNo,String ifsc,Kyc kyc, String customerName,String accType,int balance,String createDate,Map<PrimaryKey,BankAccount>bankMap) throws BankAccountException
//	{
//		duplicateValidation(ifsc,accNo,bankMap);
//		AccountType temp=AccountType.valueOf(accType.toUpperCase());
//		regAmtValidation(balance,temp);
//		LocalDate cdate=LocalDate.parse(createDate);
//		// all validation happen let's register
//		BankAccount acc=new BankAccount(accNo,ifsc,kyc,customerName,temp,balance,cdate);
//		return acc;
//		
//	}
	public static BankAccount RegistrationValidation(int accNo,String ifsc, String customerName,String accType,int balance,String createDate,Map<PrimaryKey,BankAccount>bankMap) throws BankAccountException
	{
		duplicateValidation(ifsc,accNo,bankMap);
		AccountType temp=AccountType.valueOf(accType.toUpperCase());
		regAmtValidation(balance,temp);
		LocalDate cdate=LocalDate.parse(createDate);
		// all validation happen let's register
		BankAccount acc=new BankAccount(accNo,ifsc,customerName,temp,balance,cdate);
		return acc;
		
	}
}
