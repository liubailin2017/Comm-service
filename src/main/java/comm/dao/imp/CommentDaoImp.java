package comm.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import bean.Comment;
import bean.json.Comm;
import comm.dao.CommentDao;
import comm.db.DBCon;

public class CommentDaoImp implements CommentDao {

	private String sql_addComment = "insert into comment(content,com_comment_id,stu_nmb,dyn_id,time,isnew) values(?,?,?,?,?,?)";
	
	private String sql_findCommentsByDyn = "select comment_id,content,com_comment_id,stu_nmb,dyn_id,time,nickname from comment natural join user where dyn_id=? order by time desc";
	public boolean addComment(Comment comment, Long com_Comment, Long dynId, Long stu_nmb) {
		DBCon dbc = DBCon.newInstance();
		int res = dbc.doUpdate(sql_addComment, new Object[] {
			comment.getContent(),com_Comment,stu_nmb,dynId,comment.getDate().getTime(),true
		});
		
		dbc.close();
		if(res >0)
			return true;
		else
			return false;
	}
	public ArrayList<Comm> findCommentsByDyn(Long dynId) {
		DBCon dbc = DBCon.newInstance();
		ArrayList<Comm> comms = new ArrayList();
		ResultSet set = dbc.doQuery(sql_findCommentsByDyn, new Object[] {dynId});
		try {
			while(set.next()) {
				Comm comm = new Comm();
				Comment comment = new Comment();
				comment.setContent(set.getString("content"));
				comment.setDate(new Date(set.getLong("time")));
				comment.setComment_id(set.getLong("comment_id"));
				comm.setComment(comment);
				comm.setCom_commnet_id(set.getLong("com_comment_id"));
				comm.setDyn(set.getLong("dyn_id"));
				comm.setStu_nmb(set.getLong("stu_nmb"));
				comm.setStu_nickName(set.getString("nickname"));
				comms.add(comm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
		return comms;
	}
	
	String sql1 = "update comment set isnew = 0 where dyn_id = ? and com_comment_id = -1 and dyn_id in (select dyn_id from dynamics where stu_nmb = ?)"; //更新直接回复动态的评论
	/**
	 * 评论 ？ 回复 ？  不存在的，都是一样的。 
	 */
	String sql2 ="	update comment set isnew = 0 where com_comment_id in(select * from (select comment_id from comment where stu_nmb = ?) A) and isnew = 1 and dyn_id = ?"; //对评论的回复
	
	public ArrayList<Comm> findCommentsByDyn(Long dynId,Long stu_nmb) {
		DBCon dbc = DBCon.newInstance();
		ArrayList<Comm> comms = new ArrayList();
		ResultSet set = dbc.doQuery(sql_findCommentsByDyn, new Object[] {dynId});
		try {
			while(set.next()) {
				Comm comm = new Comm();
				Comment comment = new Comment();
				comment.setContent(set.getString("content"));
				comment.setDate(new Date(set.getLong("time")));
				comment.setComment_id(set.getLong("comment_id"));
				comm.setComment(comment);
				comm.setCom_commnet_id(set.getLong("com_comment_id"));
				comm.setDyn(set.getLong("dyn_id"));
				comm.setStu_nmb(set.getLong("stu_nmb"));
				comm.setStu_nickName(set.getString("nickname"));
				comms.add(comm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbc.doUpdate(sql1, new Object[] {dynId,stu_nmb});
		dbc.doUpdate(sql2, new Object[] {stu_nmb,dynId});
		dbc.close();
		return comms;
	}
}
