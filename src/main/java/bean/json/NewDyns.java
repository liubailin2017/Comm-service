package bean.json;

import java.util.ArrayList;

import bean.Dynamics;
/**
 * ���µĶ�̬
 * �����������һ����̬�����˻ظ������ۣ������������ֻظ����㣬������̬�ͻ�Ž�dyns����
 * @author liubailin
 *
 */
public class NewDyns {
	private ArrayList<Dynamics> dyns;
	private String result;
	private String msg;
	
	public ArrayList<Dynamics> getDyns() {
		return dyns;
	}
	public void setDyns(ArrayList<Dynamics> dyns) {
		this.dyns = dyns;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "NewDyns [dyns=" + dyns + ", result=" + result + ", msg=" + msg + "]";
	}
}
