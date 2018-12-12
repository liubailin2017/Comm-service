package bean;

import java.util.Arrays;
import java.util.Date;
/**
 * ¶¯Ì¬
 * @author liubailin
 *
 */
public class Dynamics {
	private Long dynId;
	private byte[] imgBin;
	private int imgId;
	private String title;
	private String content;
	private Date time;
	private long stu_nmb;
	private String nickName;
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public long getStu_nmb() {
		return stu_nmb;
	}
	
	public void setStu_nmb(long stu_nmb) {
		this.stu_nmb = stu_nmb;
	}
	public Long getDynId() {
		return dynId;
	}
	public void setDynId(Long dynId) {
		this.dynId = dynId;
	}
	public byte[] getImgBin() {
		return imgBin;
	}
	public void setImgBin(byte[] imgBin) {
		this.imgBin = imgBin;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	@Override
	public String toString() {
		return "Dynamics [dynId=" + dynId + ", imgBin=" + Arrays.toString(imgBin) + ", imgId=" + imgId + ", title="
				+ title + ", content=" + content + ", time=" + time + ", stu_nmb=" + stu_nmb + ", nickName=" + nickName
				+ "]";
	}



}
