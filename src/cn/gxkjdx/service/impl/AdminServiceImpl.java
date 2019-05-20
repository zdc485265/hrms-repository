package cn.gxkjdx.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.mapper.DeptMapper;
import cn.gxkjdx.mapper.ModularMapper;
import cn.gxkjdx.mapper.PowerMapper;
import cn.gxkjdx.mapper.RoleMapper;
import cn.gxkjdx.service.AdminService;
import cn.gxkjdx.util.Md5Utils;
import cn.gxkjdx.util.Page;

@Service
public class AdminServiceImpl implements AdminService{
	
	private static final Logger logger =LogManager.getLogger(AdminServiceImpl.class);
	
	
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	ModularMapper modularMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	PowerMapper powerMapper;
	
	@Autowired
	DeptMapper deptMapper;

	@Override
	public Map<String, Object> login(Map<String, Object> entity) {
		logger.debug("AdminServiceImpl---login");
		//获取账号和密码
		Object adminAccount=entity.get("admin_account");
		Object adminPwd=entity.get("admin_pwd");
		//通过账号查找用户
		Map<String, Object> admin = adminMapper.findByAccount(adminAccount);
		if(adminPwd.equals(admin.get("admin_pwd"))){
			//获取角色用户角色信息
			Long roleId=(Long)admin.get("role_id");
			Map<String, Object> role = roleMapper.findById(roleId);
			//获取权限信息
			String powerIds = (String)role.get("role_powers");
			List<Map<String, Object>> powers = powerMapper.findByIds(powerIds);
			role.put("powers", powers);
			//获取所有的模块
			List<Map<String, Object>> modularAlls = modularMapper.findAll();
			List<Map<String, Object>> modulars =new ArrayList<>();
			for (Map<String, Object> modular : modularAlls) {
				Object modularId = modular.get("modular_id");
				for (Map<String, Object> power : powers) {
					Object modularId2 = power.get("modular_id");
					if(modularId.equals(modularId2)){
						modulars.add(modular);
						break;
					}
				}
			}
			admin.put("modulars", modulars);
			admin.put("role", role);
			return admin;
		}
		return null;
	}

	/**
	 * 获取page
	 */
	@Override
	public Page findAdminByPage(Map<String, Object> entity,String indexo, String sizeo) {
		int index,size;
		
		//判空
		if(null==indexo || "".equals(indexo)){
			index=0;
		}else {
			index=new Integer(indexo);
		}
		if(null==sizeo || "".equals(indexo)){
			size=5;
		}else {
			size=new Integer(sizeo);
		}
		//获取数据
		List<Map<String, Object>> data = adminMapper.findByConditionToPage(entity,index*size,size);
		for (Map<String, Object> admin : data) {
			Object roleId = admin.get("role_id");
			if(null!=roleId){
				Map<String, Object> role = roleMapper.findById(roleId);
				admin.put("role", role);
			}
		}
		//获取数据个数
		int count = adminMapper.countByCondition(entity);
		//构建page
		Page page=new Page(index, size, count, data);
		
		return page;
	}
	
	/**
	 * 添加页面
	 */
	public Map<String, Object> toAddAdmin(){
		Map<String, Object> map= new HashMap<>();
		List<Map<String, Object>> roles = roleMapper.findAll();
		List<Map<String, Object>> depts = deptMapper.findAll();
		map.put("roles", roles);
		map.put("depts", depts);
		return map;
	}
	
	/**
	 * 添加数据
	 */
	@Override
	public Map<String, Object> addAdmin(Map<String, Object> entity) {
		Map<String, Object> info=new HashMap<>();
		StringBuffer sb=new StringBuffer("新增失败,");
		Object adminName = entity.get("admin_name");
		Object adminAccount = entity.get("admin_account");
		Object adminPwd = entity.get("admin_pwd");
		int num=0;
		if(null==adminName||"".equals(adminName)){
			sb.append("用户名不能为空,");
			num++;
		}
		if(null==adminAccount||"".equals(adminAccount)){
			sb.append("账号名不能为空,");
			num++;
		}else{
			Map<String, Object> admin = adminMapper.findByAccount(adminAccount);
			if(null!=admin){
				sb.append("账号名已重复,");
				info.put("admin_add_msg", sb.toString());
				return info;
			}
		}
		
		if(null==adminPwd||"".equals(adminPwd)){
			sb.append("密码不能为空,");
			num++;
		}else{
			entity.put("admin_pwd", Md5Utils.md5(adminPwd.toString()));
		}
		
		sb.setLength(sb.length()-1);
		if(num==0){
			int insert = adminMapper.insert(entity);
			if(insert>0){
				sb=new StringBuffer("新增成功！");
			}
		}
		info.put("admin_add_msg", sb.toString());
		return info;
	}

	/**
	 * 获取页面显示数据
	 */
	@Override
	public Map<String, Object> toEditAdmin(Object adminId) {
		Map<String, Object> info=new HashMap<>();
		if(null!=adminId&&!"".equals(adminId)){
			Map<String, Object> admin = adminMapper.findById(adminId);
			info.put("admin", admin);
		}else {
			info.put("admin_edit_msg", "出错了,未找到此用户");
		}
		List<Map<String, Object>> roles = roleMapper.findAll();
		List<Map<String, Object>> depts = deptMapper.findAll();
		info.put("roles", roles);
		info.put("depts", depts);
		return info;
	}

	/**
	 * 修改数据
	 */
	@Override
	public Map<String, Object> editAdmin(Map<String, Object> entity) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb =new StringBuffer("编辑失败,");
		Object adminName = entity.get("admin_name");
		Object adminAccount = entity.get("admin_account");
		Object adminPwd = entity.get("admin_pwd");
		int num=0;
		if(null==adminName||"".equals(adminName)){
			sb.append("用户名不能为空,");
			num++;
		}
		if(null==adminAccount||"".equals(adminAccount)){
			sb.append("账号名不能为空,");
			num++;
		}else{
			Map<String, Object> admin = adminMapper.findByAccount(adminAccount);
			
			if(null!=admin && !(admin.get("admin_id").toString()).equals(entity.get("admin_id"))){
				sb.append("账号名已重复,");
				info.put("admin_edit_msg", sb.toString());
				return info;
			}
			Object oldAdminPwd = admin.get("admin_pwd");
			
			if(null==adminPwd||"".equals(adminPwd)){
				sb.append("密码不能为空,");
				num++;
			}else {
				if(!adminPwd.equals(oldAdminPwd)){
					entity.put("admin_pwd", Md5Utils.md5(adminPwd.toString()));
				}
			}
		}
		
		sb.setLength(sb.length()-1);
		if(0==num){
			int update = adminMapper.update(entity);
			if(update>0){
				sb=new StringBuffer("编辑成功！");
			}
		}
		info.put("admin_edit_msg", sb.toString());
		return info;
	}

	/**
	 * 删除数据
	 */
	@Override
	public Map<String, Object> deleteAdmin(Long[] admin_id) {
		Map<String, Object> info=new HashMap<>();
		String msg="删除失败!";
		try {
			if(admin_id.length>0){
				String adminIdStr = Arrays.toString(admin_id);
				String adminIds = adminIdStr.substring(1, adminIdStr.length()-1);
				int delete = adminMapper.delete(adminIds);
				if(delete>0){
					msg="删除成功！";
				}
			}
		} catch (Exception e) {
			msg="未知异常";
		}
		
		info.put("admin_list_msg", msg);
		return info;
	}
	
}
