package cn.gxkjdx.service;

import java.util.Map;

import cn.gxkjdx.util.Page;


public interface AdminService {

	Map<String, Object> login(Map<String, Object> entity);
	Page findAdminByPage(Map<String, Object> entity,String index,String size);
	Map<String, Object> toAddAdmin();
	Map<String, Object> addAdmin(Map<String, Object> entity);
	Map<String, Object> toEditAdmin(Object adminId);
	Map<String, Object> editAdmin(Map<String, Object> entity);
	Map<String, Object> deleteAdmin(Long[] admin_id);
}
