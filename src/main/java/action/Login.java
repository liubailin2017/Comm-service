package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import bean.User;
import bean.json.Result;
import comm.dao.UserDao;
import comm.dao.imp.UserDaoImp;
import util.Cofig;
import util.tool;

/**
 * passwd
 * stu_nmb
 * @author liubailin
 *
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long stuNmb = 0L;
		String pw = "";
		Result resultBean = new Result();
		String  result = "S";
		String resultMsg = "";
		String tmp = "";
		User user =null;
		
		String requesttmp;
		user = (User) request.getSession().getAttribute(Cofig.loginUserTag);

		requesttmp =request.getParameter("stu_nmb");
		if(requesttmp != null && tool.isNumericzidai(requesttmp)) {
			stuNmb = Long.parseLong(requesttmp);
		}
		
		/*获取 密码参数*/
		tmp = request.getParameter("passwd");
		if(tmp !=null || "".equals(tmp)) {
			pw = tmp;
		}else {
			result="F";
			resultMsg +="密码不能为空";
		}
		System.out.println("Login.class: pwd:"+pw+"stunmb:"+stuNmb);
		if(result.equals("F")) {
			resultBean.setResult("F");
			resultBean.setMsg(resultMsg);
		}else {
			UserDao dao = new UserDaoImp();
			user = dao.findUser(stuNmb, pw);
			if(user != null) {
				request.getSession().setAttribute(Cofig.loginUserTag, user);
				resultBean.setResult("S");
				resultBean.setUser(user.getStu_Nmb());
				resultBean.setNickname(user.getStu_nickName());
			}else {
				resultBean.setResult("F");
				resultBean.setMsg("验证错误 :-(");
			}
		}
		response.getWriter().append(JSON.toJSONString(resultBean));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
