package comm.dao.imp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import bean.Dynamics;
import comm.dao.DynamicsDao;
import comm.db.DBCon;

public class DynamicsDaoImp implements DynamicsDao {

	private String sql_adddynamics = "insert into dynamics(DYN_ID,STU_NMB,CONTENT,TITLE,IMGS,TIME) values(NULL,?,?,?,?,?)";
	private String sql_findImg = "select IMGS from dynamics where DYN_ID=?";
	
	private String sql_findDynamicsByStu = "select DYN_ID,STU_NMB,CONTENT,TITLE,TIME,nickname from dynamics natural join user where STU_NMB = ? order by TIME DESC limit ?,?";
	private String sql_totalByStu="select count(*) as total from (select DYN_ID from dynamics where STU_NMB = ?) alldyn";
	
	private String sql_findDynamicsBySch = "select DYN_ID,STU_NMB,CONTENT,TITLE,TIME,nickname from dynamics natural join user where SCH_NMB = ? order by TIME DESC limit ?,?";
	private String sql_totalBySch="select count(*) as total from (select DYN_ID from dynamics natural join user where SCH_NMB = ?) alldyn";

	public boolean addDynamics(Long stu, Dynamics dynamics) {
		DBCon dbc = DBCon.newInstance();
		dbc.doUpdate(sql_adddynamics,new Object[] {
			stu,dynamics.getContent(),dynamics.getTitle(),dynamics.getImgBin(),dynamics.getTime().getTime()
		});
		dbc.close();
		return true;
	}


	public byte[] findImg(Long id) {
		byte[] bytes = null;
		DBCon dbc = DBCon.newInstance();

		ResultSet set = dbc.doQuery(sql_findImg,new Object[] {
			id	
		});
		try {
			if(set.next()) {
				Blob blob = set.getBlob("IMGS");
				if(blob!=null) {
					bytes = new byte[(int) blob.length()];
					blob.getBinaryStream().read(bytes);
				}
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbc.close();
		return bytes;
	}

	public int getTotalRecordsByStu(long stu_nmb){
		int total=0;
		DBCon dbc = DBCon.newInstance();
		try {
			ResultSet rs=dbc.doQuery(sql_totalByStu,new Object[]{stu_nmb});
			if(rs.next()){
				total=rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		dbc.close();
		return total;
	}
	
	public ArrayList<Dynamics> findDynamicsByStu(Long stu_nmb,int pageNo,int pageSize) {
		ArrayList<Dynamics> dynamics = new ArrayList();
		int startP =(pageNo-1)*pageSize;
		DBCon dbc = DBCon.newInstance();
		ResultSet set = dbc.doQuery(sql_findDynamicsByStu, new Object[] {stu_nmb,startP,pageSize});
		
		try {
			while(set.next()) {
				Dynamics dyn = new Dynamics();
				
				dyn.setTime(new Date(set.getLong("TIME")));
				dyn.setTitle(set.getString("TITLE"));
				dyn.setContent(set.getString("CONTENT"));
				dyn.setImgId(set.getInt("DYN_ID"));
				dyn.setDynId(set.getLong("dyn_id"));
				dyn.setStu_nmb(set.getLong("stu_nmb"));
				dyn.setNickName(set.getString("nickname"));
				dynamics.add(dyn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
		return dynamics;
	}

	public ArrayList<Dynamics> findBynamicsBySch(int schoolId,int pageNo,int pageSize) {
		ArrayList<Dynamics> dynamics = new ArrayList();
		int startP =(pageNo-1)*pageSize;
		DBCon dbc = DBCon.newInstance();
		ResultSet set = dbc.doQuery(sql_findDynamicsBySch, new Object[] {schoolId,startP,pageSize});
		
		try {
			while(set.next()) {
				Dynamics dyn = new Dynamics();
				dyn.setTime(new Date(set.getLong("TIME")));
				dyn.setTitle(set.getString("TITLE"));
				dyn.setContent(set.getString("CONTENT"));
				dyn.setImgId(set.getInt("DYN_ID"));
				dyn.setDynId(set.getLong("dyn_id"));
				dyn.setStu_nmb(set.getLong("stu_nmb"));
				dyn.setNickName(set.getString("nickname"));
				dynamics.add(dyn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
		return dynamics;
	}
	
	public int getTotalRecordsBySch(int schoolId){
		int total=0;
		DBCon dbc = DBCon.newInstance();
		try {
			ResultSet rs=dbc.doQuery(sql_totalBySch,new Object[]{schoolId});
			if(rs.next()){
				total=rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		dbc.close();
		return total;
	}
	
	String sql_findDynForComment = 
			"select * from dynamics natural join user where dyn_id in " //评论所属的动态
			+ "( select distinct dyn_id from comment where com_comment_id in" // 连接到？的评论
			+ "(select comment_id from comment where stu_nmb = ?) "//?的评论
			+ "and isnew = 1)"; 
	
	String sql_findDynForDyn = 
			"select* from dynamics natural join user where dyn_id in (select dyn_id from comment where com_comment_id = -1 and isnew =1) and stu_nmb =?";
			
	public ArrayList<Dynamics> findDynOfNewByStu(Long stu_nmb) {
				ArrayList<Dynamics> dynamics = new ArrayList();
				DBCon dbc = DBCon.newInstance();
				ResultSet set = dbc.doQuery(sql_findDynForComment, new Object[] {stu_nmb});
				try {
					while(set.next()) {
						Dynamics dyn = new Dynamics();
						dyn.setTime(new Date(set.getLong("TIME")));
						dyn.setTitle(set.getString("TITLE"));
						dyn.setContent(set.getString("CONTENT"));
						dyn.setImgId(set.getInt("DYN_ID"));
						dyn.setDynId(set.getLong("dyn_id"));
						dyn.setStu_nmb(set.getLong("stu_nmb"));
						dyn.setNickName(set.getString("nickname"));
						dynamics.add(dyn);
	
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				set = dbc.doQuery(sql_findDynForDyn, new Object[] {stu_nmb});
				try {
					while(set.next()) {
						Dynamics dyn = new Dynamics();
						dyn.setTime(new Date(set.getLong("TIME")));
						dyn.setTitle(set.getString("TITLE"));
						dyn.setContent(set.getString("CONTENT"));
						dyn.setImgId(set.getInt("DYN_ID"));
						dyn.setDynId(set.getLong("dyn_id"));
						dyn.setStu_nmb(set.getLong("stu_nmb"));
						dyn.setNickName(set.getString("nickname"));
						dynamics.add(dyn);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return dynamics;
	}
	
	/**
	 * 这两条要用事务处理
	 */
	
	String sql_delCommentById = "delete from comment where dyn_id = ?";
	String sql_delDynById = "delete from dynamics where dyn_id = ?";
	String sql_queryDynById = "select * from dynamics natural join user where dyn_id = ?";
	
	public int delDynById(Long dynId) {
		DBCon dbc = DBCon.newInstance();
		int res = dbc.doUpdate(sql_delCommentById, new Object[] {dynId});
		res += dbc.doUpdate(sql_delDynById, new Object[] {dynId});
		return res;
	}
	
	
	public Dynamics findDynById(Long dynId) {
		ArrayList<Dynamics> dynamics = new ArrayList();
		DBCon dbc = DBCon.newInstance();
		ResultSet set = dbc.doQuery(sql_queryDynById, new Object[] {dynId});
		Dynamics dyn = null;
		try {
			if(set.next()) {
				dyn = new Dynamics();		
				dyn.setTime(new Date(set.getLong("TIME")));
				dyn.setTitle(set.getString("TITLE"));
				dyn.setContent(set.getString("CONTENT"));
				dyn.setImgId(set.getInt("DYN_ID"));
				dyn.setDynId(set.getLong("dyn_id"));
				dyn.setStu_nmb(set.getLong("stu_nmb"));
				dyn.setNickName(set.getString("nickname"));
				dynamics.add(dyn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dyn;
	}
}
