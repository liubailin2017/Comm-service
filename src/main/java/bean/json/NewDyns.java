package bean.json;

import java.util.ArrayList;

import bean.Dynamics;
/**
 * 最新的动态
 * 就是如果你在一条动态下做了回复（评论）操作，别人又回复了你，这条动态就会放进dyns下面
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
