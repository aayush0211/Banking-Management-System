package util;
//In KYC : customer email , phone no , Aadhar Card No ...) n link KYC info to BakAccount
public class Kyc {
		
	private String email;
	private long phoneNo;
	private long aadharCardNo;
	
	
	public Kyc(String email, long phoneNo, long aadharCardNo) {
		super();
		this.email = email;
		this.phoneNo = phoneNo;
		this.aadharCardNo = aadharCardNo;
	}

	@Override
	public String toString() {
		return " email=" + email + ", phoneNo=" + phoneNo + ", aadharCardNo=" + aadharCardNo + " ";
	}
	
	
}
