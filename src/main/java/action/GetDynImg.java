package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.dao.DynamicsDao;
import comm.dao.imp.DynamicsDaoImp;

/**
 * id
 */
@SuppressWarnings("serial")
@WebServlet("/dynImg.png")
public class GetDynImg extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		response.setContentType("image/?");
		response.setHeader("Content-Disposition", "filename=\"dynImg.png\"");
		
		byte[] bytes = null;
		DynamicsDao dao = new DynamicsDaoImp();
		bytes = dao.findImg(id);
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
