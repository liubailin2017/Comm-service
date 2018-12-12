package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.User;
import bean.json.Comm;
import comm.dao.CommentDao;
import comm.dao.imp.CommentDaoImp;
import util.Cofig;
import util.tool;
/**
 * dynId 
 * @author liubailin
 *
 */
@SuppressWarnings("serial")
@WebServlet("/getcomments.do")
public class GetComments extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requesttmp;
		User user = new User();
		user = (User) request.getSession().getAttribute(Cofig.loginUserTag);

		long dynId = -2; //让它默认为-2 也就是没有给参数时，是会插入失败的。
		requesttmp =request.getParameter("dynId");
		if(requesttmp != null && tool.isNumericzidai(requesttmp)) {
			dynId = Long.parseLong(requesttmp);
		}
		
		CommentDao dao = new CommentDaoImp();
		ArrayList<Comm> comms = dao.findCommentsByDyn(dynId,user.getStu_Nmb());

		Map<String, ArrayList<Comm>> map = new HashMap<String, ArrayList<Comm>>();
		map.put("comms", comms);
		response.setContentType("application/json");
		response.getWriter().print(
				JSON.toJSON(map)
		);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
