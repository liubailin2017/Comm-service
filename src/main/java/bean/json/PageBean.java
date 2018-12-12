package bean.json;

import java.util.List;

public class PageBean {
	private List<?> list;
	private int pageNo;
	private int pageSize;
	private int totalRecords;
	
	/**
	 * ����ܵ�ҳ����
	 * @return
	 */
	public int getTotalPages(){
		if(totalRecords%pageSize==0){
			return totalRecords/pageSize;
		}else{
			return totalRecords/pageSize+1;
		}
	}
	/**
	 * �����ҳ
	 * @return
	 */
	public int getToPageNo(){
		return 1;
	}
	/**
	 * �����һҳ
	 */
	public int getPreviousPageNo(){
		if(pageNo<=1){
			return 1;
		}	
			return pageNo-1;
	}
	/**
	 * �����һҳ
	 */
	public int getNextPageNo(){
		if(pageNo>=getTotalPages()){
			return getTotalPages();
		}
		return pageNo+1;
	}
	/**
	 * ���βҳ
	 */
	public int getBottomPageNo(){
		return getTotalPages();
	}
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}
