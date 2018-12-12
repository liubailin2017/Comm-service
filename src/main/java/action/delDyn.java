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

		long dynId = -2; //����Ĭ��Ϊ-2 Ҳ����û�и�����ʱ���ǻ����ʧ�ܵġ�
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
						result.setMsg("�ɹ�");
						result.setNickname(user.getStu_nickName());
						result.setUser(user.getStu_Nmb());
						result.setResult("S");
					}
					else {
						result.setMsg("δ֪����");
						result.setResult("F");
					}
				}else {
					result.setResult("F");
					result.setMsg("ֻ��ɾ���Լ��Ķ�̬");
				}
			} else {
				result.setResult("F");
				result.setMsg("û���ҵ�ָ����̬\"");
			}
		}else {
			result.setResult("F");
			result.setMsg("���ȵ�¼");
		}
		response.setContentType("application/json");
		response.getWriter().append(JSON.toJSONString(result));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
