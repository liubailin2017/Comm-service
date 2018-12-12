package bean;

public class User {
	private Long stu_Nmb;
	private String stu_nickName; 
	private int schoolId;
	private String pw;
	
	
	public User() {
	}


	public User(Long stu_Nmb, String stu_nickName, int schoolId, String pw) {
		super();
		this.stu_Nmb = stu_Nmb;
		this.stu_nickName = stu_nickName;
		this.schoolId = schoolId;
		this.pw = pw;
	}


	public Long getStu_Nmb() {
		return stu_Nmb;
	}


	public void setStu_Nmb(Long stu_Nmb) {
		this.stu_Nmb = stu_Nmb;
	}


	public String getStu_nickName() {
		return stu_nickName;
	}


	public void setStu_nickName(String stu_nickName) {
		this.stu_nickName = stu_nickName;
	}


	public int getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	@Override
	public String toString() {
		return "User [stu_Nmb=" + stu_Nmb + ", stu_nickName=" + stu_nickName + ", schoolId=" + schoolId + ", pw=" + pw
				+ "]";
	}

	
	
}
