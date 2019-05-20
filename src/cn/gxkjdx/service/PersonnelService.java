package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface PersonnelService {

	Page findPersonnelByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddPersonnel();
	Map<String, Object> addPersonnel(Map<String, Object> entity);
	Map<String, Object> toEditPersonnel(Object personnelId);
	Map<String, Object> editPersonnel(Map<String, Object> entity);
	Map<String, Object> deletePersonnel(Map<String, Object> map);
	Map<String, Object> foreverDeletePersonnel(Map<String, Object> map);
	Map<String, Object> recoveryDeletePersonnel(Map<String, Object> map);
	Map<String, Object> toPersonnelDetailed(Object personnelId);
}
