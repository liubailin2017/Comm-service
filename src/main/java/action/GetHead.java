package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.dao.UserDao;
import comm.dao.imp.UserDaoImp;

@WebServlet("/head.png")
public class GetHead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("stu_nmb"));
		
		response.setContentType("image/png");
		response.setHeader("Content-Disposition", "filename=\"head.png\"");
		
		byte[] bytes = null;
		UserDao dao = new UserDaoImp();
		bytes = dao.findHead(id);
		if(bytes != null) {
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
		}
		else
			response.setContentLength(0);
		
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
