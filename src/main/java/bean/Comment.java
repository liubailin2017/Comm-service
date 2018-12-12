package bean;

import java.util.Date;
/**
 * 评论，数据库中映射
 * @author liubailin
 *
 */
public class Comment {
	private Long comment_id;
	private String content;
	private Date date;

	public Long getComment_id() {
		return comment_id;
	}
	
	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", content=" + content + ", date=" + date + "]";
	}


	
}
