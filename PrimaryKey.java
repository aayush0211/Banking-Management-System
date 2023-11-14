package core;

import java.util.Objects;

public class PrimaryKey implements Comparable<PrimaryKey> {
	
	private Integer accountNo;
	private String ifscCode;
	
	
	

	public PrimaryKey(int accountNo, String ifscCode) {
		super();
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
	}




	@Override
	public int hashCode() {
		return Objects.hash(accountNo, ifscCode);
	}




	@Override // 1st see ifscCode then accountNo
	public boolean equals(Object obj) {
		if(obj instanceof PrimaryKey)
		{
			PrimaryKey temp=(PrimaryKey)obj;
			if(ifscCode.equals(temp.ifscCode))
				return accountNo.equals(temp.accountNo);
		}
			return false;
		
	}

	


	@Override
	public String toString() {
		return " accountNo= " + accountNo + ", ifscCode=" + ifscCode + " ";
	}




	@Override // 1st see ifscCode then accountNo
	public int compareTo(PrimaryKey o) {
	
		if((ifscCode.compareTo(o.ifscCode))==0)
			return accountNo.compareTo(o.accountNo);
		
		
		return ifscCode.compareTo(o.ifscCode);
	}

}
