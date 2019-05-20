package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface ModularService {

	Page findModularByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> addModular(Map<String, Object> entity);
	Map<String, Object> toEditModular(Object modularId);
	Map<String, Object> editModular(Map<String, Object> entity);
	Map<String, Object> deleteModular(Long[] modular_id);
}
