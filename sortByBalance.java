package util;

import java.util.Comparator;

import core.BankAccount;

public class sortByBalance implements Comparator<BankAccount> {

	@Override
	public int compare(BankAccount o1, BankAccount o2) {
		// TODO Auto-generated method stub
		Integer temp=o1.getBalance();
		int ret=temp.compareTo(o2.getBalance());
		if(ret==0)
			return o1.getKey().compareTo(o2.getKey());
		return ret;
	}

	
}
