package comm.dao;

import bean.User;

public interface UserDao {
	public User findUser(Long nmb,String pw);
	public byte[] findHead(Long stu_nmb);
	public String findName(Long nmb);
	
	/**
	 * 更新口令
	 * @param nmb 学号
	 * @param oldPw 旧口令
	 * @param newPw 新口令
	 * @return 更新条数，
	 * 0表示0条（输入旧口令或学号错误）；
	 * 1表示1条更新（成功）；
	 */
	public int updatePw(Long nmb,String oldPw,String newPw);
	
	public void updateUser(User user);
	public void updateHead(Long nmb,byte[] headImg);
}
