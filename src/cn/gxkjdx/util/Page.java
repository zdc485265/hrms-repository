package cn.gxkjdx.util;


import java.util.List;
import java.util.Map;

public class Page{
	
	//�ܼ�¼��
	private int count;
	//��ҳ����
	private List<Map<String, Object>> data;
	//��ǰ����
	private int index;
	//ÿҳ�ļ�¼��
	private int size;
	//��ҳ��
	private int totalPage;
	

	public Page() {
	
	}

	public Page(int index, int size, int count, List<Map<String, Object>> data) {
		
		this.index = index;
		this.size = size;
		this.count = count;
		this.data = data;
		//������ҳ��
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
	//�ж��Ƿ�����һҳ
	public boolean isNext() {
		//��ǰ��������ҪС��ҳ��������һҳ
		//���⣺Ϊʲô��Ҫ��1�أ�
		//��Ϊ����ҳ���Ǵ�1��ʼ���㣬����ǰ�����Ǵ�0��ʼ����ġ�
		//��1��Ϊ�ˣ�˫�����Ǵ�0��ʼ����
		if(this.index<this.totalPage-1) {
			return true;
		}else {
			return false;
		}
	}
	//�ж��Ƿ�����һҳ
	public boolean isPrevious() {
		//��ǰ��������Ҫ����0
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