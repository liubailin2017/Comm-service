package comm.dao;

import java.util.ArrayList;

import bean.Comment;
import bean.json.Comm;

public interface CommentDao {
	public boolean addComment(Comment comment,Long com_Comment,Long dynId,Long stu_nmb);
	
	public ArrayList<Comm> findCommentsByDyn(Long dynId);
	/**
	 * 
	 * @param dynId
	 * @param stu_nmb
	 * @return
	 */
	public ArrayList<Comm> findCommentsByDyn(Long dynId,Long stu_nmb);
}
