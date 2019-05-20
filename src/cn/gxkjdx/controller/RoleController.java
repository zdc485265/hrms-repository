package cn.gxkjdx.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.gxkjdx.service.RoleService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="role")
public class RoleController {
	
	private static final Logger logger =LogManager.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toRoleList")
	public Object toRoleList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("roleController-------------------toRoleList:"+map);
		String index =(String) map.get("index");
		Page page = roleService.findRoleByPage(null,index, null);
		request.setAttribute("page", page);
		return "roleList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddRole")
	public Object toAddRole(HttpServletRequest request){
		logger.debug("roleController-------------------toAddRole");
		Map<String, Object> map = roleService.toAddRole();
		request.setAttribute("modulars", map.get("modulars"));
		return "roleAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addRole")
	public Object addRole(@RequestParam Map<String, Object> entity,Long[] powerIds,HttpServletRequest request){
		logger.debug("roleController-------------------addRole:"+entity+";powerIds:"+Arrays.toString(powerIds));
		String powerIdStr = Arrays.toString(powerIds);
		String rolePowers=null;
		if(null!=powerIds&&powerIds.length>0){
			rolePowers=powerIdStr.substring(1, powerIdStr.length()-1);
		}
		entity.put("role_powers", rolePowers);
		Map<String, Object> info = roleService.addRole(entity);
		request.setAttribute("role_add_msg", info.get("role_add_msg"));
		return toAddRole(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditRole")
	public Object toEditRole(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("roleController-------------------toEditRole:"+map);
		Object roleId=map.get("role_id");
		Map<String, Object> info = roleService.toEditRole(roleId);
		Object rmsg = request.getAttribute("role_edit_msg");
		Object imsg = info.get("role_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("role_edit_msg",rmsg+" "+ imsg);
		request.setAttribute("role", info.get("role"));
		request.setAttribute("modulars", info.get("modulars"));
		return "roleEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editRole")
	public Object editRole(@RequestParam Map<String, Object> entity,Long[] powerIds,HttpServletRequest request){
		logger.debug("roleController-------------------editRole:"+entity+";powerIds:"+Arrays.toString(powerIds));
		String powerIdStr = Arrays.toString(powerIds);
		String rolePowers=null;
		if(powerIds.length>0){
			rolePowers=powerIdStr.substring(1, powerIdStr.length()-1);
		}
		entity.put("role_powers", rolePowers);
		Map<String, Object> info = roleService.editRole(entity);
		request.setAttribute("role_edit_msg", info.get("role_edit_msg"));
		return toEditRole(entity, request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteRole")
	public Object deleteRole(Long[] role_id,HttpServletRequest request){
		logger.debug("roleController-------------------deleteRole:"+Arrays.toString(role_id));
		Map<String, Object> info = roleService.deleteRole(role_id);
		request.setAttribute("role_list_msg", info.get("role_list_msg"));
		return toRoleList(new HashMap<>(), request);
	}
}
