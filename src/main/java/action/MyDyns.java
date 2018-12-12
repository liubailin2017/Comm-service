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
 * 获取指定人的动态
 * 
 * 参数
 * stu_nmb 学号(如果为空则默认为自己的学号）
 * pageNo 第几页
 * pageSize 每页大小
 */
@WebServlet("/myDyns.do")
public class MyDyns extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =  ((User)request.getSession().getAttribute(Cofig.loginUserTag));
		long nmb = 0L;
			if(user != null) nmb = user.getStu_Nmb();
		
		int pageNo=1;
		int pageSize=10;
		
		Dyns dyns = new Dyns();
		
		String requesttmp = request.getParameter("stu_nmb");
		if(requesttmp !=null && tool.isNumericzidai(requesttmp))
			nmb = Long.parseLong(requesttmp);
		
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
				dao.findDynamicsByStu(nmb, pageNo, pageSize)
				);
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalRecords(dao.getTotalRecordsByStu(nmb));
		
		dyns.setStu_nmb(nmb);
		dyns.setDyns(pageBean);
		response.setContentType("application/json");
		response.getWriter().println(
				JSON.toJSONString(dyns)
		);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
