package cn.gxkjdx.service;

import java.util.Map;


public interface DeptService {

	String getDepts(Object deptId);
	Map<String, Object> toAddDept(Object deptId);
	Map<String, Object> toEditDept(Object deptId);
	Map<String, Object> deptSetUp(Map<String, Object> map);
	Map<String, Object> deleteDept(Object deptId);
}
