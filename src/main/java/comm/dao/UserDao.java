package comm.dao;

import bean.User;

public interface UserDao {
	public User findUser(Long nmb,String pw);
	public byte[] findHead(Long stu_nmb);
	public String findName(Long nmb);
	
	/**
	 * ���¿���
	 * @param nmb ѧ��
	 * @param oldPw �ɿ���
	 * @param newPw �¿���
	 * @return ����������
	 * 0��ʾ0��������ɿ����ѧ�Ŵ��󣩣�
	 * 1��ʾ1�����£��ɹ�����
	 */
	public int updatePw(Long nmb,String oldPw,String newPw);
	
	public void updateUser(User user);
	public void updateHead(Long nmb,byte[] headImg);
}
