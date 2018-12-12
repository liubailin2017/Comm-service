package action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import bean.User;
import bean.json.Result;
import comm.dao.UserDao;
import comm.dao.imp.UserDaoImp;
import util.Cofig;
import util.tool;

/**
 * @author liubailin
 *
 */
@WebServlet("/updatePw.do")
public class UpdatePw extends HttpServlet {
	
	/**
	 * 参数
	 * stu_nmb
	 * passwd
	 * newpasswd
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int schoolId = 0;
		Long stuNmb = 0L;
		String pw = null;
		String newpw = null;
		String  result = "S";
		String resultMsg = "";
		String tmp = "";
		User user =null;
		Result resultBean = new Result();
		String requesttmp;
		user = (User) request.getSession().getAttribute(Cofig.loginUserTag);

		requesttmp =request.getParameter("stu_nmb");
		if(requesttmp != null && tool.isNumericzidai(requesttmp)) {
			stuNmb = Long.parseLong(requesttmp);
		}
		
		tmp = request.getParameter("passwd");
		if(tmp !=null || "".equals(tmp)) {
			pw = tmp;
		}
		
		tmp = request.getParameter("newpasswd");
		if(tmp !=null || "".equals(tmp)) {
			newpw = tmp;
		}
		
		System.out.println("UpdatePw.class: pwd:"+pw+"stunmb:"+stuNmb+"newpwd:"+newpw);
		
		UserDao userDao = new UserDaoImp();
		int res = userDao.updatePw(stuNmb, pw, newpw);
		if(res > 0) {
			resultBean.setResult("S");
			resultBean.setUser(stuNmb);
			Map<Long,HttpSession> map = (Map) request.getSession().getServletContext().getAttribute("sessionmap");
			if(map != null) {
				HttpSession session = map.get(stuNmb);
				if(session != null) {
					session.invalidate();
				}
			}
		}else {
			resultBean.setResult("F");
			resultBean.setMsg("验证错误,密码修改失败");
		}
		response.getWriter().append(JSON.toJSONString(resultBean));
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
