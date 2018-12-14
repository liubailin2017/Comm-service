package comm.dao.imp;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import comm.dao.UserDao;
import comm.db.DBCon;
import util.MD5;

public class UserDaoImp implements UserDao {
	private String sql_updatePw = "update classmate set pwd = ? where stu_nmb = ? and pwd=?";
	private String sql_finduser ="select * from classmate where stu_nmb=? and pwd=?";


	/** ------------------- 上面语句是page616321的数据库-----***/
	private String sql_findHead = "select head_img from user where stu_nmb=?";
	private String sql_findName = "select nickname from user where stu_nmb=?";
	

	public User findUser(Long nmb, String pw) {
		User user =null;
		DBCon dbc = DBCon.newInstance(1);
		ResultSet set = dbc.doQuery(sql_finduser, new String[] {
			nmb+"", MD5.md5(pw)
		});
		
		try {
			if(set.next()) {
				user = new User();
				user.setPw(set.getString("pwd"));
				user.setSchoolId(1);
				user.setStu_Nmb(set.getLong("stu_nmb"));
				user.setStu_nickName(set.getString("nickname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();

		dbc = DBCon.newInstance();
		if(user != null) {
			dbc.doUpdate("insert into user(stu_nmb,SCH_NMB,pass_wd,nickname) values(?,?,?,?)", new Object[]{
					user.getStu_Nmb(),user.getSchoolId(),user.getPw(),user.getStu_nickName()
			});
		}
		dbc.close();
		return user;
	}

	public byte[] findHead(Long stu_nmb) {
		byte[] bytes = null;
		DBCon dbc = DBCon.newInstance();

		ResultSet set = dbc.doQuery(sql_findHead,new Object[] {
			stu_nmb
		});
		try {
			if(set.next()) {
				Blob blob = set.getBlob("head_img");
				bytes = new byte[(int) blob.length()];
				blob.getBinaryStream().read(bytes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbc.close();
		return bytes;
	}

	public String findName(Long nmb) {
		String nickName = "";
		DBCon dbc = DBCon.newInstance();

		ResultSet set = dbc.doQuery(sql_findName,new Object[] {
			nmb
		});
		try {
			if(set.next()) {
				nickName = set.getString("nickname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
		return nickName;
	}

	public int updatePw(Long nmb,String oldPw, String newPw) {
		 DBCon dbc = DBCon.newInstance(1);
		return dbc.doUpdate(sql_updatePw, new String[] {
				 MD5.md5(newPw),nmb.toString(),MD5.md5(oldPw)
		 });
	}

	public void updateUser(User user) {
		
	}

	public void updateHead(Long nmb, byte[] headImg) {
		
	}
	
//	public static void main(String[] args) {
//		User user =new UserDaoImp().findUser(163796,1,"123456");
//		System.out.println(user.toString());
//	}
}
