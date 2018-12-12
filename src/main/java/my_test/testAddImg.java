package my_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import bean.Dynamics;
import comm.dao.CommentDao;
import comm.dao.DynamicsDao;
import comm.dao.UserDao;
import comm.dao.imp.CommentDaoImp;
import comm.dao.imp.DynamicsDaoImp;
import comm.dao.imp.UserDaoImp;
import comm.db.DBCon;

public class testAddImg {
	public static void main(String [] args) {
//		DBCon dbcon = DBCon.getInstance();
//		File file = new File("‪a.png");
//		 byte[] b = new byte[(int) file.length()];
//		 System.out.println(file.length());
//         try {
//        	FileInputStream fileinput = new FileInputStream(file);
//        	fileinput.read(b);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         
//		dbcon.doUpdate("insert into user values (2224,1,'123121',?,'PENGUIN')",new Object[] {
//				b
//		});
		
//		File file = new File("‪a.png");
//		 byte[] b = new byte[(int) file.length()];
//		 System.out.println(file.length());
//        try {
//       	FileInputStream fileinput = new FileInputStream(file);
//       	fileinput.read(b);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		DynamicsDao d= new DynamicsDaoImp();
//		Dynamics dyna = new Dynamics();
//		dyna.setContent("balabala");
//		dyna.setTime(new Date());
//		dyna.setTitle("hello");
//		dyna.setImgBin(b);
//		d.addDynamics(163796L,dyna);
		
//		try {
//			System.out.println(new String(new DynamicsDaoImp().findImg(7L),"utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
		 
//		CommentDao dao2 = new CommentDaoImp();
//		System.out.println(dao2.findCommentsByDyn(1l).toString());
		
		UserDaoImp userdao = new UserDaoImp();
		System.out.println(userdao.findUser(163796L,"123456"));;
	}
}
