package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface PowerService {

	Page findPowerByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddPower();
	Map<String, Object> addPower(Map<String, Object> entity);
	Map<String, Object> toEditPower(Object powerId);
	Map<String, Object> editPower(Map<String, Object> entity);
	Map<String, Object> deletePower(Long[] power_id);
}
