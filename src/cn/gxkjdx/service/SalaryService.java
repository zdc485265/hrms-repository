package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface SalaryService {

	Page findSalaryByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddSalary();
	Map<String, Object> addSalary(Map<String, Object> entity,Long[]item_id,Float[]salary_item_value);
	Map<String, Object> toEditSalary(Object salaryId);
	Map<String, Object> editSalary(Map<String, Object> entity,Long[]item_id,Float[]salary_item_value);
	Map<String, Object> toSalaryDetailed(Object salaryId);
	Map<String, Object> deleteSalary(Object salaryId);
}
