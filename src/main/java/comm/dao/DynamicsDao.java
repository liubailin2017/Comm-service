package comm.dao;

import java.util.ArrayList;

import bean.Dynamics;

public interface DynamicsDao {
	/**
	 * 添加动态
	 * @param stu
	 * @param dynamics
	 * @return
	 */
	public boolean addDynamics(Long stu,Dynamics dynamics);
	
	/**
	 * 找动态图片
	 * @param id
	 * @return
	 */
	public byte[] findImg(Long id);
	
	/**
	 * 跟据学生查找动态
	 * @param stu_nmb
	 * @return
	 */
	public ArrayList<Dynamics> findDynamicsByStu(Long stu_nmb,int pageNo,int pageSize);
	public int getTotalRecordsByStu(long stu_nmb);
	
	/**
	 * 跟据学校查找动态
	 * @param schoolId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Dynamics> findBynamicsBySch(int schoolId,int pageNo,int pageSize);
	public int getTotalRecordsBySch(int schoolId);
	
	/**
	 * 查找有最新消息的动态
	 * @param stu_nmb
	 * @return
	 */
	public ArrayList<Dynamics> findDynOfNewByStu(Long stu_nmb);
	/**
	 * 删除动态
	 * @param dynId
	 * @return
	 */
	public int delDynById(Long dynId);
	public Dynamics findDynById(Long dynId);
}
