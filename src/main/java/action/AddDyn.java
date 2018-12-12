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
 * 添加动态，需要登录后才能添加
 * 
 * 请求方式 ： post
 * 
 * 参数
 * img1 :图片
 * content : 内容
 * title ： 标题
 * 
 * @author liubailin
 *
 */

@WebServlet("/addDyn.do")
public class AddDyn extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().append("{\"result\":\"F\",\"msg\":\"请用post方式\"}");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        // 检测是否为多媒体上传
		        if (!ServletFileUpload.isMultipartContent(request)) {
		            // 如果不是则停止
		            PrintWriter writer = response.getWriter();
		            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
		            writer.flush();
		            return;
		        }
		 
		    	byte b[] = null;
		        String content = "";
		        String title ="";
		        long stu;
		        User user = (User) request.getSession().getAttribute(Cofig.loginUserTag);
		        if(user == null) {
		        	response.getWriter().append("{\"result\":\"F\",\"msg\":\"请登录\",\"code\":\"0\"}");
		        	return;
		        }
		        stu = user.getStu_Nmb();
		        // 配置上传参数
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		        factory.setSizeThreshold(1024*1024);
		        // 设置临时存储目录
		        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		 
		        ServletFileUpload upload = new ServletFileUpload(factory);
		    
		        // 设置最大文件上传值
		        upload.setFileSizeMax(1024*1024*5);
		         
		        // 设置最大请求值 (包含文件和表单数据)
		        upload.setSizeMax(1024*1024*5+1024*5);
		        
		        // 中文处理
		        upload.setHeaderEncoding("UTF-8"); 

//		        // 构造临时路径来存储上传的文件
//		        // 这个路径相对当前应用的目录
//		        String uploadPath = getServletContext().getRealPath("/") + File.separator + 1;
//		       
//		         
//		        // 如果目录不存在则创建
//		        File uploadDir = new File(uploadPath);
//		        if (!uploadDir.exists()) {
//		            uploadDir.mkdir();
//		        }
//		 

		        try {
		            // 解析请求的内容提取文件数据
		            List<FileItem> formItems = upload.parseRequest(request);
//		            System.out.println("+++++++++++++"+formItems.toString());
//	
		            if (formItems != null && formItems.size() > 0) { 
		                // 迭代表单数据
		                for (FileItem item : formItems) {
		                    // 处理不在表单中的字段
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
				
		System.out.println("插入动态：" + dyn.toString());

		dao.addDynamics(user.getStu_Nmb(), dyn);
		response.setContentType("application/json");
		response.getWriter().println("{ \"result\":\"S\",\"user\":\""+user.getStu_Nmb()+"\"}");
	}

}
