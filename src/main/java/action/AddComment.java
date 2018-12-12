package action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Comment;
import bean.User;
import comm.dao.CommentDao;
import comm.dao.imp.CommentDaoImp;
import util.Cofig;
import util.tool;

/**
 * content 
 * dynId //要评论的动态
 * com_comment_id  //要回复的评论
 * @author liubailin
 *
 */
@SuppressWarnings("serial")
@WebServlet("/addcommnet.do")
public class AddComment extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requesttmp;
		String content = null;
		User user = null;
		user = (User) request.getSession().getAttribute(Cofig.loginUserTag);
		
		content = request.getParameter("content");
		
		long dynId = -2; //让它默认为-2 也就是没有给参数时，是会插入失败的。
		requesttmp =request.getParameter("dynId");
		if(requesttmp != null && tool.isNumericzidai(requesttmp)) {
			dynId = Long.parseLong(requesttmp);
		}
		
		long com_comment_id = -1;
		requesttmp =request.getParameter("com_comment_id");
		if(requesttmp != null && tool.isNumericzidai(requesttmp)) {
			com_comment_id = Long.parseLong(requesttmp);
		}
		
		CommentDao dao = new CommentDaoImp();
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setDate(new Date());
		if(user == null) {
	       	response.getWriter().append("{\"result\":\"F\",\"msg\":\"请登录\",\"code\":\"0\"}");
	     	return;
	    }
		else {
			System.out.println(""+comment.toString()+";"+com_comment_id+";"+dynId+";"+user.getStu_Nmb());
			if(dao.addComment(comment, com_comment_id, dynId, user.getStu_Nmb()) ) {
				response.getWriter().println("{ \"result\":\"S\",\"user\":\"163796\"}");
			}else {
				response.getWriter().println("{ \"result\":\"F\",\"code\":1,\"user\":\"163796\"}");
			};
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
