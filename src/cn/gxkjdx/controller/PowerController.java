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
import cn.gxkjdx.service.PowerService;
import cn.gxkjdx.util.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="power")
public class PowerController {
	
	private static final Logger logger =LogManager.getLogger(PowerController.class);
	
	@Autowired
	PowerService powerService;

	/**
	 * 页面列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toPowerList")
	public Object toPowerList(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("powerController-------------------toPowerList:"+map);
		String index =(String) map.get("index");
		Page page = powerService.findPowerByPage(null,index, null);
		request.setAttribute("page", page);
		return "powerList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="toAddPower")
	public Object toAddPower(HttpServletRequest request){
		logger.debug("powerController-------------------toAddPower");
		Map<String, Object> map = powerService.toAddPower();
		request.setAttribute("modulars", map.get("modulars"));
		request.setAttribute("powers", map.get("powers"));
		return "powerAdd";
	}
	
	/**
	 * 添加数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addPower")
	public Object addPower(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("powerController-------------------addPower");
		Map<String, Object> info = powerService.addPower(entity);
		request.setAttribute("power_add_msg", info.get("power_add_msg"));
		return toAddPower(request);
	}
	
	/**
	 * 编辑页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toEditPower")
	public Object toEditPower(@RequestParam Map<String, Object> map,HttpServletRequest request){
		logger.debug("powerController-------------------toEditPower:"+map);
		Object powerId=map.get("power_id");
		Map<String, Object> info = powerService.toEditPower(powerId);
		Object rmsg = request.getAttribute("power_edit_msg");
		Object imsg = info.get("power_edit_msg");
		if(null==rmsg){
			rmsg="";
		}
		if(null==imsg){
			imsg="";
		}
		request.setAttribute("power_edit_msg",rmsg+" "+ imsg);
		request.setAttribute("power", info.get("power"));
		request.setAttribute("powers", info.get("powers"));
		request.setAttribute("modulars", info.get("modulars"));
		return "powerEdit";
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editPower")
	public Object editPower(@RequestParam Map<String, Object> entity,HttpServletRequest request){
		logger.debug("powerController-------------------editPower:"+entity);
		Map<String, Object> info = powerService.editPower(entity);
		request.setAttribute("power_edit_msg", info.get("power_edit_msg"));
		return toEditPower(entity, request);
	}
	
	/**
	 * 删除数据
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deletePower")
	public Object deletePower(Long[] power_id,HttpServletRequest request){
		logger.debug("powerController-------------------deletePower:"+Arrays.toString(power_id));
		Map<String, Object> info = powerService.deletePower(power_id);
		request.setAttribute("power_list_msg", info.get("power_list_msg"));
		return toPowerList(new HashMap<>(), request);
	}
}
