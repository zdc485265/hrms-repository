package cn.gxkjdx.util;


import java.util.List;
import java.util.Map;

public class Page{
	
	//总记录数
	private int count;
	//分页数据
	private List<Map<String, Object>> data;
	//当前索引
	private int index;
	//每页的记录数
	private int size;
	//总页数
	private int totalPage;
	

	public Page() {
	
	}

	public Page(int index, int size, int count, List<Map<String, Object>> data) {
		
		this.index = index;
		this.size = size;
		this.count = count;
		this.data = data;
		//计算总页数
		if(count%size==0) {
			this.totalPage=count/size;
		}else {
			this.totalPage=count/size+1;
		}
	}
	
	public int getCount() {
		return count;
	}
	public List<Map<String, Object>> getData() {
		return data;
	}
	public int getIndex() {
		return index;
	}
	public int getSize() {
		return size;
	}
	public int getTotalPage() {
		return totalPage;
	}
	//判断是否有下一页
	public boolean isNext() {
		//当前索引必须要小于页数才有下一页
		//问题：为什么需要减1呢？
		//因为，总页数是从1开始计算，而当前索引是从0开始计算的。
		//减1是为了，双方都是从0开始计算
		if(this.index<this.totalPage-1) {
			return true;
		}else {
			return false;
		}
	}
	//判断是否有上一页
	public boolean isPrevious() {
		//当前索引必须要大于0
		if(this.index>0) {
			return true;
		}else {
			return false;
		}
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}