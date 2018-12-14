package comm.db;

import java.sql.*;

import util.Cofig;

public class DBCon {
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://www.hqhworld.cn:3306/comm?characterEncoding=utf8";
	private String urlPage616321 = "jdbc:mysql://www.hqhworld.cn:3306/page616321?characterEncoding=utf8";
	private String user="liubailin";
	private String password=Cofig.dbPwd;
	private Connection conn=null;
	
	private DBCon() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DBCon(int dbsource) { // 连接到page616321
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(urlPage616321, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return conn;
	}

	public static DBCon newInstance() {
		return new DBCon();
	}
	public static DBCon newInstance(int dbsource) {
		return new DBCon(dbsource);
	}
	
	public ResultSet doQuery(String sql,Object[] params){
		ResultSet rs=null;
		conn=this.getConnection();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			rs=pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public int doUpdate(String sql,Object[] params){
		int res = 0;
		conn=this.getConnection();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			res=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void close(){
		try {
			if(conn!=null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
