package bean.json;

import bean.Comment;
/**
 * ����
 * @author liubailin
 *
 */
public class Comm {
	private Comment comment;
	/**
	 * ˭����������
	 */
	private long stu_nmb;
	private String stu_nickName;
	/**
	 * ��ʶ��̬�µ�����
	 */
	private long dyn; 
	/**
	 * �ظ��������ۻ�ظ������ۺͻظ���һ���ࣩ�����Ϊ-1 ����ֱ�ӻظ��Ķ�̬
	 */
	private long com_commnet_id;
	
	public String getStu_nickName() {
		return stu_nickName;
	}
	public void setStu_nickName(String stu_nickName) {
		this.stu_nickName = stu_nickName;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public long getStu_nmb() {
		return stu_nmb;
	}
	public void setStu_nmb(long stu_nmb) {
		this.stu_nmb = stu_nmb;
	}
	public long getDyn() {
		return dyn;
	}
	public void setDyn(long dyn) {
		this.dyn = dyn;
	}
	public long getCom_commnet_id() {
		return com_commnet_id;
	}
	public void setCom_commnet_id(long com_commnet_id) {
		this.com_commnet_id = com_commnet_id;
	}
	@Override
	public String toString() {
		return "Comm [comment=" + comment + ", stu_nmb=" + stu_nmb + ", stu_nickName=" + stu_nickName + ", dyn=" + dyn
				+ ", com_commnet_id=" + com_commnet_id + "]";
	}
}
