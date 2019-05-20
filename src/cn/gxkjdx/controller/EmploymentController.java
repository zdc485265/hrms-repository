package cn.gxkjdx.controller;

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

import cn.gxkjdx.service.EmploymentService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="employment")
public class EmploymentController {
	
	private static final Logger logger =LogManager.getLogger(EmploymentController.class);
	
	@Autowired
	EmploymentService employmentService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toEmploymentList")
	public Object toEmploymentList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("employmentController-------------------toEmploymentList:"+map);
		String index =(String) map.get("index");
		Page page = employmentService.findResumeByPage(null,index, null);
		request.setAttribute("page", page);
		return "employmentList";
	}
	
	
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toRegisterEmployment")
	public Object toRegisterEmployment(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("employmentController-------------------toRegisterEmployment:"+map);
		Object resumeId=map.get("resume_id");
		Map<String, Object> info = employmentService.toRegisterEmployment(resumeId);
		Object rmsg = request.getAttribute("employment_register_msg");
		Object imsg = info.get("employment_register_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("employment_register_msg", rmsg+" "+imsg);
		request.setAttribute("resume", info.get("resume"));
		request.setAttribute("recruits", info.get("recruits"));
		request.setAttribute("interview1", info.get("interview1"));
		request.setAttribute("interview2", info.get("interview2"));
		request.setAttribute("interview3", info.get("interview3"));
		request.setAttribute("employment", info.get("employment"));
		request.setAttribute("newDate", info.get("newDate"));
		return "employmentRegister";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="registerEmployment")
	public Object registerEmployment(@RequestParam Map<String, Object> entity,HttpServletRequest request,HttpSession session){
		logger.debug("employmentController-------------------registerEmployment:"+entity);
		Map<String, Object> admin =(Map<String, Object>) session.getAttribute("admin");
		entity.put("admin_id", admin.get("admin_id"));
		Map<String, Object> info = employmentService.registerEmployment(entity);
		request.setAttribute("employment_register_msg", info.get("employment_register_msg"));
		return toRegisterEmployment(entity, request);
	}
	
	@RequestMapping(value="toEmploymentDetailed")
	public Object toEmploymentDetailed(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("employmentController-------------------toEmploymentDetailed:"+map);
		Object resumeId = map.get("resume_id");
		Map<String, Object> info = employmentService.toEmploymentDetailed(resumeId);
		request.setAttribute("resume", info.get("resume"));
		request.setAttribute("recruits", info.get("recruits"));
		request.setAttribute("interview1", info.get("interview1"));
		request.setAttribute("interview2", info.get("interview2"));
		request.setAttribute("interview3", info.get("interview3"));
		request.setAttribute("employment", info.get("employment"));
		request.setAttribute("employment_detailed_msg", info.get("employment_detailed_msg"));
		return "employmentDetailed";
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteEmployment")
	public Object deleteEmployment(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("employmentController-------------------deleteEmployment:"+map);
		Map<String, Object> info = employmentService.deleteEmployment(map.get("resume_id"));
		request.setAttribute("employment_list_msg", info.get("employment_list_msg"));
		return toEmploymentList(new HashMap<>(), request);
	}
}
