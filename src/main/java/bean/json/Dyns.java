package bean.json;

public class Dyns {
	private long stu_nmb;
	private int schoolId;
	private PageBean dyns;
	
	public long getStu_nmb() {
		return stu_nmb;
	}
	public void setStu_nmb(long stu_nmb) {
		this.stu_nmb = stu_nmb;
	}
	public PageBean getDyns() {
		return dyns;
	}
	public void setDyns(PageBean dyns) {
		this.dyns = dyns;
	}
	
	
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	@Override
	public String toString() {
		return "Dyns [stu_nmb=" + stu_nmb + ", dyns=" + dyns + "]";
	}

	
}
