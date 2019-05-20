package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;

public interface RoleService {

	Page findRoleByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddRole();
	Map<String, Object> addRole(Map<String, Object> entity);
	Map<String, Object> toEditRole(Object roleId);
	Map<String, Object> editRole(Map<String, Object> entity);
	Map<String, Object> deleteRole(Long[] role_id);
}
