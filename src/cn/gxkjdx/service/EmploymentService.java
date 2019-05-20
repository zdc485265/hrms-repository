package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;


public interface EmploymentService {

	Page findResumeByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toRegisterEmployment(Object resumeId);
	Map<String, Object> registerEmployment(Map<String, Object> entity);
	Map<String, Object> deleteEmployment(Object resumeId);
	Map<String, Object> toEmploymentDetailed(Object resumeId);
}
