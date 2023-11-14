package util;

import java.util.Comparator;

import core.BankAccount;

public class SortByAcTypeAndCreateDate implements Comparator<BankAccount> {

	@Override
	public int compare(BankAccount o1, BankAccount o2) {
		// TODO Auto-generated method stub
		if(o1.getAccType().name().compareTo(o2.getAccType().name())==0)
			return o1.getCreateDate().compareTo(o2.getCreateDate());
		return o1.getAccType().name().compareTo(o2.getAccType().name());
	}

	
}
