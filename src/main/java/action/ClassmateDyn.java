package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.User;
import bean.json.Dyns;
import bean.json.PageBean;
import comm.dao.DynamicsDao;
import comm.dao.imp.DynamicsDaoImp;
import util.Cofig;
import util.tool;
/**
 * 一个学校同学的动态
 * 
 * @author liubailin
 *
 */
@SuppressWarnings("serial")
@WebServlet("/classMateDyn.do")
public class ClassmateDyn extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =  ((User)request.getSession().getAttribute(Cofig.loginUserTag));
		int schoolId = 0;
			if(user != null) schoolId = user.getSchoolId();
		
		int pageNo=1;
		int pageSize=10;
		
		Dyns dyns = new Dyns();
		
		String requesttmp = request.getParameter("schoolId");
		if(requesttmp !=null && tool.isNumericzidai(requesttmp))
			schoolId = Integer.parseInt(requesttmp);
		
		requesttmp = request.getParameter("pageNo");
		if(requesttmp !=null && tool.isNumericzidai(requesttmp))
			pageNo = Integer.parseInt(requesttmp);
			if(pageNo<=0) pageNo =1;
		requesttmp = request.getParameter("pageSize");
		if(requesttmp !=null && tool.isNumericzidai(requesttmp))
			pageSize = Integer.parseInt(requesttmp);
		
		
		DynamicsDao dao = new DynamicsDaoImp();
		
		PageBean pageBean = new PageBean();
		pageBean.setList(
				dao.findBynamicsBySch(schoolId, pageNo, pageSize)
				);
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalRecords(dao.getTotalRecordsBySch(schoolId));
		if(user !=null)
		dyns.setStu_nmb(user.getStu_Nmb());
		dyns.setDyns(pageBean);
		dyns.setSchoolId(schoolId);
		response.setContentType("application/json");
		response.getWriter().println(
				JSON.toJSONString(dyns)
		);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
