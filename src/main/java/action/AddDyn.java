package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Dynamics;
import bean.User;
import comm.dao.DynamicsDao;
import comm.dao.imp.DynamicsDaoImp;
import util.Cofig;
/**
 * ��Ӷ�̬����Ҫ��¼��������
 * 
 * ����ʽ �� post
 * 
 * ����
 * img1 :ͼƬ
 * content : ����
 * title �� ����
 * 
 * @author liubailin
 *
 */

@WebServlet("/addDyn.do")
public class AddDyn extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().append("{\"result\":\"F\",\"msg\":\"����post��ʽ\"}");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        // ����Ƿ�Ϊ��ý���ϴ�
		        if (!ServletFileUpload.isMultipartContent(request)) {
		            // ���������ֹͣ
		            PrintWriter writer = response.getWriter();
		            writer.println("Error: ��������� enctype=multipart/form-data");
		            writer.flush();
		            return;
		        }
		 
		    	byte b[] = null;
		        String content = "";
		        String title ="";
		        long stu;
		        User user = (User) request.getSession().getAttribute(Cofig.loginUserTag);
		        if(user == null) {
		        	response.getWriter().append("{\"result\":\"F\",\"msg\":\"���¼\",\"code\":\"0\"}");
		        	return;
		        }
		        stu = user.getStu_Nmb();
		        // �����ϴ�����
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		        factory.setSizeThreshold(1024*1024);
		        // ������ʱ�洢Ŀ¼
		        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		 
		        ServletFileUpload upload = new ServletFileUpload(factory);
		    
		        // ��������ļ��ϴ�ֵ
		        upload.setFileSizeMax(1024*1024*5);
		         
		        // �����������ֵ (�����ļ��ͱ�����)
		        upload.setSizeMax(1024*1024*5+1024*5);
		        
		        // ���Ĵ���
		        upload.setHeaderEncoding("UTF-8"); 

//		        // ������ʱ·�����洢�ϴ����ļ�
//		        // ���·����Ե�ǰӦ�õ�Ŀ¼
//		        String uploadPath = getServletContext().getRealPath("/") + File.separator + 1;
//		       
//		         
//		        // ���Ŀ¼�������򴴽�
//		        File uploadDir = new File(uploadPath);
//		        if (!uploadDir.exists()) {
//		            uploadDir.mkdir();
//		        }
//		 

		        try {
		            // ���������������ȡ�ļ�����
		            List<FileItem> formItems = upload.parseRequest(request);
//		            System.out.println("+++++++++++++"+formItems.toString());
//	
		            if (formItems != null && formItems.size() > 0) { 
		                // ����������
		                for (FileItem item : formItems) {
		                    // �����ڱ��е��ֶ�
		                    if (item.isFormField()) {
		                    	if(item.getFieldName().equals("title")) {
		                    		title = item.getString("utf-8");
		                    	}
		                    	if(item.getFieldName().equals("content")) {
		                    		content = item.getString("utf-8");
		                    	}
		                    	
		                    }else {
		                    	if(item.getFieldName().equals("img1")) {
			                    	b = new byte[(int) item.getSize()];
			                    	item.getInputStream().read(b);
		                    	}
		                    }
		                }
		            }
		        } catch (Exception ex) {
		         ex.printStackTrace();
		        }
		        
		Dynamics dyn = new Dynamics();
		dyn.setTime(new Date());
		dyn.setContent(content);
		dyn.setTitle(title);
		dyn.setImgBin(b);
		dyn.setStu_nmb(stu);
		DynamicsDao dao = new DynamicsDaoImp();
				
		System.out.println("���붯̬��" + dyn.toString());

		dao.addDynamics(user.getStu_Nmb(), dyn);
		response.setContentType("application/json");
		response.getWriter().println("{ \"result\":\"S\",\"user\":\""+user.getStu_Nmb()+"\"}");
	}

}
