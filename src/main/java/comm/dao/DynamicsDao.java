package comm.dao;

import java.util.ArrayList;

import bean.Dynamics;

public interface DynamicsDao {
	/**
	 * ��Ӷ�̬
	 * @param stu
	 * @param dynamics
	 * @return
	 */
	public boolean addDynamics(Long stu,Dynamics dynamics);
	
	/**
	 * �Ҷ�̬ͼƬ
	 * @param id
	 * @return
	 */
	public byte[] findImg(Long id);
	
	/**
	 * ����ѧ�����Ҷ�̬
	 * @param stu_nmb
	 * @return
	 */
	public ArrayList<Dynamics> findDynamicsByStu(Long stu_nmb,int pageNo,int pageSize);
	public int getTotalRecordsByStu(long stu_nmb);
	
	/**
	 * ����ѧУ���Ҷ�̬
	 * @param schoolId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Dynamics> findBynamicsBySch(int schoolId,int pageNo,int pageSize);
	public int getTotalRecordsBySch(int schoolId);
	
	/**
	 * ������������Ϣ�Ķ�̬
	 * @param stu_nmb
	 * @return
	 */
	public ArrayList<Dynamics> findDynOfNewByStu(Long stu_nmb);
	/**
	 * ɾ����̬
	 * @param dynId
	 * @return
	 */
	public int delDynById(Long dynId);
	public Dynamics findDynById(Long dynId);
}
