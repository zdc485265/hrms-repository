package cn.gxkjdx.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gxkjdx.service.AdminService;
import cn.gxkjdx.util.Md5Utils;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="admin")
public class AdminController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;

	/**
	 * 登陆
	 * @param entity
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login")
	public String login(@RequestParam Map<String, Object> entity,HttpSession session,HttpServletRequest request){
		logger.debug("adminControoler ----- ---login:"+entity);
		try {
			//1.将密码md5加密后在校验
			entity.put("admin_pwd", Md5Utils.md5((String)entity.get("admin_pwd")));
			Map<String, Object> admin = adminService.login(entity);
			if (admin!=null) {
				Integer adminStatus =(Integer) admin.get("admin_status");
				if(adminStatus==0){
					session.setAttribute("admin", admin);
					return "index";
				}else {
					request.setAttribute("admin_login_msg", "用户被禁用");
					return "forward:/login.jsp";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("admin_login_msg", "登录失败，请确认用户账号或密码是否正确");
		return "forward:/login.jsp";
	}
	
	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toAdminList")
	public Object toAdminList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("adminController-------------------toAdminList:"+map);
		String index =(String) map.get("index");
		Page page = adminService.findAdminByPage(null,index, null);
		request.setAttribute("page", page);
		return "adminList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddAdmin")
	public Object toAddAdmin(HttpServletRequest request){
		logger.debug("adminController-------------------toAddAdmin");
		Map<String, Object> map = adminService.toAddAdmin();
		request.setAttribute("roles", map.get("roles"));
		request.setAttribute("depts", map.get("depts"));
		Object rmsg = request.getAttribute("admin_add_msg");
		Object imsg = map.get("admin_add_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("admin_add_msg", rmsg+" "+imsg);
		return "adminAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addAdmin")
	public Object addAdmin(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("adminController-------------------addAdmin");
		Map<String, Object> info = adminService.addAdmin(entity);
		request.setAttribute("admin_add_msg", info.get("admin_add_msg"));
		return toAddAdmin(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditAdmin")
	public Object toEditAdmin(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("adminController-------------------toEditAdmin:"+map);
		Object adminId=map.get("admin_id");
		Map<String, Object> info = adminService.toEditAdmin(adminId);
		Object rmsg = request.getAttribute("admin_edit_msg");
		Object imsg = info.get("admin_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("admin_edit_msg", rmsg+" "+imsg);
		request.setAttribute("admin",info.get("admin"));
		request.setAttribute("roles", info.get("roles"));
		request.setAttribute("depts", info.get("depts"));
		return "adminEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editAdmin")
	public Object editAdmin(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("adminController-------------------editAdmin:"+entity);
		Map<String, Object> info = adminService.editAdmin(entity);
		request.setAttribute("admin_edit_msg", info.get("admin_edit_msg"));
		return toEditAdmin(entity, request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteAdmin")
	public Object deleteAdmin(Long[] admin_id,HttpServletRequest request){
		logger.debug("adminController-------------------deleteAdmin:"+Arrays.toString(admin_id));
		Map<String, Object> info = adminService.deleteAdmin(admin_id);
		request.setAttribute("admin_list_msg", info.get("admin_list_msg"));
		return toAdminList(new HashMap<>(), request);
	}
}
