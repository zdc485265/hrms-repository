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
import cn.gxkjdx.service.ModularService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="modular")
public class ModularController {
	
	private static final Logger logger =LogManager.getLogger(ModularController.class);
	
	@Autowired
	ModularService modularService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toModularList")
	public Object toModularList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("modularController-------------------toModularList:"+map);
		String index =(String) map.get("index");
		Page page = modularService.findModularByPage(null,index, null);
		request.setAttribute("page", page);
		return "modularList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddModular")
	public Object toAddModular(HttpServletRequest request){
		logger.debug("modularController-------------------toAddModular");
		return "modularAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addModular")
	public Object addModular(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("modularController-------------------addModular");
		Map<String, Object> info = modularService.addModular(entity);
		request.setAttribute("modular_add_msg", info.get("modular_add_msg"));
		return toAddModular(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditModular")
	public Object toEditModular(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("modularController-------------------toEditModular:"+map);
		Object modularId=map.get("modular_id");
		Map<String, Object> info = modularService.toEditModular(modularId);
		Object rmsg = request.getAttribute("modular_edit_msg");
		Object imsg = info.get("modular_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("modular_edit_msg",rmsg+" "+ imsg);
		request.setAttribute("modular",info.get("modular"));
		return "modularEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editModular")
	public Object editModular(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("modularController-------------------editModular:"+entity);
		Map<String, Object> info = modularService.editModular(entity);
		request.setAttribute("modular_edit_msg", info.get("modular_edit_msg"));
		return toEditModular(entity, request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteModular")
	public Object deleteModular(Long[] modular_id,HttpServletRequest request){
		logger.debug("modularController-------------------deleteModular:"+Arrays.toString(modular_id));
		Map<String, Object> info = modularService.deleteModular(modular_id);
		request.setAttribute("modular_list_msg", info.get("modular_list_msg"));
		return toModularList(new HashMap<>(), request);
	}
}
