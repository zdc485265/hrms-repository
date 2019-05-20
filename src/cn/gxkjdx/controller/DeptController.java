package cn.gxkjdx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gxkjdx.service.DeptService;

@Controller
@Scope(value="request")
@RequestMapping(value="dept")
public class DeptController {
	
	private static final Logger logger =LogManager.getLogger(DeptController.class);
	
	@Autowired
	DeptService deptService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toDeptSetUp")
	public Object toDeptSetUp(HttpServletRequest request){
		logger.debug("deptController-------------------toDeptSetUp");
		
		return "deptSetUp";
	}
	
	@RequestMapping(value="getDepts",produces="text/json;charset=UTF-8")
	@ResponseBody
	public Object getDepts(Long deptId){
		logger.debug("deptController-------------------toDeptSetUp:"+deptId);
		String depts = deptService.getDepts(deptId);
		return depts;
	}
	
	/**
	 * 获取所属信息
	 * @return
	 */
	@RequestMapping(value="toAddDept")
	@ResponseBody
	public Object toAddDept(Long deptId){
		logger.debug("deptController-------------------toAddDept:"+deptId);
		Map<String, Object> info = deptService.toAddDept(deptId);
		return info;
	}

	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditDept")
	@ResponseBody
	public Object toEditDept(Long deptId){
		logger.debug("deptController-------------------toEditDept:"+deptId);
		Map<String, Object> info = deptService.toEditDept(deptId);
		return info;
	}
	
	@RequestMapping(value="deptSetUp")
	public Object deptSetUp(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("deptController-------------------deptSetUp:"+map);
		Map<String, Object> info = deptService.deptSetUp(map);
		request.setAttribute("dept_set_up_msg", info.get("dept_set_up_msg"));
		return toDeptSetUp(request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteDept")
	@ResponseBody
	public Object deleteDept(Long deptId){
		logger.debug("deptController-------------------deleteDept:"+deptId);
		Map<String, Object> info = deptService.deleteDept(deptId);
		return info;
	}
}
