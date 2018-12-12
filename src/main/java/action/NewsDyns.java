package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Dynamics;
import bean.User;
import bean.json.NewDyns;
import comm.dao.DynamicsDao;
import comm.dao.imp.DynamicsDaoImp;
import util.Cofig;
/**
 * 有最新消息的动态
 * @author liubailin
 *
 */
@WebServlet("/newDyns.do")
public class NewsDyns extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =  ((User)request.getSession().getAttribute(Cofig.loginUserTag));
		DynamicsDao dao = new DynamicsDaoImp();
		NewDyns dyns= new NewDyns();
		if(user == null) {
			dyns.setMsg("请先登录");
			dyns.setResult("F");
		}else {
			ArrayList<Dynamics> dynsReally = dao.findDynOfNewByStu(user.getStu_Nmb());
			dyns.setResult("S");
			dyns.setDyns(dynsReally);
			dyns.setMsg("成功返回");
		}
		response.setContentType("application/json");
		response.getWriter().println(
				JSON.toJSONString(dyns)
		);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doGet(request, response);
	}

}
