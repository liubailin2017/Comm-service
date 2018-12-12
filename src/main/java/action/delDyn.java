package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Dynamics;
import bean.User;
import bean.json.Result;
import comm.dao.DynamicsDao;
import comm.dao.imp.DynamicsDaoImp;
import util.Cofig;
import util.tool;

@WebServlet("/delDyn.do")
public class delDyn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requesttmp;
		User user = null;
		String resJson = "";
		user = (User) request.getSession().getAttribute(Cofig.loginUserTag);

		long dynId = -2; //让它默认为-2 也就是没有给参数时，是会插入失败的。
		requesttmp =request.getParameter("dynId");
		if(requesttmp != null && tool.isNumericzidai(requesttmp)) {
			dynId = Long.parseLong(requesttmp);
		}
		
		
		DynamicsDao dao = new DynamicsDaoImp();
		Result result = new Result();
		if(user != null) {
			Dynamics dyn = dao.findDynById(dynId);
			if(dyn != null) {
				if(dyn.getStu_nmb()== user.getStu_Nmb()) {
					int t = dao.delDynById(dynId);
					if( t> 0) {
						result.setMsg("成功");
						result.setNickname(user.getStu_nickName());
						result.setUser(user.getStu_Nmb());
						result.setResult("S");
					}
					else {
						result.setMsg("未知错误");
						result.setResult("F");
					}
				}else {
					result.setResult("F");
					result.setMsg("只能删除自己的动态");
				}
			} else {
				result.setResult("F");
				result.setMsg("没有找到指定动态\"");
			}
		}else {
			result.setResult("F");
			result.setMsg("请先登录");
		}
		response.setContentType("application/json");
		response.getWriter().append(JSON.toJSONString(result));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
